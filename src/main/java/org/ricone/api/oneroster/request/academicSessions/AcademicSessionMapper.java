package org.ricone.api.oneroster.request.academicSessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.oneroster.QAcademicSession;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component("OneRoster:AcademicSessions:AcademicSessionMapper")
class AcademicSessionMapper extends BaseMapper<QAcademicSession, AcademicSession, AcademicSessionsResponse, AcademicSessionResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    AcademicSessionMapper() throws NoSuchMethodException {
        super(QAcademicSession.class, AcademicSession.class, AcademicSessionsResponse.class, AcademicSessionResponse.class);
    }

    @Override protected AcademicSession map(QAcademicSession instance) {
        AcademicSession academicSession = new AcademicSession();
        academicSession.setSourcedId(instance.getSourcedId());
        academicSession.setStatus(StatusType.valueOf(instance.getStatus()));
        academicSession.setDateLastModified(instance.getDateLastModified().atZone(ZoneId.systemDefault()));
        academicSession.setMetadata(mapMetadata(instance));

        academicSession.setTitle(instance.getTitle());
        academicSession.setType(SessionType.valueOf(instance.getType()));

        //Parent & Child Relationships
        if(instance.getAcademicSession() != null) {
            academicSession.setParent(MappingUtil.buildGUIDRef("academicSessions", instance.getAcademicSession().getSourcedId(), GUIDType.academicSession));
        }
        instance.getChildren().forEach(child -> {
            academicSession.getChildren().add(MappingUtil.buildGUIDRef("terms", child.getChild().getSourcedId(), GUIDType.academicSession));
        });

        if(instance.getSchoolYear() != null) {
            academicSession.setSchoolYear(instance.getSchoolYear());
        }

        academicSession.setStartDate(instance.getStartDate());
        academicSession.setEndDate(instance.getEndDate());
        return academicSession;
    }

    @Override protected Metadata mapMetadata(QAcademicSession instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}