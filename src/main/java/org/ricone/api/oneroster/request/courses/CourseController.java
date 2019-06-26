package org.ricone.api.oneroster.request.courses;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.Swagger;
import org.ricone.api.oneroster.component.springfox.SwaggerParam;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Courses:AcademicSessionController")
@Swagger.Controller.Course
class CourseController extends BaseController {
	private final CourseService service;

	public CourseController(CourseService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses/{id}")
	@Swagger.Operation.Course.GetCourse /**/ @Swagger.Response.Course
	CourseResponse getCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getCourse(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses")
	@Swagger.Operation.Course.GetAllCourses /**/ @Swagger.Response.Courses
	CoursesResponse getAllCourses(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllCourses(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/courses")
	@Swagger.Operation.Course.GetCoursesForSchool /**/ @Swagger.Response.Courses
	CoursesResponse getCoursesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getCoursesForSchool(getMetaData(request, response), id);
	}
}
