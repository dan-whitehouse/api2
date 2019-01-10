package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:AcademicSessions:CalendarMapper")
class CalendarMapper {
    CalendarMapper() {
    }

    AcademicSessionsResponse convert(List<SchoolCalendarWrapper> instance) {
        List<AcademicSession> list = new ArrayList<>();
        for (SchoolCalendarWrapper wrapper : instance) {
            AcademicSession user = map(wrapper.getSchoolCalendar(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        AcademicSessionsResponse response = new AcademicSessionsResponse();
        response.setAcademicSessions(list);
        return response;
    }

    AcademicSessionResponse convert(SchoolCalendarWrapper wrapper) {
        if(wrapper != null) {
            AcademicSessionResponse response = new AcademicSessionResponse();
            response.setAcademicSession(map(wrapper.getSchoolCalendar(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private AcademicSession map(SchoolCalendar instance, String districtId) {
        AcademicSession academicSession = new AcademicSession();
        academicSession.setSourcedId(instance.getSchoolCalendarRefId());
        academicSession.setStatus(StatusType.active);
        academicSession.setDateLastModified(null);
        academicSession.setMetadata(mapMetadata(instance, districtId));

        academicSession.setTitle(instance.getCalendarDescription()); //TODO: Not sure if this is correct, what goes here?
        academicSession.setType(SessionType.schoolYear); //TODO: I think this should be schoolYear, but I'm not sure.

        if(StringUtils.isNotBlank(instance.getCalendarYear())) {
            academicSession.setSchoolYear(instance.getCalendarYear());
        }

        /*if(instance.getBeginDate() != null) {
            //TODO: Would have to figure this programmatically.
            academicSession.setStartDate(instance.getBeginDate().toString());
        }

        if(instance.getEndDate() != null) {
            //TODO: Would have to figure this programmatically.
            academicSession.setEndDate(instance.getEndDate().toString());
        }*/

        instance.getSchoolCalendarSessions().forEach(schoolCalendarSession -> {
            academicSession.getChildren().add(MappingUtil.buildGUIDRef("terms", schoolCalendarSession.getSchoolCalendarSessionRefId(), GUIDType.org));
        });


        return academicSession;
    }

    private Metadata mapMetadata(SchoolCalendar instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSchoolCalendarSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}