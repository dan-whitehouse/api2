package org.ricone.api.xpress.request.xStaff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.ISO8601;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.acl.XStaffsACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.api.xpress.util.ControllerUtil;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController("XPress:XStaffs:XStaffController")
@Swagger.Controller.XStaffController
public class XStaffController extends BaseController {
	@Autowired private XStaffService service;
	private final Logger logger = LogManager.getLogger(XStaffController.class);

	@ACL.Get.XStaff.ById
	@GetMapping(value = "/requests/xStaffs/{id}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffById /**/ @Swagger.Response.XStaff
	public XStaffResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(ControllerUtil.isRequestHeaderIdTypeLocal(request)) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		else if(ControllerUtil.isIdTypeState(request)) {
			return service.findByStateId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
	}

	@ACL.Get.XStaff.All
	@GetMapping(value = "/requests/xStaffs", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffs /**/ @Swagger.Response.XStaffs
	public XStaffsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XStaff.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xStaffs", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffsByXLea /**/ @Swagger.Response.XStaffs
	public XStaffsResponse getXStaffsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XStaff.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xStaffs", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffsByXSchool /**/ @Swagger.Response.XStaffs
	public XStaffsResponse getXStaffsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		//Todo: Create service for getting AUPP
		changesSinceMarker.ifPresent(localDateTime -> logger.debug("THIS LOOKS LIKE A DATE: " + localDateTime));
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ACL.Get.XStaff.ByXCourse
	@GetMapping(value = "/requests/xCourses/{refId}/xStaffs", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffsByXCourse /**/ @Swagger.Response.XStaffs
	public XStaffsResponse getXStaffsByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@ACL.Get.XStaff.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xStaffs", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffsByXRoster /**/ @Swagger.Response.XStaffs
	public XStaffsResponse getXStaffsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@ACL.Get.XStaff.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xStaffs", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStaffsByXStudent /**/ @Swagger.Response.XStaffs
	public XStaffsResponse getXStaffsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
