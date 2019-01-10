package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

interface CalendarDAO {
	SchoolCalendarWrapper getCalendar(ControllerData metadata, String refId);

	List<SchoolCalendarWrapper> getAllCalendars(ControllerData metadata);
}