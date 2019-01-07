package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

public interface SchoolDAO {
	SchoolWrapper getSchool(ControllerData metadata, String refId) throws Exception;

	List<SchoolWrapper> getAllSchools(ControllerData metadata) throws Exception;
}