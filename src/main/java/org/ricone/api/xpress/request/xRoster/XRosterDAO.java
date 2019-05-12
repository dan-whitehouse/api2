package org.ricone.api.xpress.request.xRoster;

import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;

import java.util.List;

public interface XRosterDAO {
	/* Find */
	CourseSectionWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	List<CourseSectionWrapper> findAll(ControllerData metadata);

	List<CourseSectionWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<CourseSectionWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<CourseSectionWrapper> findAllByCourseRefId(ControllerData metadata, String refId);

	List<CourseSectionWrapper> findAllByStaffRefId(ControllerData metadata, String refId);

	List<CourseSectionWrapper> findAllByStudentRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId);


	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);
}