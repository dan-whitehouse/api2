package org.ricone.api.oneroster.request.classes;

import org.ricone.api.core.model.view.ClassView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface ClassDAO {
	ClassView getClass(ControllerData metadata, String refId) throws Exception;

	List<ClassView> getAllClasses(ControllerData metadata) throws Exception;

	List<ClassView> getClassesForTerm(ControllerData metadata, String refId) throws Exception;

	List<ClassView> getClassesForCourse(ControllerData metadata, String refId) throws Exception;

	List<ClassView> getClassesForStudent(ControllerData metadata, String refId) throws Exception;

	List<ClassView> getClassesForTeacher(ControllerData metadata, String refId) throws Exception;

	List<ClassView> getClassesForSchool(ControllerData metadata, String refId) throws Exception;

	List<ClassView> getClassesForUser(ControllerData metadata, String refId) throws Exception;
}