package org.ricone.api.oneroster.request.classes;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.Swagger;
import org.ricone.api.oneroster.component.springfox.SwaggerParam;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Classes:ClassController")
@Swagger.Controller.Class
class ClassController extends BaseController {
	private final ClassService service;

	public ClassController(ClassService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/classes/{id}")
	@Swagger.Operation.Class.GetClass /**/ @Swagger.Response.Class
	ClassResponse getClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getClass(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/classes")
	@Swagger.Operation.Class.GetAllClasses /**/ @Swagger.Response.Classes
	ClassesResponse getAllClasses(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllClasses(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/classes")
	@Swagger.Operation.Class.GetClassesForTerm /**/ @Swagger.Response.Classes
	ClassesResponse getClassesForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getClassesForTerm(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses/{id}/classes")
	@Swagger.Operation.Class.GetClassesForCourse /**/ @Swagger.Response.Classes
	ClassesResponse getClassesForCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getClassesForCourse(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/students/{id}/classes")
	@Swagger.Operation.Class.GetClassesForStudent /**/ @Swagger.Response.Classes
	ClassesResponse getClassesForStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getClassesForStudent(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/teachers/{id}/classes")
	@Swagger.Operation.Class.GetClassesForTeacher /**/ @Swagger.Response.Classes
	ClassesResponse getClassesForTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getClassesForTeacher(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/classes")
	@Swagger.Operation.Class.GetClassesForSchool /**/ @Swagger.Response.Classes
	ClassesResponse getClassesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getClassesForSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/users/{id}/classes")
	@Swagger.Operation.Class.GetClassesForUser /**/ @Swagger.Response.Classes
	ClassesResponse getClassesForUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getClassesForUser(getMetaData(request, response), id);
	}
}
