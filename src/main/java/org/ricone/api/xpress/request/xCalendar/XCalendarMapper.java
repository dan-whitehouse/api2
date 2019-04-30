package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.MappingException;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XPress:XCalendars:XCalendarMapper")
public class XCalendarMapper {

    public XCalendarMapper() {
    }

    public XCalendarsResponse convert(List<SchoolCalendarWrapper> instance) {
        List<XCalendar> list = new ArrayList<>();
        for (SchoolCalendarWrapper wrapper : instance) {
            XCalendar xCalendar = map(wrapper.getSchoolCalendar(), wrapper.getDistrictId());
            if(xCalendar != null) {
                list.add(xCalendar);
            }
        }

        XCalendarsResponse response = new XCalendarsResponse();
        XCalendars xCalendars = new XCalendars();
        xCalendars.setXCalendar(list);

        response.setXCalendars(xCalendars);
        return response;
    }

    public XCalendarResponse convert(SchoolCalendarWrapper instance) {
        XCalendarResponse response = new XCalendarResponse();
        XCalendar xCalendar = map(instance.getSchoolCalendar(), instance.getDistrictId());
        if(xCalendar != null) {
            response.setXCalendar(xCalendar);
        }
        return response;
    }

    public XCalendar map(SchoolCalendar instance, String districtId) {
        try {
            XCalendar xCalendar = new XCalendar();
            xCalendar.setDistrictId(districtId); //Required by Filtering
            xCalendar.setRefId(instance.getSchoolCalendarRefId());
            xCalendar.setSchoolRefId(instance.getSchool().getSchoolRefId());
            xCalendar.setSchoolYear(instance.getCalendarYear());

            //Sessions
            List<SessionList> sessionsList = new ArrayList<>();
            for (SchoolCalendarSession calendarSession : instance.getSchoolCalendarSessions()) {
                SessionList sessionList = mapSessionList(calendarSession);
                if(sessionList != null) {
                    sessionsList.add(sessionList);
                }
            }

            if(CollectionUtils.isNotEmpty(sessionsList)) {
                Sessions sessions = new Sessions();
                sessions.setSessionList(sessionsList);
                xCalendar.setSessions(sessions);
            }

            //Metadata
            xCalendar.setMetadata(mapMetadata(instance));

            return xCalendar;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new MappingException("Mapping Exception: " + ex.getLocalizedMessage());
        }
    }

    private SessionList mapSessionList(SchoolCalendarSession calendarSession) {
        SessionList sessionList = new SessionList();
        sessionList.setDescription(calendarSession.getDescription());
        sessionList.setSessionCode(calendarSession.getCode());
        sessionList.setSessionType(calendarSession.getSessionTypeCode());
        sessionList.setLinkedSessionCode(calendarSession.getLinkedSessionCode());

        sessionList.setMarkingTerm(BooleanUtils.toStringTrueFalse(calendarSession.getMarkingTermIndicator()));
        sessionList.setSchedulingTerm(BooleanUtils.toStringTrueFalse(calendarSession.getSchedulingTermIndicator()));

        if(calendarSession.getBeginDate() != null) {
            sessionList.setStartDate(DateFormatUtils.format(calendarSession.getBeginDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        }
        if(calendarSession.getEndDate() != null) {
            sessionList.setEndDate(DateFormatUtils.format(calendarSession.getEndDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        }

        if(sessionList.isEmptyObject()) {
            return null;
        }
        return sessionList;
    }

    private Metadata mapMetadata(SchoolCalendar schoolCalendar) {
        return new Metadata(schoolCalendar.getSchoolCalendarSchoolYear());
    }
}