package org.ricone.api.oneroster.request.courses;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.view.CourseView;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Courses:CourseMapper")
class CourseMapper {
    CourseMapper() {
    }

    CoursesResponse convert(List<CourseView> instance) {
        List<Course> list = new ArrayList<>();
        for (CourseView wrapper : instance) {
            Course org = map(wrapper, null);
            if(org != null) {
                list.add(org);
            }
        }

        CoursesResponse response = new CoursesResponse();
        response.setCourse(list);
        return response;
    }

    CourseResponse convert(CourseView wrapper) {
        if(wrapper != null) {
            CourseResponse response = new CourseResponse();
            response.setCourse(map(wrapper, null));
            return response;
        }
        return null;
    }

    private Course map(CourseView instance, String districtId) {
        Course course = new Course();
        course.setSourcedId(instance.getSourcedId());
        course.setStatus(StatusType.active);
        course.setDateLastModified(null);
        course.setMetadata(mapMetadata(instance, districtId));

        course.setTitle(instance.getTitle());
        course.setCourseCode(instance.getCourseCode());

        //Grades
        instance.getGrades().forEach(grade -> {
            course.getGrades().add(grade.getGradeLevel());
        });

        //Subjects
        instance.getSubjects().forEach(subject -> {
            course.getSubjects().add(StringUtils.isNotBlank(subject.getSubject()) ? subject.getSubject() : null);
            course.getSubjectCodes().add(StringUtils.isNotBlank(subject.getSubjectCode()) ? subject.getSubjectCode() : null);
        });

        //School Year - AcademicSession Ref
        course.setSchoolYear(MappingUtil.buildGUIDRef("academicSessions", instance.getAcademicSessionId(), GUIDType.academicSession));

        //Org - School
        course.setOrg(MappingUtil.buildGUIDRef("schools", instance.getOrgId(), GUIDType.org));

        return course;
    }

    private Metadata mapMetadata(CourseView instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}