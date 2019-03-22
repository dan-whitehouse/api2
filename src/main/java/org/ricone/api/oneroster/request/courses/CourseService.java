package org.ricone.api.oneroster.request.courses;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;

interface CourseService {
	CourseResponse getCourse(RequestData metadata, String refId) throws Exception;

	CoursesResponse getAllCourses(RequestData metadata) throws Exception;

	CoursesResponse getCoursesForSchool(RequestData metadata, String refId) throws Exception;
}