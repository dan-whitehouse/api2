package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.request.users.UserService;
import org.ricone.api.xpress.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@RestController
public class EnrollmentController extends BaseController {
	@Autowired
	private EnrollmentService service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/enrollments/{id}")
	public EnrollmentResponse getEnrollment(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getEnrollment(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/enrollments")
	public EnrollmentsResponse getAllEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllEnrollments(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/enrollments")
	public EnrollmentsResponse getEnrollmentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/enrollments")
	public EnrollmentsResponse getEnrollmentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return null;
	}
}
