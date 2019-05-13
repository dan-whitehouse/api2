package org.ricone.api.xpress.request.xLea;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.ISO8601;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController("XPress:XLeas:XLeaController")
@Swagger.Controller.XLeaController
public class XLeaController extends BaseController {
	@Autowired
	private XLeaService service;

	@ACL.Get.XLea.ById
	@GetMapping(value = "/requests/xLeas/{refId}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeaById /**/ @Swagger.Response.XLea
	public XLeaResponse getXLeaById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String id) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else {
			if(StringUtils.isNotBlank(request.getHeader("IdType"))) {
				return service.findById(getMetaData(request, response), id, request.getHeader("IdType"));
			}
			throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
		}
	}

	@ACL.Get.XLea.All
	@ResponseBody @GetMapping(value = "/requests/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeas /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XLea.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXSchool /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ACL.Get.XLea.ByXCalendar
	@GetMapping(value = "/requests/xCalendars/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXCalendar /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@ACL.Get.XLea.ByXCourse
	@GetMapping(value = "/requests/xCourses/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXCourse /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@ACL.Get.XLea.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xLea", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXRoster /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@ACL.Get.XLea.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXStaff /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@ACL.Get.XLea.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXStudent /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@ACL.Get.XLea.ByXContact
	@GetMapping(value = "/requests/xContacts/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXLeasByXContact /**/ @Swagger.Response.XLeas
	public XLeasResponse getXLeasByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
