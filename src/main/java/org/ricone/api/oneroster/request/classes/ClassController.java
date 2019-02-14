package org.ricone.api.oneroster.request.classes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Classes:ClassController")
@Api(value = "Class", description = "One Roster - Classes", tags = {"Class"})
class ClassController extends BaseController {
	@Autowired
	private ClassService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/classes/{id}")
	@ApiOperation(value = "getClass", tags = {"Class"})
	ClassResponse getClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClass(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/classes")
	@ApiOperation(value = "getAllClasses", tags = {"Class"})
	ClassesResponse getAllClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllClasses(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/classes")
	@ApiOperation(value = "getClassesForTerm", tags = {"Class"})
	ClassesResponse getClassesForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForTerm(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses/{id}/classes")
	@ApiOperation(value = "getClassesForCourse", tags = {"Class"})
	ClassesResponse getClassesForCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForCourse(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/students/{id}/classes")
	@ApiOperation(value = "getClassesForStudent", tags = {"Class"})
	ClassesResponse getClassesForStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForStudent(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/teachers/{id}/classes")
	@ApiOperation(value = "getClassesForTeacher", tags = {"Class"})
	ClassesResponse getClassesForTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForTeacher(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/classes")
	@ApiOperation(value = "getClassesForSchool", tags = {"Class"})
	ClassesResponse getClassesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/users/{id}/classes")
	@ApiOperation(value = "getClassesForUser", tags = {"Class"})
	ClassesResponse getClassesForUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForUser(getMetaData(request, response), id);
	}
}
