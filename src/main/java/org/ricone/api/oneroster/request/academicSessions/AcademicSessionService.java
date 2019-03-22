package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;

interface AcademicSessionService {
	AcademicSessionResponse getAcademicSession(RequestData metadata, String refId) throws Exception;

	AcademicSessionsResponse getAllAcademicSessions(RequestData metadata) throws Exception;

	AcademicSessionResponse getTerm(RequestData metadata, String refId) throws Exception;

	AcademicSessionsResponse getAllTerms(RequestData metadata) throws Exception;

	AcademicSessionResponse getGradingPeriod(RequestData metadata, String refId) throws Exception;

	AcademicSessionsResponse getAllGradingPeriods(RequestData metadata) throws Exception;

	AcademicSessionsResponse getGradingPeriodsForTerm(RequestData metadata, String refId) throws Exception;
}