package org.ricone.api.oneroster.request.classes;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.ClassView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

@Component("OneRoster:Classes:ClassMapper")
class ClassMapper extends BaseMapper<ClassView, Class, ClassesResponse, ClassResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    ClassMapper() throws NoSuchMethodException {
        super(ClassView.class, Class.class, ClassesResponse.class, ClassResponse.class);
    }

    @Override protected Class map(ClassView instance) {
        Class clazz = new Class();
        clazz.setSourcedId(instance.getSourcedId());
        clazz.setStatus(StatusType.active);
        clazz.setDateLastModified(null);
        clazz.setMetadata(mapMetadata(instance));

        clazz.setTitle(instance.getTitle());
        clazz.setClassType(ClassType.valueOf(instance.getClassType())); //TODO - We have no way to determine if the class is a homeroom.
        clazz.setLocation(instance.getLocation());
        clazz.setClassCode(instance.getClassCode());

        //Grades
        instance.getGrades().forEach(grade -> {
            clazz.getGrades().add(grade.getGradeLevel());
        });

        //Subjects
        instance.getSubjects().forEach(subject -> {
            clazz.getSubjects().add(StringUtils.isNotBlank(subject.getSubject()) ? subject.getSubject() : null);
            clazz.getSubjectCodes().add(StringUtils.isNotBlank(subject.getSubjectCode()) ? subject.getSubjectCode() : null);
        });

        //Course & School GUIDRefs
        clazz.setCourse(MappingUtil.buildGUIDRef("courses", instance.getCourseId(), GUIDType.course));
        clazz.setSchool(MappingUtil.buildGUIDRef("schools", instance.getOrgId(), GUIDType.org));

        //Periods
        instance.getPeriods().forEach(period -> {
            clazz.getPeriods().add(period.getPeriod());
        });

        //Terms
        instance.getTerms().forEach(term -> {
            clazz.getTerms().add(MappingUtil.buildGUIDRef("terms", term.getTermId(), GUIDType.valueOf(term.getType())));
        });

        //Resources
        //clazz.setResources();

        return clazz;
    }

    @Override protected Metadata mapMetadata(ClassView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}