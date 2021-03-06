package org.ricone.security;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.App;
import org.ricone.config.model.DataXML;
import org.ricone.config.model.District;
import org.ricone.security.acl.Environment;
import org.ricone.security.acl.PathPermission;
import org.ricone.security.acl.PathPermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Application implements UserDetails {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String appId;
    private String token;
    private AppService service;

    public Application(String appId, String token, AppService cacheService) {
        this.appId = appId;
        this.token = token;
        this.service = cacheService;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //This doesn't seem to do anything atm.
        return null;
    }

    @Override
    public String getPassword() {
        return token;
    }

    @Override
    public String getUsername() {
        return service.getAppById(appId).getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return service.getAppById(appId).getPublic();
    }

    public App getApp() {
        return service.getAppById(appId);
    }

    public List<PathPermission> getPermissions() {
        //logger.debug("!!!!! - I get accessed everytime? ");
        List<PathPermission> pathPermissions = new ArrayList<>();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JaxbAnnotationModule());
            xmlMapper.registerModule(new JacksonXmlModule());

            DataXML dataXML = service.getDataXMLByAppId(appId);
            Environment environment = xmlMapper.readValue(dataXML.getXml().getXml(), Environment.class);

            PathPermissionMapper mapper = new PathPermissionMapper();
            environment.getProvisionedZones().getProvisionedZone().getServices().getService().forEach(service -> {
                pathPermissions.add(mapper.map(environment.getDefaultZone().getId(), service));
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return pathPermissions;
    }

    public List<String> getDistrictLocalIds() {
        return service.getDistrictsByAppId(appId).stream().map(District::getId).collect(Collectors.toList());
    }

    public HashMap<String, String> getDistrictKVsBySchoolRefId(String schoolRefId) {
        return service.getDistrictKVsBySchoolRefId(schoolRefId);
    }

    public HashMap<String, String> getSchoolKVsBySchoolRefId(String schoolRefId) {
        return service.getSchoolKVsBySchoolRefId(schoolRefId);
    }
}
