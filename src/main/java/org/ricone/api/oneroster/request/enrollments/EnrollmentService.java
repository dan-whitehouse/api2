package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;

interface EnrollmentService {
	EnrollmentResponse getEnrollment(RequestData metadata, String refId) throws Exception;

	EnrollmentsResponse getAllEnrollments(RequestData metadata) throws Exception;

	EnrollmentsResponse getEnrollmentsForSchool(RequestData metadata, String refId) throws Exception;

	EnrollmentsResponse getEnrollmentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception;
}