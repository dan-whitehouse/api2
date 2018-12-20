package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

public interface StudentDAO {
	/* Find */
	StudentWrapper getStudent(ControllerData metadata, String refId);

	List<StudentWrapper> getAllStudents(ControllerData metadata);

	List<StudentWrapper> getStudentsForSchool(ControllerData metadata, String refId);

	List<StudentWrapper> getStudentsForClass(ControllerData metadata, String refId);

	List<StudentWrapper> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId);
}