package org.ricone.api.xpress.request.xContact;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XContactResponse;
import org.ricone.api.xpress.model.XContactsResponse;

public interface XContactService {
	XContactResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XContactsResponse findAll(ControllerData metadata) throws Exception;

	XContactsResponse findAllByLea(ControllerData metadata, String refId) throws Exception;

	XContactsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;

	XContactsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception;
}
