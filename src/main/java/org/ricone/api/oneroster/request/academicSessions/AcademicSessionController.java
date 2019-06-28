package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.Swagger;
import org.ricone.api.oneroster.component.springfox.SwaggerParam;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:AcademicSessions:AcademicSessionController")
@Swagger.Controller.AcademicSession
class AcademicSessionController extends BaseController {
	private final AcademicSessionService service;

	public AcademicSessionController(AcademicSessionService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/academicSessions/{id}")
	@Swagger.Operation.AcademicSession.GetAcademicSession /**/ @Swagger.Response.AcademicSession
	AcademicSessionResponse getAcademicSession(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getAcademicSession(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/academicSessions")
	@Swagger.Operation.AcademicSession.GetAllAcademicSessions /**/ @Swagger.Response.AcademicSessions
	AcademicSessionsResponse getAllAcademicSessions(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllAcademicSessions(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}")
	@Swagger.Operation.AcademicSession.GetTerm /**/ @Swagger.Response.AcademicSession
	AcademicSessionResponse getTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getTerm(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms")
	@Swagger.Operation.AcademicSession.GetAllTerms /**/ @Swagger.Response.AcademicSessions
	AcademicSessionsResponse getAllTerms(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllTerms(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods/{id}")
	@Swagger.Operation.AcademicSession.GetGradingPeriod /**/ @Swagger.Response.AcademicSession
	AcademicSessionResponse getGradingPeriod(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getGradingPeriod(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods")
	@Swagger.Operation.AcademicSession.GetAllGradingPeriods /**/ @Swagger.Response.AcademicSessions
	AcademicSessionsResponse getAllGradingPeriods(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllGradingPeriods(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/gradingPeriods")
	@Swagger.Operation.AcademicSession.GetGradingPeriodsForTerm /**/ @Swagger.Response.AcademicSessions
	AcademicSessionsResponse getGradingPeriodsForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getGradingPeriodsForTerm(getMetaData(request, response), id);
	}
}
