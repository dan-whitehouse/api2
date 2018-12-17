package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.oneroster.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@RestController
public class EnrollmentController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/enrollments/{id}")
	public EnrollmentResponse getEnrollment(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		Enrollment enrollment = new Enrollment();
		enrollment.setSourcedId(UUID.randomUUID().toString());
		enrollment.setStatus(StatusType.active);
		enrollment.setDateLastModified(Instant.now().toString());

		GUIDRef guidRefStudent = new GUIDRef();
		guidRefStudent.setHref("http://localhost:8080/ims/oneroster/v1p1/students/");
		guidRefStudent.setSourcedId(UUID.randomUUID().toString());
		guidRefStudent.setType(GUIDType.student);

		GUIDRef guidRefClass = new GUIDRef();
		guidRefClass.setHref("http://localhost:8080/ims/oneroster/v1p1/classes/");
		guidRefClass.setSourcedId(UUID.randomUUID().toString());
		guidRefClass.setType(GUIDType.student);

		GUIDRef guidRefSchool = new GUIDRef();
		guidRefSchool.setHref("http://localhost:8080/ims/oneroster/v1p1/schools/");
		guidRefSchool.setSourcedId(UUID.randomUUID().toString());
		guidRefSchool.setType(GUIDType.org);

		enrollment.setUser(guidRefStudent);
		enrollment.setClass_(guidRefClass);
		enrollment.setSchool(guidRefSchool);
		enrollment.setRole(RoleType.student);
		enrollment.setPrimary(true);
		enrollment.setBeginDate(Instant.now().toString());
		enrollment.setEndDate(Instant.now().toString());

		EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
		enrollmentResponse.setEnrollment(enrollment);

		return enrollmentResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/enrollments")
	public EnrollmentsResponse getAllEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Enrollment enrollment = new Enrollment();
		enrollment.setSourcedId(UUID.randomUUID().toString());
		enrollment.setStatus(StatusType.active);
		enrollment.setDateLastModified(Instant.now().toString());

		GUIDRef guidRefStudent = new GUIDRef();
		guidRefStudent.setHref("http://localhost:8080/ims/oneroster/v1p1/students/");
		guidRefStudent.setSourcedId(UUID.randomUUID().toString());
		guidRefStudent.setType(GUIDType.student);

		GUIDRef guidRefClass = new GUIDRef();
		guidRefClass.setHref("http://localhost:8080/ims/oneroster/v1p1/classes/");
		guidRefClass.setSourcedId(UUID.randomUUID().toString());
		guidRefClass.setType(GUIDType.clazz);

		GUIDRef guidRefSchool = new GUIDRef();
		guidRefSchool.setHref("http://localhost:8080/ims/oneroster/v1p1/schools/");
		guidRefSchool.setSourcedId(UUID.randomUUID().toString());
		guidRefSchool.setType(GUIDType.org);

		enrollment.setUser(guidRefStudent);
		enrollment.setClass_(guidRefClass);
		enrollment.setSchool(guidRefSchool);
		enrollment.setRole(RoleType.student);
		enrollment.setPrimary(true);
		enrollment.setBeginDate(Instant.now().toString());
		enrollment.setEndDate(Instant.now().toString());

		EnrollmentsResponse enrollmentsResponse = new EnrollmentsResponse();
		enrollmentsResponse.getEnrollments().add(enrollment);

		return enrollmentsResponse;
	}
}
