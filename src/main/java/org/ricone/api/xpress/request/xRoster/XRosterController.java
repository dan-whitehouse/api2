package org.ricone.api.xpress.request.xRoster;

import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.acl.XRostersACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XRosterResponse;
import org.ricone.api.xpress.model.XRostersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XRosters:XRosterController")
@Swagger.Controller.XRosterController
public class XRosterController extends BaseController {
	@Autowired
	private XRosterService service;

	@ACL.Get.XRoster.ById
	@GetMapping(value = "/requests/xRosters/{refId}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRosterByRefId /**/ @Swagger.Response.XRoster
	public XRosterResponse getXRosterByRefId(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear) throws Exception {
		return service.findByRefId(getMetaData(request, response), refId);
	}

	@ACL.Get.XRoster.All
	@GetMapping(value = "/requests/xRosters", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRosters /**/ @Swagger.Response.XRosters
	public XRostersResponse getXRosters(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XRoster.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xRosters", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRostersByXLea /**/ @Swagger.Response.XRosters
	public XRostersResponse getXRostersByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XRoster.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xRosters", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRostersByXSchool /**/ @Swagger.Response.XRosters
	public XRostersResponse getXRostersByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ACL.Get.XRoster.ByXCourse
	@GetMapping(value = "/requests/xCourses/{refId}/xRosters", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRostersByXCourse /**/ @Swagger.Response.XRosters
	public XRostersResponse getXRostersByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@ACL.Get.XRoster.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xRosters", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRostersByXStaff /**/ @Swagger.Response.XRosters
	public XRostersResponse getXRostersByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@ACL.Get.XRoster.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xRosters", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXRostersByXStudent /**/ @Swagger.Response.XRosters
	public XRostersResponse getXRostersByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
