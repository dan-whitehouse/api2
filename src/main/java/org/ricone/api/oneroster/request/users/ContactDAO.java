package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.StudentContact;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

public interface ContactDAO {
	/* Find */
	StudentContactWrapper getContact(ControllerData metadata, String refId);

	List<StudentContactWrapper> getAllContacts(ControllerData metadata);
}