package org.ricone.api.oneroster.request.enrollments;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.Base;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;

@Transactional
@Service("OneRoster:Enrollments:EnrollmentService")
class EnrollmentServiceImp implements EnrollmentService {
	@Autowired private StudentDAO studentDAO;
	@Autowired private StudentMapper studentMapper;
	@Autowired private TeacherDAO teacherDAO;
	@Autowired private TeacherMapper teacherMapper;

	@Override
	public EnrollmentResponse getEnrollment(ControllerData metadata, String refId) throws Exception {
		EnrollmentResponse studentResponse = studentMapper.convert(studentDAO.getEnrollment(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}

		EnrollmentResponse teacherResponse = teacherMapper.convert(teacherDAO.getEnrollment(metadata, refId));
		if(teacherResponse != null) {
			return teacherResponse;
		}

		throw new UnknownObjectException();
	}

	@Override
	public EnrollmentsResponse getAllEnrollments(ControllerData metadata) throws Exception {
		EnrollmentsResponse enrollmentsResponse = new EnrollmentsResponse();
		EnrollmentsResponse studentsResponse = studentMapper.convert(studentDAO.getAllEnrollments(metadata));
		EnrollmentsResponse teachersResponse = teacherMapper.convert(teacherDAO.getAllEnrollments(metadata));

		if(CollectionUtils.isNotEmpty(studentsResponse.getEnrollments())) {
			enrollmentsResponse.getEnrollments().addAll(studentsResponse.getEnrollments());
		}

		if(CollectionUtils.isNotEmpty(teachersResponse.getEnrollments())) {
			enrollmentsResponse.getEnrollments().addAll(teachersResponse.getEnrollments());
		}

		if(CollectionUtils.isEmpty(enrollmentsResponse.getEnrollments())) {
			throw new NoContentException();
		}

		//Sort On RefId
		enrollmentsResponse.getEnrollments().sort(Comparator.comparing(Base::getSourcedId));
		return enrollmentsResponse;
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