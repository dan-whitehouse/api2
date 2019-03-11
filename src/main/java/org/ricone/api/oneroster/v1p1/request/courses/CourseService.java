package org.ricone.api.oneroster.v1p1.request.courses;

import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;

interface CourseService {
	CourseResponse getCourse(ControllerData metadata, String refId) throws Exception;

	CoursesResponse getAllCourses(ControllerData metadata) throws Exception;

	CoursesResponse getCoursesForSchool(ControllerData metadata, String refId) throws Exception;
}