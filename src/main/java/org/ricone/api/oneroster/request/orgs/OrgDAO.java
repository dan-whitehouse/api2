package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.core.model.view.OrgView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface OrgDAO {
	OrgView getOrg(ControllerData metadata, String refId) throws Exception;

	List<OrgView> getAllOrgs(ControllerData metadata) throws Exception;

	OrgView getSchool(ControllerData metadata, String refId) throws Exception;

	List<OrgView> getAllSchools(ControllerData metadata) throws Exception;
}