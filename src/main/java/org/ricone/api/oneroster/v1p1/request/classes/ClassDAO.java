package org.ricone.api.oneroster.v1p1.request.classes;

import org.ricone.api.core.model.v1p1.QClass;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface ClassDAO {
	QClass getClass(ControllerData metadata, String refId) throws Exception;

	List<QClass> getAllClasses(ControllerData metadata) throws Exception;

	List<QClass> getClassesForTerm(ControllerData metadata, String refId) throws Exception;

	List<QClass> getClassesForCourse(ControllerData metadata, String refId) throws Exception;

	List<QClass> getClassesForStudent(ControllerData metadata, String refId) throws Exception;

	List<QClass> getClassesForTeacher(ControllerData metadata, String refId) throws Exception;

	List<QClass> getClassesForSchool(ControllerData metadata, String refId) throws Exception;

	List<QClass> getClassesForUser(ControllerData metadata, String refId) throws Exception;

	int countAllClasses(ControllerData metadata) throws Exception;

	int countClassesForTerm(ControllerData metadata, String refId) throws Exception;

	int countClassesForCourse(ControllerData metadata, String refId) throws Exception;

	int countClassesForStudent(ControllerData metadata, String refId) throws Exception;

	int countClassesForTeacher(ControllerData metadata, String refId) throws Exception;

	int countClassesForSchool(ControllerData metadata, String refId) throws Exception;

	int countClassesForUser(ControllerData metadata, String refId) throws Exception;
}