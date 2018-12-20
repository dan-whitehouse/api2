package org.ricone.api.oneroster.request.users;

import org.ricone.api.oneroster.model.*;
import org.ricone.api.xpress.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users/{id}")
	public UserResponse getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getUser(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users")
	public UsersResponse getAllUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllUsers(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students/{id}")
	public UserResponse getStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudent(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students")
	public UsersResponse getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllStudents(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/students")
	public UsersResponse getStudentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudentsForSchool(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/students")
	public UsersResponse getStudentsForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudentsForClass(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/students")
	public UsersResponse getStudentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return service.getStudentsForClassInSchool(getMetaData(request, response), schoolId, classId);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers/{id}")
	public UserResponse getTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeacher(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers")
	public UsersResponse getAllTeachers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllTeachers(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/teachers")
	public UsersResponse getTeachersForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeachersForSchool(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/teachers")
	public UsersResponse getTeachersForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeachersForClass(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/teachers")
	public UsersResponse getTeachersForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return service.getTeachersForClassInSchool(getMetaData(request, response), schoolId, classId);
	}
}

