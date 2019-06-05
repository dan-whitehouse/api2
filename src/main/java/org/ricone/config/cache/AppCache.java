package org.ricone.config.cache;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.request.xStaff.XStaffController;
import org.ricone.config.ConfigService;
import org.ricone.config.model.App;
import org.ricone.config.model.DataXML;
import org.ricone.config.model.District;
import org.ricone.config.model.School;
import org.ricone.security.acl.Environment;
import org.ricone.security.acl.PathPermission;
import org.ricone.security.acl.PathPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AppCache {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private static AppCache instance = null;
    private static final Integer EXPIRE = 2;
    private final LoadingCache<String, App> cache;

    public static AppCache getInstance() {
        if(instance == null) {
            instance = new AppCache();
        }
        return instance;
    }

    private AppCache() {
        cache = CacheBuilder.newBuilder()
        .expireAfterWrite(EXPIRE, TimeUnit.HOURS)
        .build(new CacheLoader<>() {
            @Override
            public App load(String appId) throws Exception {
                return loadCache(appId);
            }
        });
    }

    public App get(String appId) {
        return cache.getUnchecked(appId);
    }

    public void put(String appId, App app) {
        cache.put(appId, app);
    }

    public void clearCache() {
        cache.invalidateAll();
    }

    private App loadCache(String appId) {
        //Get App
        App app = ConfigService.getInstance().getApp(appId);
        if (app != null) {

            //Get ACL Path Permissions
            DataXML dataXML = ConfigService.getInstance().getDataXMLByApp(app.getId());
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JaxbAnnotationModule());
            xmlMapper.registerModule(new JacksonXmlModule());
            try {
                Environment environment = xmlMapper.readValue(dataXML.getXml().getXml(), Environment.class);

                PathPermissionMapper mapper = new PathPermissionMapper();
                List<PathPermission> pathPermissions = new ArrayList<>();
                environment.getProvisionedZones().getProvisionedZone().getServices().getService().forEach(service -> {
                    pathPermissions.add(mapper.map(environment.getDefaultZone().getId(), service));
                });
                app.setPermissions(pathPermissions);

                //TODO - Remove Output
                pathPermissions.forEach(pathPermission -> {
                    logger.debug("pathPermission: " + pathPermission.toString());
                });

            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //Get Districts For App
            List<District> districts = ConfigService.getInstance().getDistrictsByApp(app.getId());
            if (CollectionUtils.isNotEmpty(districts)) {
                List<District> filtered = districts.stream().filter(district -> (district.getEnabled() && StringUtils.equalsIgnoreCase(district.getProviderId(), System.getenv("provider_id")))).collect(Collectors.toList());
                filtered.forEach(district -> {
                    //Get District API KV's
                    district.setKv(ConfigService.getInstance().getDistrictAPIKV(district.getId()));

                    //Get Schools By District
                    List<School> schools = ConfigService.getInstance().getSchoolsByDistrict(district.getId());
                    if (CollectionUtils.isNotEmpty(schools)) {
                        List<School> filteredSchools = schools.stream().filter(School::getEnabled).collect(Collectors.toList());
                        filteredSchools.forEach(school -> {
                            //Get School API KV's
                            school.setKv(ConfigService.getInstance().getSchoolAPIKV(school.getId()));
                        });
                        district.getSchools().addAll(schools);
                    }
                });
                app.setDistricts(filtered);
            }
        }
        return app;
    }
}