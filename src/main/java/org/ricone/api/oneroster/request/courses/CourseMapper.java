package org.ricone.api.oneroster.request.courses;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.CourseIdentifier;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component("OneRoster:Courses:CourseMapper")
class CourseMapper {
    CourseMapper() {
    }

    CoursesResponse convert(List<CourseWrapper> instance) {
        List<Course> list = new ArrayList<>();
        for (CourseWrapper wrapper : instance) {
            Course org = map(wrapper.getCourse(), wrapper.getDistrictId());
            if(org != null) {
                list.add(org);
            }
        }

        CoursesResponse response = new CoursesResponse();
        response.setCourse(list);
        return response;
    }

    CourseResponse convert(CourseWrapper wrapper) {
        if(wrapper != null) {
            CourseResponse response = new CourseResponse();
            response.setCourse(map(wrapper.getCourse(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Course map(org.ricone.api.core.model.Course instance, String districtId) {
        Course course = new Course();
        course.setSourcedId(instance.getCourseRefId());
        course.setStatus(StatusType.active);
        course.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getCourseSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        course.setMetadata(metadata);

        course.setTitle(instance.getTitle());
        //course.setSchoolYear();
        course.setCourseCode(mapIdentifier(instance.getCourseIdentifiers()));

        //Grades
        instance.getCourseGrades().forEach(courseGrade -> {
            course.getGrades().add(courseGrade.getGradeLevelCode());
        });

        //Subjects
        course.getSubjects().add(instance.getSubjectCode());
        course.getSubjectCodes().add(instance.getSubjectCode());

        //School Year - AcademicSession Ref
        course.setSchoolYear(MappingUtil.buildGUIDRef("academicSessions", instance.getSchool().getSchoolRefId(), GUIDType.academicSession)); //TODO - Not Correct

        //Org - School
        course.setOrg(MappingUtil.buildGUIDRef("schools", instance.getSchool().getSchoolRefId(), GUIDType.org));

        return course;
    }

    private String mapIdentifier(Set<CourseIdentifier> courseIdentifiers) {
        Optional<CourseIdentifier> id = courseIdentifiers.stream().filter(ci -> StringUtils.equalsIgnoreCase(ci.getIdentificationSystemCode(), "School")).findFirst();
        return id.map(CourseIdentifier::getCourseId).orElse(null);
    }
}