package org.ricone.api.oneroster.request.courses;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.ricone.api.oneroster.component.BaseController;
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
	CourseResponse getCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getCourse(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/courses")
	@ApiOperation(value = "getAllCourses", tags = {"Course"})
	CoursesResponse getAllCourses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllCourses(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/courses")
	@ApiOperation(value = "getCoursesForSchool", tags = {"Course"})
	CoursesResponse getCoursesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getCoursesForSchool(getMetaData(request, response), id);
	}
}
