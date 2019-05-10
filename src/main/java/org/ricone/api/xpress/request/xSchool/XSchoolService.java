package org.ricone.api.xpress.request.xSchool;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.model.XSchoolResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;

public interface XSchoolService {
	XSchoolResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XSchoolResponse findById(ControllerData metadata, String id, String idType) throws Exception;

	XSchoolsResponse findAll(ControllerData metadata) throws Exception;

	XSchoolsResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XSchoolsResponse findAllByCalendar(ControllerData metadata, String refId) throws Exception;

	XSchoolsResponse findAllByCourse(ControllerData metadata, String refId) throws Exception;

	XSchoolsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception;

	XSchoolsResponse findAllByStaff(ControllerData metadata, String refId) throws Exception;

	XSchoolsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception;

	XSchoolsResponse findAllByContact(ControllerData metadata, String refId) throws Exception;
}