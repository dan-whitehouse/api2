package org.ricone.api.oneroster.v1p1.request.classes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster2:Classes:ClassController")
@Api(value = "Class", description = "One Roster - Classes", tags = {"Class"})
class ClassController extends BaseController {
	@Autowired
	private ClassService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/classes/{id}")
	@ApiOperation(value = "getClass", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassResponse getClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClass(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/classes")
	@ApiOperation(value = "getAllClasses", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getAllClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllClasses(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/terms/{id}/classes")
	@ApiOperation(value = "getClassesForTerm", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getClassesForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForTerm(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/courses/{id}/classes")
	@ApiOperation(value = "getClassesForCourse", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getClassesForCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForCourse(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/students/{id}/classes")
	@ApiOperation(value = "getClassesForStudent", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getClassesForStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForStudent(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/teachers/{id}/classes")
	@ApiOperation(value = "getClassesForTeacher", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getClassesForTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForTeacher(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/schools/{id}/classes")
	@ApiOperation(value = "getClassesForSchool", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getClassesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/users/{id}/classes")
	@ApiOperation(value = "getClassesForUser", tags = {"Class"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	ClassesResponse getClassesForUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getClassesForUser(getMetaData(request, response), id);
	}
}
