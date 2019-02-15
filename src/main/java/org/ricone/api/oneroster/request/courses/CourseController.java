package org.ricone.api.oneroster.request.courses;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
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
@Api(value = "Course", description = "One Roster - Courses", tags = {"Course"})
class CourseController extends BaseController {
	@Autowired
	private CourseService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses/{id}")
	@ApiOperation(value = "getCourse", tags = {"Course"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = CourseResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	CourseResponse getCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getCourse(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses")
	@ApiOperation(value = "getAllCourses", tags = {"Course"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = CoursesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	CoursesResponse getAllCourses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllCourses(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/courses")
	@ApiOperation(value = "getCoursesForSchool", tags = {"Course"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = CoursesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	CoursesResponse getCoursesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getCoursesForSchool(getMetaData(request, response), id);
	}
}
