package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.AcademicSession;

import java.util.List;

interface AcademicSessionDAO {

	AcademicSessionView getAcademicSession(ControllerData metadata, String refId) throws Exception;

	List<AcademicSessionView> getAllAcademicSessions(ControllerData metadata) throws Exception;

	AcademicSessionView getCalendar(ControllerData metadata, String refId) throws Exception;

	List<AcademicSessionView> getAllCalendars(ControllerData metadata) throws Exception;

	AcademicSessionView getTerm(ControllerData metadata, String refId) throws Exception;

	List<AcademicSessionView> getAllTerms(ControllerData metadata) throws Exception;

	int countAllAcademicSessions(ControllerData metadata) throws Exception;

	int countAllCalendars(ControllerData metadata) throws Exception;

	int countAllTerms(ControllerData metadata) throws Exception;
}