package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Demographics:DemographicService")
class DemographicServiceImp implements DemographicService {
	@Autowired private DemographicDAO dao;
	@Autowired private DemographicMapper mapper;

	@Override
	public DemographicResponse getDemographic(ControllerData metadata, String refId) throws Exception {
		DemographicResponse response = mapper.convert(dao.getDemographic(metadata, refId));
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public DemographicsResponse getAllDemographics(ControllerData metadata) throws Exception {
		List<StudentWrapper> instance = dao.getAllDemographics(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}
}