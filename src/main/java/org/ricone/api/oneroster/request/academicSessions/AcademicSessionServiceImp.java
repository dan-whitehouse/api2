package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.oneroster.QAcademicSession;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.component.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.ricone.error.NoContentException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:AcademicSessions:AcademicSessionService")
class AcademicSessionServiceImp implements AcademicSessionService {
	private final AcademicSessionDAO dao;
	private final AcademicSessionMapper mapper;
	private final AcademicSessionFieldSelector fieldSelector;

	public AcademicSessionServiceImp(AcademicSessionDAO dao, AcademicSessionMapper mapper, AcademicSessionFieldSelector fieldSelector) {
		this.dao = dao;
		this.mapper = mapper;
		this.fieldSelector = fieldSelector;
	}

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