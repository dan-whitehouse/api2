package org.ricone.api.oneroster.v1p1.request.orgs;

import org.ricone.api.core.model.v1p1.QOrg;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface OrgDAO {
	QOrg getOrg(ControllerData metadata, String refId) throws Exception;

	List<QOrg> getAllOrgs(ControllerData metadata) throws Exception;

	QOrg getSchool(ControllerData metadata, String refId) throws Exception;

	List<QOrg> getAllSchools(ControllerData metadata) throws Exception;

	int countAllOrgs(ControllerData metadata) throws Exception;

	int countAllSchools(ControllerData metadata) throws Exception;
}