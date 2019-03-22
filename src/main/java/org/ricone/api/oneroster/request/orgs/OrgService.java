package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;

interface OrgService {
	OrgResponse getOrg(RequestData metadata, String refId) throws Exception;

	OrgsResponse getAllOrgs(RequestData metadata) throws Exception;

	OrgResponse getSchool(RequestData metadata, String refId) throws Exception;

	OrgsResponse getAllSchools(RequestData metadata) throws Exception;
}