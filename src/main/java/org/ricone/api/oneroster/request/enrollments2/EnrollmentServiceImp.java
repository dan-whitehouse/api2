package org.ricone.api.oneroster.request.enrollments2;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Enrollments2:EnrollmentService")
class EnrollmentServiceImp implements EnrollmentService {
	@Autowired private EnrollmentDAO dao;
	@Autowired private EnrollmentMapper mapper;

	@Override
	public EnrollmentResponse getEnrollment(ControllerData metadata, String refId) throws Exception {
		EnrollmentResponse studentResponse = mapper.convert(dao.getEnrollment(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public EnrollmentsResponse getAllEnrollments(ControllerData metadata) throws Exception {
		List<EnrollmentView> instance = dao.getAllEnrollments(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public EnrollmentsResponse getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception {
		return null;
	}

	@Override
	public EnrollmentsResponse getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		return null;
	}
}