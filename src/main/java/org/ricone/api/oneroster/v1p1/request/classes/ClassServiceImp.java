package org.ricone.api.oneroster.v1p1.request.classes;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.v1p1.QClass;
import org.ricone.api.core.model.view.ClassView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster2:Classes:ClassService")
class ClassServiceImp implements ClassService {
	@Autowired private ClassDAO dao;
	@Autowired private ClassMapper mapper;
	@Autowired private ClassFieldSelector selector;

	@Override
	public ClassResponse getClass(ControllerData metadata, String refId) throws Exception {
		ClassResponse response = selector.apply(mapper.convert(dao.getClass(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public ClassesResponse getAllClasses(ControllerData metadata) throws Exception {
		List<QClass> instance = dao.getAllClasses(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForTerm(ControllerData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForTerm(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForCourse(ControllerData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForCourse(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForStudent(ControllerData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForStudent(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForTeacher(ControllerData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForTeacher(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForSchool(ControllerData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForUser(ControllerData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForUser(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}