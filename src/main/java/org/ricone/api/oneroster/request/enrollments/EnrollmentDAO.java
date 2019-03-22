package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.v1p1.QEnrollment;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface EnrollmentDAO {
	QEnrollment getEnrollment(RequestData metadata, String refId) throws Exception;

	List<QEnrollment> getAllEnrollments(RequestData metadata) throws Exception;

	List<QEnrollment> getEnrollmentsForSchool(RequestData metadata, String refId) throws Exception;

	List<QEnrollment> getEnrollmentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception;

	int countAllEnrollments(RequestData metadata) throws Exception;

	int countEnrollmentsForSchool(RequestData metadata, String refId) throws Exception ;

	int countEnrollmentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception;
}