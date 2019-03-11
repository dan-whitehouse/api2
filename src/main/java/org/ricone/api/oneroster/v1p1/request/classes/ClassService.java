package org.ricone.api.oneroster.v1p1.request.classes;

import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;

interface ClassService {
	ClassResponse getClass(ControllerData metadata, String refId) throws Exception;

	ClassesResponse getAllClasses(ControllerData metadata) throws Exception;

	ClassesResponse getClassesForTerm(ControllerData metadata, String refId) throws Exception;

	ClassesResponse getClassesForCourse(ControllerData metadata, String refId) throws Exception;

	ClassesResponse getClassesForStudent(ControllerData metadata, String refId) throws Exception;

	ClassesResponse getClassesForTeacher(ControllerData metadata, String refId) throws Exception;

	ClassesResponse getClassesForSchool(ControllerData metadata, String refId) throws Exception;

	ClassesResponse getClassesForUser(ControllerData metadata, String refId) throws Exception;
}