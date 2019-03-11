package org.ricone.api.oneroster.v1p1.request.classes;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QClass;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("OneRoster2:Classes:ClassMapper")
class ClassMapper extends BaseMapper<QClass, Class, ClassesResponse, ClassResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    ClassMapper() throws NoSuchMethodException {
        super(QClass.class, Class.class, ClassesResponse.class, ClassResponse.class);
    }

    @Override protected Class map(QClass instance) {
        Class clazz = new Class();
        clazz.setSourcedId(instance.getSourcedId());
        clazz.setStatus(StatusType.active);
        clazz.setDateLastModified(null);
        clazz.setMetadata(mapMetadata(instance));

        clazz.setTitle(instance.getTitle());
        clazz.setClassType(ClassType.valueOf(instance.getClassType())); //TODO - We have no way to determine if the class is a homeroom.
        clazz.setLocation(instance.getLocation());
        clazz.setClassCode(instance.getClassCode());

        //TODO: Grades
        /*List<String> grades = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getGrades(), ","));
        grades.forEach(grade -> {
            clazz.getGrades().add(grade);
        });*/

        //TODO: Subjects
       /* List<String> subjects = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getSubjects(), ","));
        subjects.forEach(subject -> {
            clazz.getSubjects().add(StringUtils.isNotBlank(subject) ? subject : null);
        });*/

        //TODO: SubjectCodes
        /*List<String> subjectCodes = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getSubjectCodes(), ","));
        subjectCodes.forEach(subjectCode -> {
            clazz.getSubjectCodes().add(StringUtils.isNotBlank(subjectCode) ? subjectCode : null);
        });*/

        //TODO: Periods
        /*List<String> periods = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getPeriods(), ","));
        periods.forEach(period -> {
            clazz.getPeriods().add(period);
        });*/


        //Course GUIDRefs
        if(instance.getCourse() != null) {
            clazz.setCourse(MappingUtil.buildGUIDRef("courses", instance.getCourse().getSourcedId(), GUIDType.course));
        }

        //School GUIDRefs
        if(instance.getOrg() != null) {
            clazz.setSchool(MappingUtil.buildGUIDRef("schools", instance.getOrg().getSourcedId(), GUIDType.org));
        }

        //Terms GUIDRefs
        instance.getTerms().forEach(term -> {
            if(term.getAcademicSession() != null) {
                clazz.getTerms().add(MappingUtil.buildGUIDRef("terms", term.getAcademicSession().getSourcedId(), GUIDType.valueOf(term.getAcademicSession().getType())));
            }
        });

        //Resources
        //clazz.setResources();

        return clazz;
    }

    @Override protected Metadata mapMetadata(QClass instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}