package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.error.exception.UnknownObjectException;
import org.ricone.api.oneroster.model.Base;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired private StudentDAO studentDAO;
	@Autowired private TeacherDAO teacherDAO;
	@Autowired private ContactDAO contactDAO;
	@Autowired private StudentMapper studentMapper;
	@Autowired private TeacherMapper teacherMapper;
	@Autowired private ContactMapper contactMapper;

	@Override
	public UserResponse getUser(ControllerData metadata, String refId) throws Exception {
		UserResponse studentResponse = studentMapper.convert(studentDAO.getStudent(metadata, refId));
		if(studentResponse != null) {
			return studentResponse;
		}

		UserResponse teacherResponse = teacherMapper.convert(teacherDAO.getTeacher(metadata, refId));
		if(teacherResponse != null) {
			return teacherResponse;
		}

		UserResponse contactResponse = contactMapper.convert(contactDAO.getContact(metadata, refId));
		if(contactResponse != null) {
			return contactResponse;
		}
		throw new UnknownObjectException();
	}

	@Override
	public UsersResponse getAllUsers(ControllerData metadata) throws Exception {
		UsersResponse usersResponse = new UsersResponse();
		UsersResponse studentsResponse = studentMapper.convert(studentDAO.getAllStudents(metadata));
		UsersResponse teachersResponse = teacherMapper.convert(teacherDAO.getAllTeachers(metadata));
		UsersResponse contactsResponse = contactMapper.convert(contactDAO.getAllContacts(metadata));

		if(CollectionUtils.isNotEmpty(studentsResponse.getUsers())) {
			usersResponse.getUsers().addAll(studentsResponse.getUsers());
		}

		if(CollectionUtils.isNotEmpty(teachersResponse.getUsers())) {
			usersResponse.getUsers().addAll(teachersResponse.getUsers());
		}

		if(CollectionUtils.isNotEmpty(contactsResponse.getUsers())) {
			usersResponse.getUsers().addAll(contactsResponse.getUsers());
		}

		//Sort On RefId
		usersResponse.getUsers().sort(Comparator.comparing(Base::getSourcedId));
		return usersResponse;
	}

	@Override
	public UserResponse getStudent(ControllerData metadata, String refId) throws Exception {
		StudentWrapper instance = studentDAO.getStudent(metadata, refId);
		if(instance == null) {
			throw new UnknownObjectException();
		}
		return studentMapper.convert(instance);
	}

	@Override
	public UsersResponse getAllStudents(ControllerData metadata) throws Exception {
		return studentMapper.convert(studentDAO.getAllStudents(metadata));
	}

	@Override
	public UsersResponse getStudentsForSchool(ControllerData metadata, String refId) throws Exception {
		return studentMapper.convert(studentDAO.getStudentsForSchool(metadata, refId));
	}

	@Override
	public UsersResponse getStudentsForClass(ControllerData metadata, String refId) throws Exception {
		return studentMapper.convert(studentDAO.getStudentsForClass(metadata, refId));
	}

	@Override
	public UsersResponse getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) throws Exception {
		return studentMapper.convert(studentDAO.getStudentsForClassInSchool(metadata, refId, classRefId));
	}

	@Override
	public UserResponse getTeacher(ControllerData metadata, String refId) throws Exception {
		StaffWrapper instance = teacherDAO.getTeacher(metadata, refId);
		if(instance == null) {
			throw new UnknownObjectException();
		}
		return teacherMapper.convert(instance);
	}

	@Override
	public UsersResponse getAllTeachers(ControllerData metadata) throws Exception {
		return teacherMapper.convert(teacherDAO.getAllTeachers(metadata));
	}

	@Override
	public UsersResponse getTeachersForSchool(ControllerData metadata, String refId) throws Exception {
		return teacherMapper.convert(teacherDAO.getTeachersForSchool(metadata, refId));
	}

	@Override
	public UsersResponse getTeachersForClass(ControllerData metadata, String refId) throws Exception {
		return teacherMapper.convert(teacherDAO.getTeachersForClass(metadata, refId));
	}

	@Override
	public UsersResponse getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) throws Exception {
		return teacherMapper.convert(teacherDAO.getTeachersForClassInSchool(metadata, schoolRefId, classRefId));
	}
}