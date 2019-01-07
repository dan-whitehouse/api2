package org.ricone.api.oneroster.request.courses;

import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.request.orgs.OrgService;
import org.ricone.api.xpress.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CourseController extends BaseController {
	@Autowired
	private CourseService service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/courses/{id}")
	public CourseResponse getCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getCourse(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/courses")
	public CoursesResponse getAllCourses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllCourses(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/courses")
	public CoursesResponse getCoursesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getCoursesForSchool(getMetaData(request, response), id);
	}
}
