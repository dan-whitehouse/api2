package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:AcademicSessions:AcademicSessionMapper")
class AcademicSessionMapper {
    AcademicSessionMapper() {
    }

    AcademicSessionsResponse convert(List<AcademicSessionView> instance) {
        List<AcademicSession> list = new ArrayList<>();
        for (AcademicSessionView wrapper : instance) {
            AcademicSession academicSession = map(wrapper, null);
            if(academicSession != null) {
                list.add(academicSession);
            }
        }

        AcademicSessionsResponse response = new AcademicSessionsResponse();
        response.setAcademicSessions(list);
        return response;
    }

    AcademicSessionResponse convert(AcademicSessionView wrapper) {
        if(wrapper != null) {
            AcademicSessionResponse response = new AcademicSessionResponse();
            response.setAcademicSession(map(wrapper, null));
            return response;
        }
        return null;
    }

    private AcademicSession map(AcademicSessionView instance, String districtId) {
        AcademicSession academicSession = new AcademicSession();
        academicSession.setSourcedId(instance.getSourcedId());
        academicSession.setStatus(StatusType.active);
        academicSession.setDateLastModified(null);
        academicSession.setMetadata(mapMetadata(instance, districtId));
        academicSession.setTitle(instance.getTitle()); //TODO: Not correct, what goes here?
        academicSession.setType(SessionType.valueOf(instance.getType()));
        academicSession.setParent(MappingUtil.buildGUIDRef("academicSessions", instance.getAcademicSessionId(), GUIDType.academicSession));

        instance.getChildren().forEach(child -> {
            academicSession.getChildren().add(MappingUtil.buildGUIDRef("terms", child.getChildId(), GUIDType.academicSession));
        });

        academicSession.setSchoolYear(instance.getSchoolYear());
        academicSession.setStartDate(instance.getBeginDate().toString());
        academicSession.setEndDate(instance.getEndDate().toString());
        return academicSession;
    }

    private Metadata mapMetadata(AcademicSessionView instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}