package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.v1p1.QUser;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface UserDAO {
	/* Find */
	QUser getUser(RequestData metadata, String refId) throws Exception;

	List<QUser> getAllUsers(RequestData metadata) throws Exception;

	QUser getStudent(RequestData metadata, String refId) throws Exception;

	List<QUser> getAllStudents(RequestData metadata) throws Exception;

	List<QUser> getStudentsForSchool(RequestData metadata, String refId) throws Exception;

	List<QUser> getStudentsForClass(RequestData metadata, String refId) throws Exception;

	List<QUser> getStudentsForClassInSchool(RequestData metadata, String refId, String classRefId) throws Exception;

	QUser getTeacher(RequestData metadata, String refId) throws Exception;

	List<QUser> getAllTeachers(RequestData metadata) throws Exception;

	List<QUser> getTeachersForSchool(RequestData metadata, String refId) throws Exception;

	List<QUser> getTeachersForClass(RequestData metadata, String refId) throws Exception;

	List<QUser> getTeachersForClassInSchool(RequestData metadata, String schoolRefId, String classRefId) throws Exception;

	QUser getContact(RequestData metadata, String refId) throws Exception;

	List<QUser> getAllContacts(RequestData metadata) throws Exception;

	int countAllUsers(RequestData metadata) throws Exception;

	int countAllStudents(RequestData metadata) throws Exception;

	int countStudentsForSchool(RequestData metadata, String refId) throws Exception;

	int countStudentsForClass(RequestData metadata, String refId) throws Exception;

	int countStudentsForClassInSchool(RequestData metadata, String refId, String classRefId) throws Exception;

	int countAllTeachers(RequestData metadata) throws Exception;

	int countTeachersForSchool(RequestData metadata, String refId) throws Exception;

	int countTeachersForClass(RequestData metadata, String refId) throws Exception;

	int countTeachersForClassInSchool(RequestData metadata, String schoolRefId, String classRefId) throws Exception;

	int countAllContacts(RequestData metadata) throws Exception;
}