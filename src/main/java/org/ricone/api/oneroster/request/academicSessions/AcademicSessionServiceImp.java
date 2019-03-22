package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.v1p1.QAcademicSession;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster2:AcademicSessions:AcademicSessionService")
class AcademicSessionServiceImp implements AcademicSessionService {
	@Autowired private AcademicSessionDAO dao;
	@Autowired private AcademicSessionMapper mapper;
	@Autowired private AcademicSessionFieldSelector fieldSelector;

	@Override
	public AcademicSessionResponse getAcademicSession(RequestData metadata, String refId) throws Exception {
		AcademicSessionResponse response = fieldSelector.apply(mapper.convert(dao.getAcademicSession(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllAcademicSessions(RequestData metadata) throws Exception {
		List<QAcademicSession> instance = dao.getAllAcademicSessions(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return fieldSelector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public AcademicSessionResponse getTerm(RequestData metadata, String refId) throws Exception {
		AcademicSessionResponse studentResponse = fieldSelector.apply(mapper.convert(dao.getTerm(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllTerms(RequestData metadata) throws Exception {
		List<QAcademicSession> instance = dao.getAllTerms(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return fieldSelector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public AcademicSessionResponse getGradingPeriod(RequestData metadata, String refId) throws Exception {
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllGradingPeriods(RequestData metadata) throws Exception {
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getGradingPeriodsForTerm(RequestData metadata, String refId) throws Exception {
		throw new UnknownObjectException();
	}
}