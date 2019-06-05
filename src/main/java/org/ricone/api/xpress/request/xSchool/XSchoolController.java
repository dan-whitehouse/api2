package org.ricone.api.xpress.request.xSchool;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.ISO8601;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XSchoolResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;
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

@RestController("XPress:XSchools:XSchoolController")
@Swagger.Controller.XSchoolController
public class XSchoolController extends BaseController {
	private final XSchoolService service;

	public XSchoolController(XSchoolService service) {this.service = service;}

	@ACL.Get.XSchool.ById
	@GetMapping(value = "/requests/xSchools/{refId}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolById /**/ @Swagger.Response.XSchool
	public XSchoolResponse getXSchoolById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType(values = {"local", "state"}) String idType) throws Exception {
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

	@ACL.Get.XSchool.All
	@GetMapping(value = "/requests/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchools /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchools(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XSchool.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXLea /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XSchool.ByXCalendar
	@GetMapping(value = "/requests/xCalendars/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXCalendar /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@ACL.Get.XSchool.ByXCourse
	@GetMapping(value = "/requests/xCourses/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXCourse /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@ACL.Get.XSchool.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXRoster /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@ACL.Get.XSchool.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXStaff /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@ACL.Get.XSchool.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXStudent /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@ACL.Get.XSchool.ByXContact
	@GetMapping(value = "/requests/xContacts/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXSchoolsByXContact /**/ @Swagger.Response.XSchools
	public XSchoolsResponse getXSchoolsByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
