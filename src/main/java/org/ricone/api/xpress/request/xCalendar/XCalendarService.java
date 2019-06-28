package org.ricone.api.xpress.request.xCalendar;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;

public interface XCalendarService {
	XCalendarResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XCalendarsResponse findAll(ControllerData metadata) throws Exception;

	XCalendarsResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XCalendarsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;
}