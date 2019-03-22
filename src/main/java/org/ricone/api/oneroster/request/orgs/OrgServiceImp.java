package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.v1p1.QOrg;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster2:Orgs:OrgService")
class OrgServiceImp implements OrgService {
	@Autowired private OrgDAO dao;
	@Autowired private OrgMapper mapper;
	@Autowired private OrgFieldSelector selector;

	@Override
	public OrgResponse getOrg(RequestData metadata, String refId) throws Exception {
		OrgResponse response = selector.apply(mapper.convert(dao.getOrg(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public OrgsResponse getAllOrgs(RequestData metadata) throws Exception {
		try {
			List<QOrg> instance = dao.getAllOrgs(metadata);
			if(CollectionUtils.isEmpty(instance)) {
				throw new NoContentException();
			}
			return selector.apply(mapper.convert(instance, metadata), metadata);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrgResponse getSchool(RequestData metadata, String refId) throws Exception {
		OrgResponse response = mapper.convert(dao.getSchool(metadata, refId), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public OrgsResponse getAllSchools(RequestData metadata) throws Exception {
		List<QOrg> instance = dao.getAllSchools(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance, metadata);
	}
}