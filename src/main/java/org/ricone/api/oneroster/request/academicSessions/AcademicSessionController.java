package org.ricone.api.oneroster.request.academicSessions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.*;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:AcademicSessions:AcademicSessionController")
@Api(value = "AcademicSession", description = "One Roster - Academic Sessions", tags = {"AcademicSession"})
class AcademicSessionController extends BaseController {
	@Autowired
	private AcademicSessionService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/academicSessions/{id}")
	@SwaggerOperation.AcademicSessions.GetAcademicSession
	@SwaggerResponse.AcademicSessionResponse
	AcademicSessionResponse getAcademicSession(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @FieldParam String fields) throws Exception {
		return service.getAcademicSession(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/academicSessions")
	@SwaggerOperation.AcademicSessions.GetAllAcademicSessions
	@SwaggerResponse.AcademicSessionsResponse
	AcademicSessionsResponse getAllAcademicSessions(HttpServletRequest request, HttpServletResponse response, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getAllAcademicSessions(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}")
	@SwaggerOperation.AcademicSessions.GetTerm
	@SwaggerResponse.AcademicSessionResponse
	AcademicSessionResponse getTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @FieldParam String fields) throws Exception {
		return service.getTerm(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms")
	@SwaggerOperation.AcademicSessions.GetAllTerms
	@SwaggerResponse.AcademicSessionsResponse
	AcademicSessionsResponse getAllTerms(HttpServletRequest request, HttpServletResponse response, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getAllTerms(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods/{id}")
	@SwaggerOperation.AcademicSessions.GetGradingPeriod
	@SwaggerResponse.AcademicSessionResponse
	AcademicSessionResponse getGradingPeriod(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getGradingPeriod(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods")
	@SwaggerOperation.AcademicSessions.GetAllGradingPeriods
	@SwaggerResponse.AcademicSessionsResponse
	AcademicSessionsResponse getAllGradingPeriods(HttpServletRequest request, HttpServletResponse response, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getAllGradingPeriods(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/gradingPeriods")
	@SwaggerOperation.AcademicSessions.GetGradingPeriodsForTerm
	@SwaggerResponse.AcademicSessionsResponse
	AcademicSessionsResponse getGradingPeriodsForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getGradingPeriodsForTerm(getMetaData(request, response), id);
	}
}
