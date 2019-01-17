package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.oneroster.component.ControllerData;

interface EnrollmentService {
	EnrollmentResponse getEnrollment(ControllerData metadata, String refId) throws Exception;

	EnrollmentsResponse getAllEnrollments(ControllerData metadata) throws Exception;

	EnrollmentsResponse getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception;

	EnrollmentsResponse getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception;
}