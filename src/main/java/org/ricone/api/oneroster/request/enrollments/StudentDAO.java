package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.StudentCourseSection;
import org.ricone.api.core.model.wrapper.StudentCourseSectionWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

public interface StudentDAO {
	/* Find */
	StudentCourseSectionWrapper getEnrollment(ControllerData metadata, String refId);

	List<StudentCourseSectionWrapper> getAllEnrollments(ControllerData metadata);

	List<StudentCourseSectionWrapper> getEnrollmentsForSchool(ControllerData metadata, String refId);

	List<StudentCourseSectionWrapper> getEnrollmentsForClassInSchool(ControllerData metadata, String refId);
}