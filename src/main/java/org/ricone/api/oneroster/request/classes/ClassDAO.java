package org.ricone.api.oneroster.request.classes;

import org.ricone.api.core.model.v1p1.QClass;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface ClassDAO {
	QClass getClass(RequestData metadata, String refId) throws Exception;

	List<QClass> getAllClasses(RequestData metadata) throws Exception;

	List<QClass> getClassesForTerm(RequestData metadata, String refId) throws Exception;

	List<QClass> getClassesForCourse(RequestData metadata, String refId) throws Exception;

	List<QClass> getClassesForStudent(RequestData metadata, String refId) throws Exception;

	List<QClass> getClassesForTeacher(RequestData metadata, String refId) throws Exception;

	List<QClass> getClassesForSchool(RequestData metadata, String refId) throws Exception;

	List<QClass> getClassesForUser(RequestData metadata, String refId) throws Exception;

	int countAllClasses(RequestData metadata) throws Exception;

	int countClassesForTerm(RequestData metadata, String refId) throws Exception;

	int countClassesForCourse(RequestData metadata, String refId) throws Exception;

	int countClassesForStudent(RequestData metadata, String refId) throws Exception;

	int countClassesForTeacher(RequestData metadata, String refId) throws Exception;

	int countClassesForSchool(RequestData metadata, String refId) throws Exception;

	int countClassesForUser(RequestData metadata, String refId) throws Exception;
}