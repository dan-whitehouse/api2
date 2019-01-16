package org.ricone.api.oneroster.request.users2;

import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.api.oneroster.component.ControllerData;

interface User2Service {
	UserResponse getUser(ControllerData metadata, String refId) throws Exception;

	UsersResponse getAllUsers(ControllerData metadata) throws Exception;

	UserResponse getStudent(ControllerData metadata, String refId) throws Exception;

	UsersResponse getAllStudents(ControllerData metadata) throws Exception;

	UsersResponse getStudentsForSchool(ControllerData metadata, String refId) throws Exception;

	UsersResponse getStudentsForClass(ControllerData metadata, String refId) throws Exception;

	UsersResponse getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception;

	UserResponse getTeacher(ControllerData metadata, String refId) throws Exception;

	UsersResponse getAllTeachers(ControllerData metadata) throws Exception;

	UsersResponse getTeachersForSchool(ControllerData metadata, String refId) throws Exception;

	UsersResponse getTeachersForClass(ControllerData metadata, String refId) throws Exception;

	UsersResponse getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception;
}