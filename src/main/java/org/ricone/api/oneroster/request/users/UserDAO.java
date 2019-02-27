package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface UserDAO {
	/* Find */
	UserView getUser(ControllerData metadata, String refId) throws Exception;

	List<UserView> getAllUsers(ControllerData metadata) throws Exception;

	UserView getStudent(ControllerData metadata, String refId) throws Exception;

	List<UserView> getAllStudents(ControllerData metadata) throws Exception;

	List<UserView> getStudentsForSchool(ControllerData metadata, String refId) throws Exception;

	List<UserView> getStudentsForClass(ControllerData metadata, String refId) throws Exception;

	List<UserView> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception;

	UserView getTeacher(ControllerData metadata, String refId) throws Exception;

	List<UserView> getAllTeachers(ControllerData metadata) throws Exception;

	List<UserView> getTeachersForSchool(ControllerData metadata, String refId) throws Exception;

	List<UserView> getTeachersForClass(ControllerData metadata, String refId) throws Exception;

	List<UserView> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception;

	UserView getContact(ControllerData metadata, String refId) throws Exception;

	List<UserView> getAllContacts(ControllerData metadata) throws Exception;

	int countAllUsers(ControllerData metadata) throws Exception;

	int countAllStudents(ControllerData metadata) throws Exception;

	int countStudentsForSchool(ControllerData metadata, String refId) throws Exception;

	int countStudentsForClass(ControllerData metadata, String refId) throws Exception;

	int countStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception;

	int countAllTeachers(ControllerData metadata) throws Exception;

	int countTeachersForSchool(ControllerData metadata, String refId) throws Exception;

	int countTeachersForClass(ControllerData metadata, String refId) throws Exception;

	int countTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception;

	int countAllContacts(ControllerData metadata) throws Exception;
}