package org.ricone.api.oneroster.request.enrollments;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Enrollments:EnrollmentController")
@Api(value = "Enrollment", description = "One Roster - Enrollments", tags = {"Enrollment"})
class EnrollmentController extends BaseController {
	@Autowired
	private EnrollmentService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/enrollments/{id}")
	@ApiOperation(value = "getEnrollment", tags = {"Enrollment"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = EnrollmentResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	EnrollmentResponse getEnrollment(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getEnrollment(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/enrollments")
	@ApiOperation(value = "getAllEnrollments", tags = {"Enrollment"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = EnrollmentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	EnrollmentsResponse getAllEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllEnrollments(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/enrollments")
	@ApiOperation(value = "getEnrollmentsForSchool", tags = {"Enrollment"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = EnrollmentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	EnrollmentsResponse getEnrollmentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		//TODO - Implement
		return null;
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/enrollments")
	@ApiOperation(value = "getEnrollmentsForClassInSchool", tags = {"Enrollment"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = EnrollmentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	EnrollmentsResponse getEnrollmentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		//TODO - Implement
		return null;
	}
}
