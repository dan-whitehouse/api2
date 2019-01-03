package org.ricone.api.xpress.request.xStaff;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;

public interface XStaffService {
	XStaffResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XStaffResponse findByLocalId(ControllerData metadata, String refId) throws Exception;

	XStaffResponse findByStateId(ControllerData metadata, String refId) throws Exception;

	XStaffsResponse findAll(ControllerData metadata) throws Exception;

	XStaffsResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XStaffsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;

	XStaffsResponse findAllByCourse(ControllerData metadata, String refId) throws Exception;

	XStaffsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception;

	XStaffsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception;
}
