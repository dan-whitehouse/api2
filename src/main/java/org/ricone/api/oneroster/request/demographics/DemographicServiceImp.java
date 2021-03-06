package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.oneroster.QDemographic;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.component.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.ricone.error.NoContentException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Demographics:DemographicService")
class DemographicServiceImp implements DemographicService {
	private final DemographicDAO dao;
	private final DemographicMapper mapper;
	private final DemographicFieldSelector selector;

	public DemographicServiceImp(DemographicDAO dao, DemographicMapper mapper, DemographicFieldSelector selector) {
		this.dao = dao;
		this.mapper = mapper;
		this.selector = selector;
	}

	@Override
	public DemographicResponse getDemographic(RequestData metadata, String refId) throws Exception {
		DemographicResponse response = selector.apply(mapper.convert(dao.getDemographic(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public DemographicsResponse getAllDemographics(RequestData metadata) throws Exception {
		List<QDemographic> instance = dao.getAllDemographics(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}