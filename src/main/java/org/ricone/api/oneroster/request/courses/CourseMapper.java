package org.ricone.api.oneroster.request.courses;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QCourse;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Component("OneRoster:Courses:CourseMapper")
class CourseMapper extends BaseMapper<QCourse, Course, CoursesResponse, CourseResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    CourseMapper() throws NoSuchMethodException {
        super(QCourse.class, Course.class, CoursesResponse.class, CourseResponse.class);
    }

    @Override protected Course map(QCourse instance) {
        Course course = new Course();
        course.setSourcedId(instance.getSourcedId());
        course.setStatus(StatusType.valueOf(instance.getStatus()));
        course.setDateLastModified(instance.getDateLastModified().atZone(ZoneId.systemDefault()));
        course.setMetadata(mapMetadata(instance));

        course.setTitle(instance.getTitle());
        course.setCourseCode(instance.getCourseCode());

        //Grades
        if(StringUtils.isNotBlank(instance.getGrades())) {
            List<String> grades = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getGrades(), ","));
            grades.forEach(grade -> {
                course.getGrades().add(grade);
            });
        }

        //Subjects
        if(StringUtils.isNotBlank(instance.getSubjects())) {
            List<String> subjects = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getSubjects(), ","));
            subjects.forEach(subject -> {
                course.getSubjects().add(StringUtils.isNotBlank(subject) ? subject : null);
            });
        }

        //School Year - AcademicSession Ref
        /*  TODO: There is an issue with SIF districts, as they have no full year calendar.
         * So the query will return multiple rows, where the only difference is the getAcademicSessionId.
         * Because of they hibernate works, the same id will be displayed for all records.
         * See: https://stackoverflow.com/questions/10564832/hibernate-query-gives-same-record-multiple-times
         *      https://getyouralgorithm.blogspot.com/2013/07/hibernate-getresultlist-return-same-row.html
         */

        if(instance.getAcademicSession() != null) {
            course.setSchoolYear(MappingUtil.buildGUIDRef("academicSessions", instance.getAcademicSession().getSourcedId(), GUIDType.academicSession));
        }

        //Org - School
        if(instance.getOrg() != null) {
            course.setOrg(MappingUtil.buildGUIDRef("schools", instance.getOrg().getSourcedId(), GUIDType.org));
        }
        return course;
    }

    @Override protected Metadata mapMetadata(QCourse instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}