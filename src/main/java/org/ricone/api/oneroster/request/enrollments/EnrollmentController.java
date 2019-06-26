package org.ricone.api.oneroster.request.enrollments;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.Swagger;
import org.ricone.api.oneroster.component.springfox.SwaggerParam;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Enrollments:EnrollmentController")
@Swagger.Controller.Enrollment
class EnrollmentController extends BaseController {
	private final EnrollmentService service;

	public EnrollmentController(EnrollmentService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/enrollments/{id}")
	@Swagger.Operation.Enrollment.GetEnrollment /**/ @Swagger.Response.Enrollment
	EnrollmentResponse getEnrollment(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getEnrollment(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/enrollments")
	@Swagger.Operation.Enrollment.GetAllEnrollments /**/ @Swagger.Response.Enrollments
	EnrollmentsResponse getAllEnrollments(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllEnrollments(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/enrollments")
	@Swagger.Operation.Enrollment.GetEnrollmentsForSchool /**/ @Swagger.Response.Enrollments
	EnrollmentsResponse getEnrollmentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getEnrollmentsForSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/enrollments")
	@Swagger.Operation.Enrollment.GetEnrollmentsForClassInSchool /**/ @Swagger.Response.Enrollments
	EnrollmentsResponse getEnrollmentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getEnrollmentsForClassInSchool(getMetaData(request, response), schoolId, classId);
	}
}
