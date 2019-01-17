package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface TermDAO {
	SchoolCalendarSessionWrapper getTerm(ControllerData metadata, String refId);

	List<SchoolCalendarSessionWrapper> getAllTerms(ControllerData metadata);
}