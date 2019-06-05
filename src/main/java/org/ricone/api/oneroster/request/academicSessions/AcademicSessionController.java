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
	private final AcademicSessionService service;

	public AcademicSessionController(AcademicSessionService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/academicSessions/{id}")
	@ApiOperation(value = "getAcademicSession", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionResponse getAcademicSession(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @FieldParam String fields) throws Exception {
		return service.getAcademicSession(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/academicSessions")
	@ApiOperation(value = "getAllAcademicSessions", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionsResponse getAllAcademicSessions(HttpServletRequest request, HttpServletResponse response, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getAllAcademicSessions(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}")
	@ApiOperation(value = "getTerm", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionResponse getTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @FieldParam String fields) throws Exception {
		return service.getTerm(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms")
	@ApiOperation(value = "getAllTerms", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionsResponse getAllTerms(HttpServletRequest request, HttpServletResponse response, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getAllTerms(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods/{id}")
	@ApiOperation(value = "getGradingPeriod", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionResponse getGradingPeriod(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getGradingPeriod(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods")
	@ApiOperation(value = "getAllGradingPeriods", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionsResponse getAllGradingPeriods(HttpServletRequest request, HttpServletResponse response, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getAllGradingPeriods(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/gradingPeriods")
	@ApiOperation(value = "getGradingPeriodsForTerm", tags = {"AcademicSession"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = AcademicSessionsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	AcademicSessionsResponse getGradingPeriodsForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @PagingLimitParam String limit, @PagingOffsetParam String offset, @SortParam String sort, @OrderByParam String orderBy, @FilterParam String filter, @FieldParam String fields) throws Exception {
		return service.getGradingPeriodsForTerm(getMetaData(request, response), id);
	}
}
