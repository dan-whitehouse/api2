package org.ricone.api.xpress.request.xCourse;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.Course;
import org.ricone.api.core.model.CourseGrade;
import org.ricone.api.core.model.CourseIdentifier;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.xpress.component.BaseMapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("XPress:XCourses:XCourseMapper")
public class XCourseMapper extends BaseMapper {

    public XCourseMapper() {
    }

    public XCoursesResponse convert(List<CourseWrapper> instance) throws MappingException {
        List<XCourse> list = new ArrayList<>();
        for (CourseWrapper wrapper : instance) {
            XCourse xCourse = map(wrapper.getCourse(), wrapper.getDistrictId());
            if(xCourse != null) {
                list.add(xCourse);
            }
        }

        XCoursesResponse response = new XCoursesResponse();
        XCourses xCourses = new XCourses();
        xCourses.setXCourse(list);

        response.setXCourses(xCourses);
        return response;
    }

    public XCourseResponse convert(CourseWrapper instance) throws MappingException {
        XCourseResponse response = new XCourseResponse();
        XCourse xCourse = map(instance.getCourse(), instance.getDistrictId());
        if(xCourse != null) {
            response.setXCourse(xCourse);
        }
        return response;
    }


    public XCourse map(Course instance, String districtId) throws MappingException {
        try {
            XCourse xCourse = new XCourse();
            xCourse.setDistrictId(districtId); //Required by Filtering
            xCourse.setRefId(instance.getCourseRefId());
            xCourse.setCourseTitle(instance.getTitle());
            xCourse.setSubject(instance.getSubjectCode());
            xCourse.setDescription(instance.getDescription());

            if(instance.getSchool() != null) {
                xCourse.setSchoolRefId(instance.getSchool().getSchoolRefId());
            }

            xCourse.setScedCourseCode(instance.getScedCourseCode());
            xCourse.setScedCourseLevelCode(instance.getScedCourseLevelCode());
            xCourse.setScedCourseSubjectAreaCode(instance.getScedCourseSubjectAreaCode());

            //Identifiers
            List<OtherId> otherIdList = new ArrayList<>();
            for (CourseIdentifier id : instance.getCourseIdentifiers()) {
                if(LEA_ID.equalsIgnoreCase(id.getIdentificationSystemCode())) {
                    xCourse.setLeaCourseId(id.getCourseId());
                }
                else if(SCHOOL_ID.equalsIgnoreCase(id.getIdentificationSystemCode())) {
                    xCourse.setSchoolCourseId(id.getCourseId());
                }
                else {
                    OtherId otherId = mapOtherId(id);
                    if(otherId != null) {
                        otherIdList.add(otherId);
                    }
                }
            }

            //Other Identifiers
            if(CollectionUtils.isNotEmpty(otherIdList)) {
                OtherIds otherIds = new OtherIds();
                otherIds.setOtherId(otherIdList);
                xCourse.setOtherIds(otherIds);
            }

            //Applicable Education Levels
            ApplicableEducationLevels applicableEducationLevels = mapApplicableEducationLevels(instance.getCourseGrades());
            if(applicableEducationLevels != null) {
                xCourse.setApplicableEducationLevels(applicableEducationLevels);
            }

            //Metadata
            xCourse.setMetadata(mapMetadata(instance));


            return xCourse;
        }
        catch (Exception ex) {
            throw new MappingException();
        }
    }

    private ApplicableEducationLevels mapApplicableEducationLevels(Set<CourseGrade> courseGrades) {
        ApplicableEducationLevels applicableEducationLevels = new ApplicableEducationLevels();
        for (CourseGrade grade : courseGrades) {
            applicableEducationLevels.getApplicableEducationLevel().add(grade.getGradeLevelCode());
        }

        if(applicableEducationLevels.isEmptyObject()) {
            return null;
        }
        return applicableEducationLevels;
    }

    private OtherId mapOtherId(CourseIdentifier id) {
        OtherId otherId = new OtherId();
        otherId.setId(id.getCourseId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject()) {
            return null;
        }
        return otherId;
    }

    private Metadata mapMetadata(Course course) {
        return new Metadata(course.getCourseSchoolYear());
    }
}