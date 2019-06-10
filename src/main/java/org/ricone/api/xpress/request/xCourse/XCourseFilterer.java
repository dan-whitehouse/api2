package org.ricone.api.xpress.request.xCourse;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XCourseFilter;
import org.ricone.init.CacheService;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component("XPress:XCourses:XCourseFilterer")
public class XCourseFilterer {
    private final CacheService cacheService;

    public XCourseFilterer(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    XCoursesResponse apply(XCoursesResponse response, ControllerData metadata) {
        Iterator<XCourse> iterator = response.getXCourses().getXCourse().iterator();
        while (iterator.hasNext()) {
            XCourse i = iterator.next();
            i = filter(i, cacheService.getXCourseFilter(i.getDistrictId(), metadata.getApplication().getApp().getId()));

            // Remove object from list if empty
            if (i.isEmptyObject()) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(response.getXCourses().getXCourse())) {
            return null;
        }
        return response;
    }

    XCourseResponse apply(XCourseResponse response, ControllerData metadata) {
        response.setXCourse(filter(response.getXCourse(), cacheService.getXCourseFilter(response.getXCourse().getDistrictId(), metadata.getApplication().getApp().getId())));
        if (response.getXCourse().isEmptyObject()) {
            return null;
        }
        return response;
    }

    public XCourse filter(XCourse instance, XCourseFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if(!filter.getCourseTitle()) {
            instance.setCourseTitle(null);
        }
        if(!filter.getSubject()) {
            instance.setSubject(null);
        }
        if(!filter.getDescription()) {
            instance.setDescription(null);
        }
        if(!filter.getSchoolRefId()) {
            instance.setSchoolRefId(null);
        }
        if(!filter.getScedCourseCode()) {
            instance.setScedCourseCode(null);
        }
        if(!filter.getScedCourseLevelCode()) {
            instance.setScedCourseLevelCode(null);
        }
        if(!filter.getScedCourseSubjectAreaCode()) {
            instance.setScedCourseSubjectAreaCode(null);
        }

        //Identifiers
        if(!filter.getLeaCourseId()) {
            instance.setLeaCourseId(null);
        }
        if(!filter.getSchoolCourseId()) {
            instance.setSchoolCourseId(null);
        }

        //Other Identifiers
        for (OtherId i : instance.getOtherIds().getOtherId()) {
            if(!filter.getOtherIdsotherIdid()) {
                i.setId(null);
            }
            if(!filter.getOtherIdsotherIdtype()) {
                i.setType(null);
            }
        }

        //Applicable Education Levels
        if(!filter.getApplicableEducationLevelsapplicableEducationLevel()) {
            instance.setApplicableEducationLevels(null);
        }

        return instance;
    }
}