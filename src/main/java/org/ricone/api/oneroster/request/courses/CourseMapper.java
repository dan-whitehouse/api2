package org.ricone.api.oneroster.request.courses;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.view.CourseView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Courses:CourseMapper")
class CourseMapper extends BaseMapper {
    CourseMapper() {
    }

    CoursesResponse convert(List<CourseView> instance, ControllerData metadata) {
        List<Course> list = new ArrayList<>();
        for (CourseView view : instance) {
            Course org = map(view);
            if(org != null) {
                list.add(org);
            }
        }
        return new CoursesResponse(list, mapErrors(metadata, CourseView.class, Course.class));
    }

    CourseResponse convert(CourseView view, ControllerData metadata) {
        if(view != null) {
            return new CourseResponse(map(view), mapErrors(metadata, CourseView.class, Course.class));
        }
        return null;
    }

    private Course map(CourseView instance) {
        Course course = new Course();
        course.setSourcedId(instance.getSourcedId());
        course.setStatus(StatusType.active);
        course.setDateLastModified(null);
        course.setMetadata(mapMetadata(instance));

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

    private Metadata mapMetadata(CourseView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}