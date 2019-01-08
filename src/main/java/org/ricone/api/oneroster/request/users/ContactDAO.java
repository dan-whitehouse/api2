package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

interface ContactDAO {
	StudentContactWrapper getContact(ControllerData metadata, String refId);

	List<StudentContactWrapper> getAllContacts(ControllerData metadata);
}