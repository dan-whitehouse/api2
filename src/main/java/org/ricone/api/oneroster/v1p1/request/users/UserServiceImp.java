package org.ricone.api.oneroster.v1p1.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.v1p1.QUser;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster2:Users:UserService")
class UserServiceImp implements UserService {
	@Autowired private UserDAO dao;
	@Autowired private UserMapper mapper;
	@Autowired private UserFieldSelector selector;

	@Override
	public UserResponse getUser(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = selector.apply(mapper.convert(dao.getStudent(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllUsers(ControllerData metadata) throws Exception {
		List<QUser> instance = dao.getAllUsers(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UserResponse getStudent(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = selector.apply(mapper.convert(dao.getStudent(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllStudents(ControllerData metadata) throws Exception {
		List<QUser> instance = dao.getAllStudents(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getStudentsForSchool(ControllerData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getStudentsForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getStudentsForClass(ControllerData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getStudentsForClass(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getStudentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		List<QUser> instance = dao.getStudentsForClassInSchool(metadata, schoolId, classId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UserResponse getTeacher(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = selector.apply(mapper.convert(dao.getTeacher(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllTeachers(ControllerData metadata) throws Exception {
		List<QUser> instance = dao.getAllTeachers(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getTeachersForSchool(ControllerData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getTeachersForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getTeachersForClass(ControllerData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getTeachersForClass(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception {
		List<QUser> instance = dao.getTeachersForClassInSchool(metadata, schoolRefId, classRefId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}