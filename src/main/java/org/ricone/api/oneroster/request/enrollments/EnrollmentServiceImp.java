package org.ricone.api.oneroster.request.enrollments;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.Enrollment;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.xpress.error.exception.NoContentException;
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
	public EnrollmentResponse getEnrollment(ControllerData metadata, String refId) throws Exception {
		EnrollmentView instance = dao.getEnrollment(metadata, refId);
		if(instance == null) {
			throw new UnknownObjectException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public EnrollmentsResponse getAllEnrollments(ControllerData metadata) throws Exception {
		List<EnrollmentView> instance = dao.getAllEnrollments(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public EnrollmentsResponse getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception {
		List<EnrollmentView> instance = dao.getEnrollmentsForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public EnrollmentsResponse getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		List<EnrollmentView> instance = dao.getEnrollmentsForClassInSchool(metadata, schoolId, classId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}