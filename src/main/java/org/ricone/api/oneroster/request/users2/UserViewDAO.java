package org.ricone.api.oneroster.request.users2;

import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface UserViewDAO {
	/* Find */
	UserView getUser(ControllerData metadata, String refId);

	List<UserView> getAllUsers(ControllerData metadata);

	UserView getStudent(ControllerData metadata, String refId);

	List<UserView> getAllStudents(ControllerData metadata);

	List<UserView> getStudentsForSchool(ControllerData metadata, String refId);

	List<UserView> getStudentsForClass(ControllerData metadata, String refId);

	List<UserView> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId);

	UserView getTeacher(ControllerData metadata, String refId);

	List<UserView> getAllTeachers(ControllerData metadata);

	List<UserView> getTeachersForSchool(ControllerData metadata, String refId);

	List<UserView> getTeachersForClass(ControllerData metadata, String refId);

	List<UserView> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId);

	UserView getContact(ControllerData metadata, String refId);

	List<UserView> getAllContacts(ControllerData metadata);
}