package org.ricone.api.oneroster.request.courses;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.CourseView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Courses:CourseMapper")
class CourseMapper extends BaseMapper<CourseView, Course, CoursesResponse, CourseResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    CourseMapper() throws NoSuchMethodException {
        super(CourseView.class, Course.class, CoursesResponse.class, CourseResponse.class);
    }

    @Override protected Course map(CourseView instance) {
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
        /*  TODO: There is an issue with SIF districts, as they have no full year calendar.
         * So the query will return multiple rows, where the only difference is the getAcademicSessionId.
         * Because of they hibernate works, the same id will be displayed for all records.
         * See: https://stackoverflow.com/questions/10564832/hibernate-query-gives-same-record-multiple-times
         *      https://getyouralgorithm.blogspot.com/2013/07/hibernate-getresultlist-return-same-row.html
         */
        course.setSchoolYear(MappingUtil.buildGUIDRef("academicSessions", instance.getAcademicSessionId(), GUIDType.academicSession));

        //Org - School
        course.setOrg(MappingUtil.buildGUIDRef("schools", instance.getOrgId(), GUIDType.org));

        return course;
    }

    @Override protected Metadata mapMetadata(CourseView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}