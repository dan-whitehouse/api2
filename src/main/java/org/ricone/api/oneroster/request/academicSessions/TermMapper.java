package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("OneRoster:AcademicSessions:TermMapper")
class TermMapper {
    TermMapper() {
    }

    AcademicSessionsResponse convert(List<SchoolCalendarSessionWrapper> instance) {
        List<AcademicSession> list = new ArrayList<>();
        for (SchoolCalendarSessionWrapper wrapper : instance) {
            AcademicSession user = map(wrapper.getSchoolCalendarSession(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        AcademicSessionsResponse response = new AcademicSessionsResponse();
        response.setAcademicSessions(list);
        return response;
    }

    AcademicSessionResponse convert(SchoolCalendarSessionWrapper wrapper) {
        if(wrapper != null) {
            AcademicSessionResponse response = new AcademicSessionResponse();
            response.setAcademicSession(map(wrapper.getSchoolCalendarSession(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private AcademicSession map(SchoolCalendarSession instance, String districtId) {
        AcademicSession academicSession = new AcademicSession();
        academicSession.setSourcedId(instance.getSchoolCalendarSessionRefId());
        academicSession.setStatus(StatusType.active);
        academicSession.setDateLastModified(null);
        academicSession.setMetadata(mapMetadata(instance, districtId));

        academicSession.setTitle(instance.getSessionTypeCode() + " - " + instance.getDescription()); //TODO: Not correct, what goes here?
        academicSession.setType(SessionType.term);
        academicSession.setParent(MappingUtil.buildGUIDRef("academicSessions", instance.getSchoolCalendar().getSchoolCalendarRefId(), GUIDType.academicSession));

        if(instance.getSchoolCalendar() != null) {
            academicSession.setSchoolYear(instance.getSchoolCalendar().getCalendarYear());
        }

        if(instance.getBeginDate() != null) {
            academicSession.setStartDate(instance.getBeginDate().toString());
        }

        if(instance.getEndDate() != null) {
            academicSession.setEndDate(instance.getEndDate().toString());
        }
        return academicSession;
    }

    private Metadata mapMetadata(SchoolCalendarSession instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSchoolCalendarSessionSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}