package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface DistrictDAO {
	LeaWrapper getDistrict(ControllerData metadata, String refId) throws Exception;

	List<LeaWrapper> getAllDistricts(ControllerData metadata) throws Exception;
}