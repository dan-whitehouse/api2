package org.ricone.api.xpress.request.xCourse;

import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.acl.XCourseACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XCourseResponse;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XCourses:XCourseController")
@Swagger.Controller.XCourseController
public class XCourseController extends BaseController {
	@Autowired
	private XCourseService service;

	@ACL.Get.XCourse.ById
	@GetMapping(value = "/requests/xCourses/{refId}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCourseByRefId /**/ @Swagger.Response.XCourse
	public XCourseResponse getXCourseByRefId(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear) throws Exception {
		return service.findByRefId(getMetaData(request, response), refId);
	}

	@ACL.Get.XCourse.All
	@GetMapping(value = "/requests/xCourses", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCourses /**/ @Swagger.Response.XCourses
	public XCoursesResponse getXCourses(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XCourse.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xCourses", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCoursesByXLea /**/ @Swagger.Response.XCourses
	public XCoursesResponse getXCoursesByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XCourse.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xCourses", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCoursesByXSchool /**/ @Swagger.Response.XCourses
	public XCoursesResponse getXCoursesByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ACL.Get.XCourse.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xCourses", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCoursesByXRoster /**/ @Swagger.Response.XCourses
	public XCoursesResponse getXCoursesByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}
}
