package org.ricone.api.oneroster.request.classes;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Classes:ClassService")
class ClassServiceImp implements ClassService {
	@Autowired private ClassDAO dao;
	@Autowired private ClassMapper mapper;

	@Override
	public ClassResponse getClass(ControllerData metadata, String refId) throws Exception {
		ClassResponse response = mapper.convert(dao.getClass(metadata, refId));
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public ClassesResponse getAllClasses(ControllerData metadata) throws Exception {
		List<CourseSectionWrapper> instance = dao.getAllClasses(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public ClassesResponse getClassesForTerm(ControllerData metadata, String refId) throws Exception {
		List<CourseSectionWrapper> instance = dao.getClassesForTerm(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public ClassesResponse getClassesForCourse(ControllerData metadata, String refId) throws Exception {
		List<CourseSectionWrapper> instance = dao.getClassesForCourse(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public ClassesResponse getClassesForStudent(ControllerData metadata, String refId) throws Exception {
		List<CourseSectionWrapper> instance = dao.getClassesForStudent(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public ClassesResponse getClassesForTeacher(ControllerData metadata, String refId) throws Exception {
		List<CourseSectionWrapper> instance = dao.getClassesForTeacher(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public ClassesResponse getClassesForSchool(ControllerData metadata, String refId) throws Exception {
		List<CourseSectionWrapper> instance = dao.getClassesForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public ClassesResponse getClassesForUser(ControllerData metadata, String refId) throws Exception {
		List<CourseSectionWrapper> instance = dao.getClassesForUser(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}
}