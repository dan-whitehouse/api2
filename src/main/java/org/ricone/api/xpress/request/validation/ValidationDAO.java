package org.ricone.api.xpress.request.validation;

import org.ricone.api.xpress.component.ControllerData;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-17
 */

public interface ValidationDAO {
	/* Find */
	int countStudentsByLeaRefId(ControllerData metadata, String leaRefId);

	int countStudentEmailsByLeaRefId(ControllerData metadata, String leaRefId);

	int countStudentLocalIdsByLeaRefId(ControllerData metadata, String leaRefId);

	int countStaffsByLeaRefId(ControllerData metadata, String leaRefId);

	int countStaffEmailsByLeaRefId(ControllerData metadata, String leaRefId);

	int countStaffLocalIdsByLeaRefId(ControllerData metadata, String leaRefId);

	int countStudentEnrollmentsPrimaryBySchoolRefId(ControllerData metadata, String schoolRefId);

	int countStudentEnrollmentsOtherBySchoolRefId(ControllerData metadata, String schoolRefId);

	int countStaffAssignmentsPrimaryBySchoolRefId(ControllerData metadata, String schoolRefId);

	int countStaffAssignmentsOtherBySchoolRefId(ControllerData metadata, String schoolRefId);

	int countCoursesBySchoolRefId(ControllerData metadata, String schoolRefId);

	int countCourseSectionsBySchoolRefId(ControllerData metadata, String schoolRefId);
}