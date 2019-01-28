package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.ricone.api.oneroster.model.Base;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Transactional
@Service("OneRoster:AcademicSessions:AcademicSessionService")
class AcademicSessionServiceImp implements AcademicSessionService {
	@Autowired private AcademicSessionDAO dao;
	@Autowired private AcademicSessionMapper mapper;
	@Autowired private AcademicSessionFieldSelector fieldSelector;

	@Override
	public AcademicSessionResponse getAcademicSession(ControllerData metadata, String refId) throws Exception {
		AcademicSessionResponse response = fieldSelector.apply(mapper.convert(dao.getAcademicSession(metadata, refId)), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllAcademicSessions(ControllerData metadata) throws Exception {
		List<AcademicSessionView> instance = dao.getAllAcademicSessions(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return fieldSelector.apply(mapper.convert(instance), metadata);
	}

	@Override
	public AcademicSessionResponse getTerm(ControllerData metadata, String refId) throws Exception {
		AcademicSessionResponse studentResponse = fieldSelector.apply(mapper.convert(dao.getTerm(metadata, refId)), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllTerms(ControllerData metadata) throws Exception {
		List<AcademicSessionView> instance = dao.getAllTerms(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return fieldSelector.apply(mapper.convert(instance), metadata);
	}

	@Override
	public AcademicSessionResponse getGradingPeriod(ControllerData metadata, String refId) throws Exception {
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllGradingPeriods(ControllerData metadata) throws Exception {
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getGradingPeriodsForTerm(ControllerData metadata, String refId) throws Exception {
		throw new UnknownObjectException();
	}
}