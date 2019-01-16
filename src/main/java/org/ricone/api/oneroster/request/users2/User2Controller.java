package org.ricone.api.oneroster.request.users2;

import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.api.oneroster.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class User2Controller extends BaseController {
	@Autowired
	private User2Service service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users2/{id}")
	UserResponse getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getUser(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users2")
	UsersResponse getAllUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllUsers(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students2/{id}")
	UserResponse getStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudent(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students2")
	UsersResponse getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllStudents(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/students2")
	UsersResponse getStudentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudentsForSchool(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/students2")
	UsersResponse getStudentsForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudentsForClass(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/students2")
	UsersResponse getStudentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return service.getStudentsForClassInSchool(getMetaData(request, response), schoolId, classId);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers2/{id}")
	UserResponse getTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeacher(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers2")
	UsersResponse getAllTeachers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllTeachers(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/teachers2")
	UsersResponse getTeachersForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeachersForSchool(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/teachers2")
	UsersResponse getTeachersForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeachersForClass(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/teachers2")
	UsersResponse getTeachersForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return service.getTeachersForClassInSchool(getMetaData(request, response), schoolId, classId);
	}
}

