package org.ricone.api.oneroster.request.users2;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("OneRoster:Users2:UserService")
class User2ServiceImp implements User2Service {
	@Autowired private UserViewDAO dao;
	@Autowired private User2Mapper mapper;

	@Override
	public UserResponse getUser(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = mapper.convert(dao.getStudent(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllUsers(ControllerData metadata) throws Exception {
		List<UserView> instance = dao.getAllUsers(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UserResponse getStudent(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = mapper.convert(dao.getStudent(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllStudents(ControllerData metadata) throws Exception {
		List<UserView> instance = dao.getAllStudents(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UsersResponse getStudentsForSchool(ControllerData metadata, String refId) throws Exception {
		List<UserView> instance = dao.getStudentsForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UsersResponse getStudentsForClass(ControllerData metadata, String refId) throws Exception {
		List<UserView> instance = dao.getStudentsForClass(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UsersResponse getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception {
		List<UserView> instance = dao.getStudentsForClassInSchool(metadata, refId, classRefId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UserResponse getTeacher(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = mapper.convert(dao.getTeacher(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllTeachers(ControllerData metadata) throws Exception {
		List<UserView> instance = dao.getAllTeachers(metadata);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UsersResponse getTeachersForSchool(ControllerData metadata, String refId) throws Exception {
		List<UserView> instance = dao.getTeachersForSchool(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UsersResponse getTeachersForClass(ControllerData metadata, String refId) throws Exception {
		List<UserView> instance = dao.getTeachersForClass(metadata, refId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}

	@Override
	public UsersResponse getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception {
		List<UserView> instance = dao.getTeachersForClassInSchool(metadata, schoolRefId, classRefId);
		if(CollectionUtils.isEmpty(instance)) {
			throw new NoContentException();
		}
		return mapper.convert(instance);
	}
}