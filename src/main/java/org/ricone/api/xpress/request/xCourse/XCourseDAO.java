package org.ricone.api.xpress.request.xCourse;

import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;

import java.util.List;

public interface XCourseDAO {
	/* Find */
	CourseWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	List<CourseWrapper> findAll(ControllerData metadata);

	List<CourseWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<CourseWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<CourseWrapper> findAllByRosterRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);
}