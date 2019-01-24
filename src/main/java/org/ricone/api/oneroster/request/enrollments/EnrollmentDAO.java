package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface EnrollmentDAO {
	EnrollmentView getEnrollment(ControllerData metadata, String refId);

	List<EnrollmentView> getAllEnrollments(ControllerData metadata);

	List<EnrollmentView> getEnrollmentsForSchool(ControllerData metadata, String refId);

	List<EnrollmentView> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId);
}