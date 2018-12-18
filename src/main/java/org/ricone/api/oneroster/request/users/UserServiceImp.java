package org.ricone.api.oneroster.request.users;

import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired private UserDAO dao;
	@Autowired private StudentMapper studentMapper;
	@Autowired private StaffMapper staffMapper;

	@Override
	public UserResponse getUser(ControllerData metadata, String refId) throws Exception {
		return null;
	}

	@Override
	public UsersResponse getAllUsers(ControllerData metadata) throws Exception {
		return null;
	}

	@Override
	public UserResponse getStudent(ControllerData metadata, String refId) throws Exception {
		return studentMapper.convert(dao.getStudent(metadata, refId));
	}

	@Override
	public UsersResponse getAllStudents(ControllerData metadata) throws Exception {
		return studentMapper.convert(dao.getAllStudents(metadata));
	}

	@Override
	public UsersResponse getStudentsForSchool(ControllerData metadata, String refId) throws Exception {
		return studentMapper.convert(dao.getStudentsForSchool(metadata, refId));
	}

	@Override
	public UsersResponse getStudentsForClass(ControllerData metadata, String refId) throws Exception {
		return studentMapper.convert(dao.getStudentsForClass(metadata, refId));
	}

	@Override
	public UsersResponse getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception {
		return studentMapper.convert(dao.getStudentsForClassInSchool(metadata, refId, classRefId));
	}

	@Override
	public UserResponse getTeacher(ControllerData metadata, String refId) throws Exception {
		return staffMapper.convert(dao.getTeacher(metadata, refId));
	}

	@Override
	public UsersResponse getAllTeachers(ControllerData metadata) throws Exception {
		return staffMapper.convert(dao.getAllTeachers(metadata));
	}

	@Override
	public UsersResponse getTeachersForSchool(ControllerData metadata, String refId) throws Exception {
		return staffMapper.convert(dao.getTeachersForSchool(metadata, refId));
	}

	@Override
	public UsersResponse getTeachersForClass(ControllerData metadata, String refId) throws Exception {
		return staffMapper.convert(dao.getTeachersForClass(metadata, refId));
	}

	@Override
	public UsersResponse getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception {
		return staffMapper.convert(dao.getTeachersForClassInSchool(metadata, schoolRefId, classRefId));
	}
}