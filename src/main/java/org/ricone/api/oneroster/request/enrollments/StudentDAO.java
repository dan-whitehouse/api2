package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.wrapper.StudentCourseSectionWrapper;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface StudentDAO {
	StudentCourseSectionWrapper getEnrollment(ControllerData metadata, String refId);

	List<StudentCourseSectionWrapper> getAllEnrollments(ControllerData metadata);

	List<StudentCourseSectionWrapper> getEnrollmentsForSchool(ControllerData metadata, String refId);

	List<StudentCourseSectionWrapper> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId);
}