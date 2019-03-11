package org.ricone.api.oneroster.v1p1.request.users;

import org.ricone.api.core.model.v1p1.QUser;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface UserDAO {
	/* Find */
	QUser getUser(ControllerData metadata, String refId) throws Exception;

	List<QUser> getAllUsers(ControllerData metadata) throws Exception;

	QUser getStudent(ControllerData metadata, String refId) throws Exception;

	List<QUser> getAllStudents(ControllerData metadata) throws Exception;

	List<QUser> getStudentsForSchool(ControllerData metadata, String refId) throws Exception;

	List<QUser> getStudentsForClass(ControllerData metadata, String refId) throws Exception;

	List<QUser> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception;

	QUser getTeacher(ControllerData metadata, String refId) throws Exception;

	List<QUser> getAllTeachers(ControllerData metadata) throws Exception;

	List<QUser> getTeachersForSchool(ControllerData metadata, String refId) throws Exception;

	List<QUser> getTeachersForClass(ControllerData metadata, String refId) throws Exception;

	List<QUser> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception;

	QUser getContact(ControllerData metadata, String refId) throws Exception;

	List<QUser> getAllContacts(ControllerData metadata) throws Exception;

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