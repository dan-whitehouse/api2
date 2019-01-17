package org.ricone.api.oneroster.request.courses;

import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.ricone.api.oneroster.component.ControllerData;

interface CourseService {
	CourseResponse getCourse(ControllerData metadata, String refId) throws Exception;

	CoursesResponse getAllCourses(ControllerData metadata) throws Exception;

	CoursesResponse getCoursesForSchool(ControllerData metadata, String refId) throws Exception;
}