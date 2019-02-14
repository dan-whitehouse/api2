package org.ricone.api.oneroster.request.enrollments;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
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
	EnrollmentResponse getEnrollment(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getEnrollment(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/enrollments")
	@ApiOperation(value = "getAllEnrollments", tags = {"Enrollment"})
	EnrollmentsResponse getAllEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllEnrollments(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/enrollments")
	@ApiOperation(value = "getEnrollmentsForSchool", tags = {"Enrollment"})
	EnrollmentsResponse getEnrollmentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		//TODO - Implement
		return null;
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/enrollments")
	@ApiOperation(value = "getEnrollmentsForClassInSchool", tags = {"Enrollment"})
	EnrollmentsResponse getEnrollmentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		//TODO - Implement
		return null;
	}
}
