package org.ricone.api.xpress.request.app;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.App;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@Service("Core:App:AppService")
public class AppServiceImp implements org.ricone.api.xpress.request.app.AppService {
    private final AppService cacheService;

    public AppServiceImp(AppService cacheService) {this.cacheService = cacheService;}

    //TODO - District Filters output seems to be missing the ID field.

    @Override
    public Application find(ControllerData metadata) throws Exception {
        return findByAppId(metadata, metadata.getApplication().getApp().getId());
    }

    @Override
    public Application findByAppId(ControllerData metadata, String appId) throws Exception {
        App app = cacheService.getAppById(appId);

        Application instance = new Application(app);
        cacheService.getDistrictsByAppId(appId).forEach(d -> {
            District district = new District();
            district.setId(d.getId());
            district.setName(d.getName());

            cacheService.getSchoolsByDistrictId(d.getId()).forEach(sch -> {
                School school = new School();
                school.setId(sch.getId());
                school.setName(sch.getLocName());
                school.setKv(cacheService.getSchoolKVsBySchoolId(sch.getId()));

                district.getSchools().add(school);
            });
            //Set KV's
            if(MapUtils.isNotEmpty(cacheService.getDistrictKVsByDistrictId(d.getId()))) {
                HashMap<String, String> cleanMap = new HashMap<>();
                cacheService.getDistrictKVsByDistrictId(d.getId()).forEach((key, value) -> {
                    if(key.startsWith("api")) {
                        cleanMap.put(key, value);
                    }
                });
                if(MapUtils.isNotEmpty(cleanMap)) {
                    district.setKv(cleanMap);
                }
            }

            //Set Filters
            String id = StringUtils.replace(app.getId(),"_", "|");

            district.getFilters().add(cacheService.getXLeaFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXSchoolFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXCalendarFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXCourseFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXRosterFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXStaffFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXStudentFilter(district.getId(), id));
            district.getFilters().add(cacheService.getXContactFilter(district.getId(), id));

            instance.getDistricts().add(district);
        });
        return instance;
    }
}