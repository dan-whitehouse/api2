package org.ricone.api.oneroster.v1p1.request.courses;

import org.ricone.api.core.model.v1p1.QCourse;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface CourseDAO {
	QCourse getCourse(ControllerData metadata, String refId) throws Exception;

	List<QCourse> getAllCourses(ControllerData metadata) throws Exception;

	List<QCourse> getCoursesForSchool(ControllerData metadata, String refId) throws Exception;

	int countAllCourses(ControllerData metadata) throws Exception;

	int countCoursesForSchool(ControllerData metadata, String refId) throws Exception;
}