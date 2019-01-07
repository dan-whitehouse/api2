package org.ricone.api.oneroster.request.courses;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImp implements CourseService {
	@Autowired private CourseDAO dao;
	@Autowired private CourseMapper mapper;

	@Override
	public CourseResponse getCourse(ControllerData metadata, String refId) throws Exception {
		CourseResponse response = mapper.convert(dao.getCourse(metadata, refId));
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public CoursesResponse getAllCourses(ControllerData metadata) throws Exception {
		List<CourseWrapper> instance = dao.getAllCourses(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public CoursesResponse getCoursesForSchool(ControllerData metadata, String refId) throws Exception {
		List<CourseWrapper> instance = dao.getCoursesForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}
}