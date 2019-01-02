package org.ricone.api.xpress.request.xCourse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XCourseACL;
import org.ricone.api.xpress.model.XCourseResponse;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "xCourses", description = "REST API for xCourses", tags = {"xCourses"})
public class XCourseController extends BaseController {
	@Autowired
	private XCourseService service;

	@XCourseACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xCourses/{id}")
	@ApiOperation(value = "Return xCourse by refId", tags = {"xCourses"})
	public XCourseResponse getXCourseById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XCourseACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xCourses")
	@ApiOperation(value = "Return all xCourses", tags = {"xCourses"})
	public XCoursesResponse getXCourses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XCourseACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xCourses")
	@ApiOperation(value = "Return all xCourses by xLea refId", tags = {"xCourses"})
	public XCoursesResponse getXCoursesByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XCourseACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xCourses")
	@ApiOperation(value = "Return all xCourses by xSchool refId", tags = {"xCourses"})
	public XCoursesResponse getXCoursesByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XCourseACL.Get.ByXRoster
	@ResponseBody @GetMapping(value = "/requests/xRosters/{refId}/xCourses")
	@ApiOperation(value = "Return all xCourses by xSchool refId", tags = {"xCourses"})
	public XCoursesResponse getXCoursesByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}
}
