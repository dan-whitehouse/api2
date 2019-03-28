package org.ricone.api.oneroster.request.enrollments;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.oneroster.QEnrollment;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Enrollments:EnrollmentService")
class EnrollmentServiceImp implements EnrollmentService {
	@Autowired private EnrollmentDAO dao;
	@Autowired private EnrollmentMapper mapper;
	@Autowired private EnrollmentFieldSelector selector;

	@Override
	public EnrollmentResponse getEnrollment(RequestData metadata, String refId) throws Exception {
		QEnrollment instance = dao.getEnrollment(metadata, refId);
		if(instance == null) {
			throw new UnknownObjectException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public EnrollmentsResponse getAllEnrollments(RequestData metadata) throws Exception {
		List<QEnrollment> instance = dao.getAllEnrollments(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public EnrollmentsResponse getEnrollmentsForSchool(RequestData metadata, String refId) throws Exception {
		List<QEnrollment> instance = dao.getEnrollmentsForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public EnrollmentsResponse getEnrollmentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception {
		List<QEnrollment> instance = dao.getEnrollmentsForClassInSchool(metadata, schoolId, classId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}