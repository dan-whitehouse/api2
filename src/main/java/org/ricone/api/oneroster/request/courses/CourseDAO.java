package org.ricone.api.oneroster.request.courses;

import org.ricone.api.core.model.view.CourseView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface CourseDAO {
	CourseView getCourse(ControllerData metadata, String refId) throws Exception;

	List<CourseView> getAllCourses(ControllerData metadata) throws Exception;

	List<CourseView> getCoursesForSchool(ControllerData metadata, String refId) throws Exception;
}