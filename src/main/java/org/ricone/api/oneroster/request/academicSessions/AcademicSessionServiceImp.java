package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.ricone.api.oneroster.model.Base;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Transactional
@Service("OneRoster:AcademicSessions:AcademicSessionService")
class AcademicSessionServiceImp implements AcademicSessionService {
	@Autowired private TermDAO termDAO;
	@Autowired private TermMapper termMapper;
	@Autowired private CalendarDAO calendarDAO;
	@Autowired private CalendarMapper calendarMapper;

	@Override
	public AcademicSessionResponse getAcademicSession(ControllerData metadata, String refId) throws Exception {
		AcademicSessionResponse studentResponse = calendarMapper.convert(calendarDAO.getCalendar(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}

		AcademicSessionResponse teacherResponse = termMapper.convert(termDAO.getTerm(metadata, refId));
		if(teacherResponse != null) {
			return teacherResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public AcademicSessionsResponse getAllAcademicSessions(ControllerData metadata) throws Exception {
		AcademicSessionsResponse response = new AcademicSessionsResponse();
		AcademicSessionsResponse studentsResponse = calendarMapper.convert(calendarDAO.getAllCalendars(metadata));
		AcademicSessionsResponse teachersResponse = termMapper.convert(termDAO.getAllTerms(metadata));

		if(CollectionUtils.isNotEmpty(studentsResponse.getAcademicSessions())) {
			response.getAcademicSessions().addAll(studentsResponse.getAcademicSessions());
		}

		if(CollectionUtils.isNotEmpty(teachersResponse.getAcademicSessions())) {
			response.getAcademicSessions().addAll(teachersResponse.getAcademicSessions());
		}

		//Sort On RefId
		response.getAcademicSessions().sort(Comparator.comparing(Base::getSourcedId));
		return response;
	}

	@Override
	public AcademicSessionResponse getTerm(ControllerData metadata, String refId) throws Exception {
		SchoolCalendarSessionWrapper instance = termDAO.getTerm(metadata, refId);
		if(instance == null) {
			throw new UnknownObjectException();
		}
		return termMapper.convert(instance);
	}

	@Override
	public AcademicSessionsResponse getAllTerms(ControllerData metadata) throws Exception {
		List<SchoolCalendarSessionWrapper> instance = termDAO.getAllTerms(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return termMapper.convert(instance);
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