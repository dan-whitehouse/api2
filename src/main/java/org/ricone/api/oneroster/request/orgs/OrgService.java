package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.ricone.api.oneroster.component.ControllerData;

interface OrgService {
	OrgResponse getOrg(ControllerData metadata, String refId) throws Exception;

	OrgsResponse getAllOrgs(ControllerData metadata) throws Exception;

	OrgResponse getSchool(ControllerData metadata, String refId) throws Exception;

	OrgsResponse getAllSchools(ControllerData metadata) throws Exception;
}