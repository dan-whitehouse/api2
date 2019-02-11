package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Demographics:DemographicService")
class DemographicServiceImp implements DemographicService {
	@Autowired private DemographicDAO dao;
	@Autowired private DemographicMapper mapper;
	@Autowired private DemographicFieldSelector selector;

	@Override
	public DemographicResponse getDemographic(ControllerData metadata, String refId) throws Exception {
		DemographicResponse response = selector.apply(mapper.convert(dao.getDemographic(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public DemographicsResponse getAllDemographics(ControllerData metadata) throws Exception {
		List<DemographicView> instance = dao.getAllDemographics(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}