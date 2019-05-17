package org.ricone.api.xpress.request.app;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.config.cache.AppCache;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.App;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@Service("Core:App:AppService")
public class AppServiceImp implements AppService {
    private final String SEPARATOR = "_";
    private final String[] objects = new String[]{"XLea", "XSchool", "XCalendar", "XCourse", "XRoster", "XStaff", "XStudent", "XContact"};

    //TODO - District Filters output seems to be missing the ID field.

    @Override
    public Application find(ControllerData metadata) throws Exception {
        App app = AppCache.getInstance().get(metadata.getApplication().getApp().getId());

        Application instance = new Application(app);
        app.getDistricts().forEach(d -> {
            District district = new District();
            district.setId(d.getId());
            district.setName(d.getName());


            d.getSchools().forEach(sch -> {
                School school = new School();
                school.setId(sch.getId());
                school.setName(sch.getLocName());
                school.setKv(sch.getKv());

                district.getSchools().add(school);
            });
            //Set KV's
            if(MapUtils.isNotEmpty(d.getKv())) {
                HashMap<String, String> cleanMap = new HashMap<>();
                d.getKv().forEach((key, value) -> {
                    if(key.startsWith("api")) {
                        cleanMap.put(key, value);
                    }
                });
                if(MapUtils.isNotEmpty(cleanMap)) {
                    district.setKv(cleanMap);
                }
            }

            //Set Filters
            String appId = StringUtils.replace(app.getId(),"_", "|");
            for(String o : objects) {
                district.getFilters().add(FilterCache.getInstance().get(district.getId() + SEPARATOR + appId + SEPARATOR + o));
            }
            instance.getDistricts().add(district);
        });
        return instance;
    }

    @Override
    public Application findByAppId(ControllerData metadata, String appId) throws Exception {
        return null;
    }
}