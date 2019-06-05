package org.ricone.api.oneroster.request.classes;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.oneroster.QClass;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.component.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Classes:ClassService")
class ClassServiceImp implements ClassService {
	private final ClassDAO dao;
	private final ClassMapper mapper;
	private final ClassFieldSelector selector;

	public ClassServiceImp(ClassDAO dao, ClassMapper mapper, ClassFieldSelector selector) {
		this.dao = dao;
		this.mapper = mapper;
		this.selector = selector;
	}

	@Override
	public ClassResponse getClass(RequestData metadata, String refId) throws Exception {
		ClassResponse response = selector.apply(mapper.convert(dao.getClass(metadata, refId), metadata), metadata);
		if(response != null) {
			return response;
		}
		throw new UnknownObjectException();
	}

	@Override
	public ClassesResponse getAllClasses(RequestData metadata) throws Exception {
		List<QClass> instance = dao.getAllClasses(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForTerm(RequestData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForTerm(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForCourse(RequestData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForCourse(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForStudent(RequestData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForStudent(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForTeacher(RequestData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForTeacher(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForSchool(RequestData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public ClassesResponse getClassesForUser(RequestData metadata, String refId) throws Exception {
		List<QClass> instance = dao.getClassesForUser(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}