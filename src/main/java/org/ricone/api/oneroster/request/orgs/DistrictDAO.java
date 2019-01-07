package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

public interface DistrictDAO {
	LeaWrapper getDistrict(ControllerData metadata, String refId) throws Exception;

	List<LeaWrapper> getAllDistricts(ControllerData metadata) throws Exception;
}