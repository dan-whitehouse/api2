package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.AcademicSession;

import java.util.List;

interface AcademicSessionDAO {

	AcademicSessionView getAcademicSession(ControllerData metadata, String refId);

	List<AcademicSessionView> getAllAcademicSessions(ControllerData metadata);

	AcademicSessionView getCalendar(ControllerData metadata, String refId);

	List<AcademicSessionView> getAllCalendars(ControllerData metadata);

	AcademicSessionView getTerm(ControllerData metadata, String refId);

	List<AcademicSessionView> getAllTerms(ControllerData metadata);

	int countAllAcademicSessions(ControllerData metadata);

	int countAllCalendars(ControllerData metadata);

	int countAllTerms(ControllerData metadata);
}