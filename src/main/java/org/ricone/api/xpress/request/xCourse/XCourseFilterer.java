package org.ricone.api.xpress.request.xCourse;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.OtherId;
import org.ricone.api.xpress.model.XCourse;
import org.ricone.api.xpress.model.XCourseResponse;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.XCourseFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XCourses:XCourseFilterer")
public class XCourseFilterer {
    private final AppService cacheService;

    public XCourseFilterer(AppService cacheService) {
        this.cacheService = cacheService;
    }

    XCoursesResponse apply(XCoursesResponse response, ControllerData metadata) {
        //Filter All
        response.getXCourses().getXCourse().forEach(xCourse -> {
            filter(xCourse, cacheService.getXCourseFilter(xCourse.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXCourses().getXCourse().removeIf(XCourse::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXCourses().getXCourse())) {
            return null;
        }
        return response;
    }

    XCourseResponse apply(XCourseResponse response, ControllerData metadata) {
        filter(response.getXCourse(), cacheService.getXCourseFilter(response.getXCourse().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXCourse().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private void filter(XCourse instance, XCourseFilter filter) {
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
        if(instance.getOtherIds() != null) {
            instance.getOtherIds().getOtherId().forEach(otherId -> {
                if(!filter.getOtherIdsotherIdid()) {
                    otherId.setId(null);
                }
                if(!filter.getOtherIdsotherIdtype()) {
                    otherId.setType(null);
                }
            });

            instance.getOtherIds().getOtherId().removeIf(OtherId::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherIds().getOtherId())) {
                instance.setOtherIds(null);
            }
        }

        //Applicable Education Levels
        if(!filter.getApplicableEducationLevelsapplicableEducationLevel()) {
            instance.setApplicableEducationLevels(null);
        }
    }
}