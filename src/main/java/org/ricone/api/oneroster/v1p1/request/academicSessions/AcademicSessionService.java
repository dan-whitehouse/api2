package org.ricone.api.oneroster.v1p1.request.academicSessions;

import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;

interface AcademicSessionService {
	AcademicSessionResponse getAcademicSession(ControllerData metadata, String refId) throws Exception;

	AcademicSessionsResponse getAllAcademicSessions(ControllerData metadata) throws Exception;

	AcademicSessionResponse getTerm(ControllerData metadata, String refId) throws Exception;

	AcademicSessionsResponse getAllTerms(ControllerData metadata) throws Exception;

	AcademicSessionResponse getGradingPeriod(ControllerData metadata, String refId) throws Exception;

	AcademicSessionsResponse getAllGradingPeriods(ControllerData metadata) throws Exception;

	AcademicSessionsResponse getGradingPeriodsForTerm(ControllerData metadata, String refId) throws Exception;
}