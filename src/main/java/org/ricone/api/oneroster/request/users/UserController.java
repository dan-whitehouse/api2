package org.ricone.api.oneroster.request.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@RestController("OneRoster:Users:UserController")
@Api(value = "User", description = "One Roster - Users", tags = {"User"})
public class UserController extends BaseController {
	@Autowired
	private UserService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/users/{id}")
	@ApiOperation(value = "getUser", tags = {"User"})
	UserResponse getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getUser(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/users")
	@ApiOperation(value = "getAllUsers", tags = {"User"})
	UsersResponse getAllUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllUsers(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/students/{id}")
	@ApiOperation(value = "getStudent", tags = {"User"})
	UserResponse getStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudent(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/students")
	@ApiOperation(value = "getAllStudents", tags = {"User"})
	UsersResponse getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllStudents(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/students")
	@ApiOperation(value = "getStudentsForSchool", tags = {"User"})
	UsersResponse getStudentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudentsForSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/students")
	@ApiOperation(value = "getStudentsForClass", tags = {"User"})
	UsersResponse getStudentsForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getStudentsForClass(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/students")
	@ApiOperation(value = "getStudentsForClassInSchool", tags = {"User"})
	UsersResponse getStudentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return service.getStudentsForClassInSchool(getMetaData(request, response), schoolId, classId);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/teachers/{id}")
	@ApiOperation(value = "getTeacher", tags = {"User"})
	UserResponse getTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeacher(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/teachers")
	@ApiOperation(value = "getAllTeachers", tags = {"User"})
	UsersResponse getAllTeachers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllTeachers(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/teachers")
	@ApiOperation(value = "getTeachersForSchool", tags = {"User"})
	UsersResponse getTeachersForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeachersForSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/teachers")
	@ApiOperation(value = "getTeachersForClass", tags = {"User"})
	UsersResponse getTeachersForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getTeachersForClass(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/teachers")
	@ApiOperation(value = "getTeachersForClassInSchool", tags = {"User"})
	UsersResponse getTeachersForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return service.getTeachersForClassInSchool(getMetaData(request, response), schoolId, classId);
	}
}

