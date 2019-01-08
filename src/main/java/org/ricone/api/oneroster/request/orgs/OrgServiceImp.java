package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.request.users.*;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Transactional
@Service("OneRoster:Orgs:OrgService")
class OrgServiceImp implements OrgService {
	@Autowired private DistrictDAO districtDAO;
	@Autowired private SchoolDAO schoolDAO;
	@Autowired private DistrictMapper districtMapper;
	@Autowired private SchoolMapper schoolMapper;

	@Override
	public OrgResponse getOrg(ControllerData metadata, String refId) throws Exception {
		OrgResponse districtResponse = districtMapper.convert(districtDAO.getDistrict(metadata, refId));
		if(districtResponse != null) {
			return districtResponse;
		}

		OrgResponse schoolResponse = schoolMapper.convert(schoolDAO.getSchool(metadata, refId));
		if(schoolResponse != null) {
			return schoolResponse;
		}

		throw new UnknownObjectException();
	}

	@Override
	public OrgsResponse getAllOrgs(ControllerData metadata) throws Exception {
		OrgsResponse orgsResponse = new OrgsResponse();
		OrgsResponse districtsResponse = districtMapper.convert(districtDAO.getAllDistricts(metadata));
		OrgsResponse schoolsResponse = schoolMapper.convert(schoolDAO.getAllSchools(metadata));

		if(CollectionUtils.isNotEmpty(districtsResponse.getOrgs())) {
			orgsResponse.getOrgs().addAll(districtsResponse.getOrgs());
		}

		if(CollectionUtils.isNotEmpty(schoolsResponse.getOrgs())) {
			orgsResponse.getOrgs().addAll(schoolsResponse.getOrgs());
		}

		//Sort On RefId
		orgsResponse.getOrgs().sort(Comparator.comparing(Base::getSourcedId));
		return orgsResponse;
	}

	@Override
	public OrgResponse getSchool(ControllerData metadata, String refId) throws Exception {
		OrgResponse response = schoolMapper.convert(schoolDAO.getSchool(metadata, refId));
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public OrgsResponse getAllSchools(ControllerData metadata) throws Exception {
		List<SchoolWrapper> instance = schoolDAO.getAllSchools(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return schoolMapper.convert(instance);
	}
}