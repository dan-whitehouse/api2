package org.ricone.api.oneroster.request.courses;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.v1p1.QCourse;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster2:Courses:CourseService")
class CourseServiceImp implements CourseService {
	@Autowired private CourseDAO dao;
	@Autowired private CourseMapper mapper;
	@Autowired private CourseFieldSelector selector;

	@Override
	public CourseResponse getCourse(RequestData metadata, String refId) throws Exception {
		CourseResponse response = selector.apply(mapper.convert(dao.getCourse(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public CoursesResponse getAllCourses(RequestData metadata) throws Exception {
		List<QCourse> instance = dao.getAllCourses(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public CoursesResponse getCoursesForSchool(RequestData metadata, String refId) throws Exception {
		List<QCourse> instance = dao.getCoursesForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}