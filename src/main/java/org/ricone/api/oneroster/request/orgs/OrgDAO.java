package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.core.model.v1p1.QOrg;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface OrgDAO {
	QOrg getOrg(RequestData metadata, String refId) throws Exception;

	List<QOrg> getAllOrgs(RequestData metadata) throws Exception;

	QOrg getSchool(RequestData metadata, String refId) throws Exception;

	List<QOrg> getAllSchools(RequestData metadata) throws Exception;

	int countAllOrgs(RequestData metadata) throws Exception;

	int countAllSchools(RequestData metadata) throws Exception;
}