package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.wrapper.StaffCourseSectionWrapper;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface TeacherDAO {
	StaffCourseSectionWrapper getEnrollment(ControllerData metadata, String refId);

	List<StaffCourseSectionWrapper> getAllEnrollments(ControllerData metadata);

	List<StaffCourseSectionWrapper> getEnrollmentsForSchool(ControllerData metadata, String refId);

	List<StaffCourseSectionWrapper> getEnrollmentsForClassInSchool(ControllerData metadata, String refId);
}