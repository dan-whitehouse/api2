package org.ricone.api.oneroster.request.classes;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;
import org.ricone.api.core.model.CourseIdentifier;
import org.ricone.api.core.model.CourseSection;
import org.ricone.api.core.model.CourseSectionSchedule;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Component("OneRoster:Classes:ClassMapper")
class ClassMapper {
    ClassMapper() {
    }

    ClassesResponse convert(List<CourseSectionWrapper> instance) {
        List<Class> list = new ArrayList<>();
        for (CourseSectionWrapper wrapper : instance) {
            Class clazz = map(wrapper.getCourseSection(), wrapper.getDistrictId());
            if(clazz != null) {
                list.add(clazz);
            }
        }

        ClassesResponse response = new ClassesResponse();
        response.setClass_(list);
        return response;
    }

    ClassResponse convert(CourseSectionWrapper wrapper) {
        if(wrapper != null) {
            ClassResponse response = new ClassResponse();
            response.setClass_(map(wrapper.getCourseSection(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Class map(CourseSection instance, String districtId) {
        Class clazz = new Class();
        clazz.setSourcedId(instance.getCourseSectionRefId());
        clazz.setStatus(StatusType.active);
        clazz.setDateLastModified(null);
        clazz.setMetadata(mapMetadata(instance, districtId));

        clazz.setTitle(instance.getCourse().getTitle());
        clazz.setClassType(ClassType.scheduled);
        clazz.setLocation(mapCurrentLocation(instance.getCourseSectionSchedules()));

        if(instance.getCourse() != null) {
            //Class Code
            clazz.setClassCode(mapIdentifier(instance.getCourse().getCourseIdentifiers()));

            //Grades
            if(CollectionUtils.isNotEmpty(instance.getCourse().getCourseGrades())) {
                instance.getCourse().getCourseGrades().forEach(courseGrade -> {
                    clazz.getGrades().add(courseGrade.getGradeLevelCode());
                });
            }

            //Subjects & Subject Codes
            if(StringUtils.isNotBlank(instance.getCourse().getSubjectCode())) {
                clazz.getSubjects().add(instance.getCourse().getSubjectCode());
                //clazz.getSubjectCodes().add(instance.getCourse().getSubjectCode()); //TODO - I don't think we have these...
            }

            //Course & School GUIDRefs
            clazz.setCourse(MappingUtil.buildGUIDRef("courses", instance.getCourse().getCourseRefId(), GUIDType.course));
            if(instance.getCourse().getSchool() != null) {
                clazz.setSchool(MappingUtil.buildGUIDRef("schools", instance.getCourse().getSchool().getSchoolRefId(), GUIDType.org));
            }
        }

        //Periods
        if(CollectionUtils.isNotEmpty(instance.getCourseSectionSchedules())) {
            Set<String> periods = new HashSet<>();
            instance.getCourseSectionSchedules().forEach(courseSectionSchedule -> {
                if(StringUtils.isNotBlank(courseSectionSchedule.getClassPeriod())) {
                    periods.add(courseSectionSchedule.getClassPeriod());
                }
            });

            if(CollectionUtils.isNotEmpty(periods)) {
                periods.forEach(period -> {
                    clazz.getPeriods().add(period);
                });
            }
        }

        //Terms
        instance.getCourseSectionSchedules().forEach(courseSectionSchedule -> {
            //TODO - IDK if this is correct
            clazz.getTerms().add(MappingUtil.buildGUIDRef("terms", courseSectionSchedule.getSchoolCalendarSession().getSchoolCalendarSessionRefId(), GUIDType.term));
        });

        if(instance.getSchoolCalendarSession() != null) {
            clazz.getTerms().add(MappingUtil.buildGUIDRef("academicSessions", instance.getSchoolCalendarSession().getSchoolCalendar().getSchoolCalendarRefId(), GUIDType.academicSession));
        }

        //Resources
        //clazz.setResources();

        return clazz;
    }

    private Metadata mapMetadata(CourseSection instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getCourseSectionSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }

    private String mapIdentifier(Set<CourseIdentifier> courseIdentifiers) {
        Optional<CourseIdentifier> id = courseIdentifiers.stream().filter(ci -> StringUtils.equalsIgnoreCase(ci.getIdentificationSystemCode(), "School")).findFirst();
        return id.map(CourseIdentifier::getCourseId).orElse(null);
    }

    private String mapCurrentLocation(Set<CourseSectionSchedule> courseSectionSchedules) {
        Optional<CourseSectionSchedule> courseSectionSchedule = courseSectionSchedules.stream().filter(css -> isWithinRange(css.getSchoolCalendarSession().getBeginDate(), css.getSchoolCalendarSession().getEndDate())).findFirst();
        return courseSectionSchedule.map(CourseSectionSchedule::getClassroomIdentifier).orElse(null);
    }

    private boolean isWithinRange(Date startDate, Date endDate) {
        if(startDate != null && endDate != null) {
            LocalDate now = LocalDate.now();
            LocalDate start = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

            if(now.equals(start) || now.equals(end)) {
                return true;
            }
            else return now.isAfter(start) && now.isBefore(end);
        }
        return false;
    }
}