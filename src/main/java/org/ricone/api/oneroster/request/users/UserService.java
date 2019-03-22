package org.ricone.api.oneroster.request.users;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;

interface UserService {
	UserResponse getUser(RequestData metadata, String refId) throws Exception;

	UsersResponse getAllUsers(RequestData metadata) throws Exception;

	UserResponse getStudent(RequestData metadata, String refId) throws Exception;

	UsersResponse getAllStudents(RequestData metadata) throws Exception;

	UsersResponse getStudentsForSchool(RequestData metadata, String refId) throws Exception;

	UsersResponse getStudentsForClass(RequestData metadata, String refId) throws Exception;

	UsersResponse getStudentsForClassInSchool(RequestData metadata, String refId, String classRefId) throws Exception;

	UserResponse getTeacher(RequestData metadata, String refId) throws Exception;

	UsersResponse getAllTeachers(RequestData metadata) throws Exception;

	UsersResponse getTeachersForSchool(RequestData metadata, String refId) throws Exception;

	UsersResponse getTeachersForClass(RequestData metadata, String refId) throws Exception;

	UsersResponse getTeachersForClassInSchool(RequestData metadata, String schoolRefId, String classRefId) throws Exception;
}