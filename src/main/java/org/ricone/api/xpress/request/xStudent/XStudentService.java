package org.ricone.api.xpress.request.xStudent;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.api.xpress.model.XStudentResponse;
import org.ricone.api.xpress.model.XStudentsResponse;

public interface XStudentService {
	XStudentResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XStudentResponse findByLocalId(ControllerData metadata, String refId) throws Exception;

	XStudentResponse findByStateId(ControllerData metadata, String refId) throws Exception;

	XStudentsResponse findAll(ControllerData metadata) throws Exception;

	XStudentsResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XStudentsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;

	XStudentsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception;

	XStudentsResponse findAllByStaff(ControllerData metadata, String refId) throws Exception;

	XStudentsResponse findAllByContact(ControllerData metadata, String refId) throws Exception;
}
