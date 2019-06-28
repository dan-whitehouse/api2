package org.ricone.api.xpress.request.xCalendar;

import org.ricone.api.core.model.CalendarEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.XCalendar;
import org.ricone.api.xpress.model.XCalendars;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XCalendars:XCalendarEventLogMapper")
public class XCalendarEventLogMapper {
    private final XCalendarMapper mapper;

    public XCalendarEventLogMapper(XCalendarMapper mapper) {
        this.mapper = mapper;
    }

    public XCalendarsResponse convert(List<EventLogWrapper<CalendarEventLog>> instance) throws MappingException {
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

    private XCalendar map(CalendarEventLog eventLog, String districtId) throws MappingException {
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