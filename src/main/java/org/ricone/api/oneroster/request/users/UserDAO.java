package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.error.exception.NotFoundException;

import java.util.List;

public interface UserDAO {
	/* Find */
	LeaWrapper getUser(ControllerData metadata, String refId) throws NotFoundException;

	List<LeaWrapper> getAllUsers(ControllerData metadata);

	StudentWrapper getStudent(ControllerData metadata, String refId);

	List<StudentWrapper> getAllStudents(ControllerData metadata);

	List<StudentWrapper> getStudentsForSchool(ControllerData metadata, String refId);

	List<StudentWrapper> getStudentsForClass(ControllerData metadata, String refId);

	List<StudentWrapper> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId);

	StaffWrapper getTeacher(ControllerData metadata, String refId);

	List<StaffWrapper> getAllTeachers(ControllerData metadata);

	List<StaffWrapper> getTeachersForSchool(ControllerData metadata, String refId);

	List<StaffWrapper> getTeachersForClass(ControllerData metadata, String refId);

	List<StaffWrapper> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId);
}