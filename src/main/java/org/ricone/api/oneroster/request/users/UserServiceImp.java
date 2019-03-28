package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.oneroster.QUser;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Users:UserService")
class UserServiceImp implements UserService {
	@Autowired private UserDAO dao;
	@Autowired private UserMapper mapper;
	@Autowired private UserFieldSelector selector;

	@Override
	public UserResponse getUser(RequestData metadata, String refId) throws Exception {
		UserResponse studentResponse = selector.apply(mapper.convert(dao.getUser(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllUsers(RequestData metadata) throws Exception {
		List<QUser> instance = dao.getAllUsers(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UserResponse getStudent(RequestData metadata, String refId) throws Exception {
		UserResponse studentResponse = selector.apply(mapper.convert(dao.getStudent(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllStudents(RequestData metadata) throws Exception {
		List<QUser> instance = dao.getAllStudents(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getStudentsForSchool(RequestData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getStudentsForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getStudentsForClass(RequestData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getStudentsForClass(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getStudentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception {
		List<QUser> instance = dao.getStudentsForClassInSchool(metadata, schoolId, classId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UserResponse getTeacher(RequestData metadata, String refId) throws Exception {
		UserResponse studentResponse = selector.apply(mapper.convert(dao.getTeacher(metadata, refId), metadata), metadata);
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllTeachers(RequestData metadata) throws Exception {
		List<QUser> instance = dao.getAllTeachers(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getTeachersForSchool(RequestData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getTeachersForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getTeachersForClass(RequestData metadata, String refId) throws Exception {
		List<QUser> instance = dao.getTeachersForClass(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}

	@Override
	public UsersResponse getTeachersForClassInSchool(RequestData metadata, String schoolRefId, String classRefId) throws Exception {
		List<QUser> instance = dao.getTeachersForClassInSchool(metadata, schoolRefId, classRefId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return selector.apply(mapper.convert(instance, metadata), metadata);
	}
}