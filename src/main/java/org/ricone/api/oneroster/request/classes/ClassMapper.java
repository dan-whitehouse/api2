package org.ricone.api.oneroster.request.classes;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.CourseIdentifier;
import org.ricone.api.core.model.CourseSection;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getCourseSectionSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        clazz.setMetadata(metadata);

        clazz.setTitle(instance.getCourse().getTitle());
        //clazz.setClassCode();
        clazz.setClassType(ClassType.scheduled);
        //clazz.setLocation();
        //clazz.setGrades();
        //clazz.setSubjects();
        //clazz.setSubjectCodes();
        clazz.setCourse(MappingUtil.buildGUIDRef("courses", instance.getCourse().getCourseRefId(), GUIDType.course));
        clazz.setSchool(MappingUtil.buildGUIDRef("schools", instance.getCourse().getSchool().getSchoolRefId(), GUIDType.org));
        //clazz.setPeriods();


        return clazz;
    }

    private String mapIdentifier(Set<CourseIdentifier> courseIdentifiers) {
        Optional<CourseIdentifier> id = courseIdentifiers.stream().filter(ci -> StringUtils.equalsIgnoreCase(ci.getIdentificationSystemCode(), "School")).findFirst();
        return id.map(CourseIdentifier::getCourseId).orElse(null);
    }
}