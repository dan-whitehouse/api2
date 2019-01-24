package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.view.OrgView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Orgs:OrgService")
class OrgServiceImp implements OrgService {
	@Autowired private OrgDAO dao;
	@Autowired private OrgMapper mapper;
	@Autowired private OrgFieldSelector fieldSelector;

	@Override
	public OrgResponse getOrg(ControllerData metadata, String refId) throws Exception {
		OrgResponse response = fieldSelector.apply(mapper.convert(dao.getOrg(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public OrgsResponse getAllOrgs(ControllerData metadata) throws Exception {
		List<OrgView> instance = dao.getAllOrgs(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return fieldSelector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public OrgResponse getSchool(ControllerData metadata, String refId) throws Exception {
		OrgResponse response = mapper.convert(dao.getSchool(metadata, refId), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public OrgsResponse getAllSchools(ControllerData metadata) throws Exception {
		List<OrgView> instance = dao.getAllSchools(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance, metadata);
	}
}