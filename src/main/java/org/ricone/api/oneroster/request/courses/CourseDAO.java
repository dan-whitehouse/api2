package org.ricone.api.oneroster.request.courses;

import org.ricone.api.core.model.v1p1.QCourse;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface CourseDAO {
	QCourse getCourse(RequestData metadata, String refId) throws Exception;

	List<QCourse> getAllCourses(RequestData metadata) throws Exception;

	List<QCourse> getCoursesForSchool(RequestData metadata, String refId) throws Exception;

	int countAllCourses(RequestData metadata) throws Exception;

	int countCoursesForSchool(RequestData metadata, String refId) throws Exception;
}