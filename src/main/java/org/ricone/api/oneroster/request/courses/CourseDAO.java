package org.ricone.api.oneroster.request.courses;

import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

interface CourseDAO {
	CourseWrapper getCourse(ControllerData metadata, String refId) throws Exception;

	List<CourseWrapper> getAllCourses(ControllerData metadata) throws Exception;

	List<CourseWrapper> getCoursesForSchool(ControllerData metadata, String refId) throws Exception;
}