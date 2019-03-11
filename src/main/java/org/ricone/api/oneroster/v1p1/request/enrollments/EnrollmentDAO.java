package org.ricone.api.oneroster.v1p1.request.enrollments;

import org.ricone.api.core.model.v1p1.QEnrollment;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface EnrollmentDAO {
	QEnrollment getEnrollment(ControllerData metadata, String refId) throws Exception;

	List<QEnrollment> getAllEnrollments(ControllerData metadata) throws Exception;

	List<QEnrollment> getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception;

	List<QEnrollment> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception;

	int countAllEnrollments(ControllerData metadata) throws Exception;

	int countEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception ;

	int countEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception;
}