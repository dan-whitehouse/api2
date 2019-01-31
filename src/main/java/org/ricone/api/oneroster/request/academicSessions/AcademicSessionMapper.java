package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:AcademicSessions:AcademicSessionMapper")
class AcademicSessionMapper extends BaseMapper<AcademicSessionView, AcademicSession, AcademicSessionsResponse, AcademicSessionResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    AcademicSessionMapper() throws NoSuchMethodException {
        super(AcademicSessionView.class, AcademicSession.class, AcademicSessionsResponse.class, AcademicSessionResponse.class);
    }

    @Override protected AcademicSession map(AcademicSessionView instance) {
        AcademicSession academicSession = new AcademicSession();
        academicSession.setSourcedId(instance.getSourcedId());
        academicSession.setStatus(StatusType.active);
        academicSession.setDateLastModified(null);
        academicSession.setMetadata(mapMetadata(instance));

        academicSession.setTitle(instance.getTitle());
        academicSession.setType(SessionType.valueOf(instance.getType()));

        academicSession.setParent(MappingUtil.buildGUIDRef("academicSessions", instance.getParentId(), GUIDType.academicSession));
        instance.getChildren().forEach(child -> {
            academicSession.getChildren().add(MappingUtil.buildGUIDRef("terms", child.getChildId(), GUIDType.academicSession));
        });

        academicSession.setSchoolYear(instance.getSchoolYear());
        academicSession.setStartDate(instance.getBeginDate().toString());
        academicSession.setEndDate(instance.getEndDate().toString());
        return academicSession;
    }

    @Override protected Metadata mapMetadata(AcademicSessionView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}