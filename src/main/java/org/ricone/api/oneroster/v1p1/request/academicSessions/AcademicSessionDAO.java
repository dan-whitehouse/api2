package org.ricone.api.oneroster.v1p1.request.academicSessions;

import org.ricone.api.core.model.v1p1.QAcademicSession;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface AcademicSessionDAO {

	QAcademicSession getAcademicSession(ControllerData metadata, String refId) throws Exception;

	List<QAcademicSession> getAllAcademicSessions(ControllerData metadata) throws Exception;

	QAcademicSession getCalendar(ControllerData metadata, String refId) throws Exception;

	List<QAcademicSession> getAllCalendars(ControllerData metadata) throws Exception;

	QAcademicSession getTerm(ControllerData metadata, String refId) throws Exception;

	List<QAcademicSession> getAllTerms(ControllerData metadata) throws Exception;

	int countAllAcademicSessions(ControllerData metadata) throws Exception;

	int countAllCalendars(ControllerData metadata) throws Exception;

	int countAllTerms(ControllerData metadata) throws Exception;
}