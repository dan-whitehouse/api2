package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.ricone.api.xpress.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
class AcademicSessionController extends BaseController {
	@Autowired
	private AcademicSessionService service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/academicSessions/{id}")
	AcademicSessionResponse getAcademicSession(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getAcademicSession(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/academicSessions")
	AcademicSessionsResponse getAllAcademicSessions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllAcademicSessions(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms/{id}")
	AcademicSessionResponse getTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTerm(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms")
	AcademicSessionsResponse getAllTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllTerms(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods/{id}")
	AcademicSessionResponse getGradingPeriod(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getGradingPeriod(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods")
	AcademicSessionsResponse getAllGradingPeriods(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllGradingPeriods(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/gradingPeriods")
	AcademicSessionsResponse getGradingPeriodsForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getGradingPeriodsForTerm(getMetaData(request, response), id);
	}
}
