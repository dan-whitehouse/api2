package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface EnrollmentDAO {
	EnrollmentView getEnrollment(ControllerData metadata, String refId) throws Exception;

	List<EnrollmentView> getAllEnrollments(ControllerData metadata) throws Exception;

	List<EnrollmentView> getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception;

	List<EnrollmentView> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception;

	int countAllEnrollments(ControllerData metadata) throws Exception;

	int countEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception ;

	int countEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception;
}