package org.ricone.api.xpress.request.xCourse;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.ricone.api.xpress.model.XCourseResponse;
import org.ricone.api.xpress.model.XCoursesResponse;

public interface XCourseService {
	XCourseResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XCoursesResponse findAll(ControllerData metadata) throws Exception;

	XCoursesResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XCoursesResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;

	XCoursesResponse findAllByRoster(ControllerData metadata, String refId) throws Exception;
}