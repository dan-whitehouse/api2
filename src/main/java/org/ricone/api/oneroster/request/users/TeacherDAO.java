package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

interface TeacherDAO {
	StaffWrapper getTeacher(ControllerData metadata, String refId);

	List<StaffWrapper> getAllTeachers(ControllerData metadata);

	List<StaffWrapper> getTeachersForSchool(ControllerData metadata, String refId);

	List<StaffWrapper> getTeachersForClass(ControllerData metadata, String refId);

	List<StaffWrapper> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId);
}