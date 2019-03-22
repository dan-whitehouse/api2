package org.ricone.api.oneroster.request.classes;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;

interface ClassService {
	ClassResponse getClass(RequestData metadata, String refId) throws Exception;

	ClassesResponse getAllClasses(RequestData metadata) throws Exception;

	ClassesResponse getClassesForTerm(RequestData metadata, String refId) throws Exception;

	ClassesResponse getClassesForCourse(RequestData metadata, String refId) throws Exception;

	ClassesResponse getClassesForStudent(RequestData metadata, String refId) throws Exception;

	ClassesResponse getClassesForTeacher(RequestData metadata, String refId) throws Exception;

	ClassesResponse getClassesForSchool(RequestData metadata, String refId) throws Exception;

	ClassesResponse getClassesForUser(RequestData metadata, String refId) throws Exception;
}