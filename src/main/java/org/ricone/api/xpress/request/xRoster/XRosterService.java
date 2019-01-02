package org.ricone.api.xpress.request.xRoster;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XRosterResponse;
import org.ricone.api.xpress.model.XRostersResponse;
import org.ricone.api.xpress.model.XSchoolResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;

public interface XRosterService {
	XRosterResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XRostersResponse findAll(ControllerData metadata) throws Exception;

	XRostersResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XRostersResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;

	XRostersResponse findAllByCourse(ControllerData metadata, String refId) throws Exception;

	XRostersResponse findAllByStaff(ControllerData metadata, String refId) throws Exception;

	XRostersResponse findAllByStudent(ControllerData metadata, String refId) throws Exception;
}