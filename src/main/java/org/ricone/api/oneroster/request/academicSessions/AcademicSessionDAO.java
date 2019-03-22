package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.core.model.v1p1.QAcademicSession;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface AcademicSessionDAO {

	QAcademicSession getAcademicSession(RequestData metadata, String refId) throws Exception;

	List<QAcademicSession> getAllAcademicSessions(RequestData metadata) throws Exception;

	QAcademicSession getCalendar(RequestData metadata, String refId) throws Exception;

	List<QAcademicSession> getAllCalendars(RequestData metadata) throws Exception;

	QAcademicSession getTerm(RequestData metadata, String refId) throws Exception;

	List<QAcademicSession> getAllTerms(RequestData metadata) throws Exception;

	int countAllAcademicSessions(RequestData metadata) throws Exception;

	int countAllCalendars(RequestData metadata) throws Exception;

	int countAllTerms(RequestData metadata) throws Exception;
}