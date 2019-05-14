package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.MappingException;
import org.ricone.api.core.model.CalendarEventLog;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XPress:XCalendars:XCalendarEventLogMapper")
public class XCalendarEventLogMapper {
    @Autowired private XCalendarMapper mapper;

    public XCalendarEventLogMapper() {
    }

    public XCalendarsResponse convert(List<EventLogWrapper<CalendarEventLog>> instance) {
        List<XCalendar> list = new ArrayList<>();
        for (EventLogWrapper<CalendarEventLog> wrapper : instance) {
            XCalendar xCalendar = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xCalendar != null) {
                list.add(xCalendar);
            }
        }

        XCalendarsResponse response = new XCalendarsResponse();
        XCalendars xCalendars = new XCalendars();
        xCalendars.setXCalendar(list);

        response.setXCalendars(xCalendars);
        return response;
    }

    private XCalendar map(CalendarEventLog eventLog, String districtId) {
        XCalendar instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XCalendar();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getSchoolCalendar(), districtId);
            if(instance == null) {
                instance = new XCalendar();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}