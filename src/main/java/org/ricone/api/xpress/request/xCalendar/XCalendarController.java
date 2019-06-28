package org.ricone.api.xpress.request.xCalendar;

import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.ISO8601;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController("XPress:XCalendars:XCalendarController")
@Swagger.Controller.XCalendarController
public class XCalendarController extends BaseController {
	private final XCalendarService service;

	public XCalendarController(XCalendarService service) {this.service = service;}

	@ACL.Get.XCalendar.ById
	@GetMapping(value = "/requests/xCalendars/{refId}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCalendarByRefId /**/ @Swagger.Response.XCalendar
	public XCalendarResponse getXCalendarByRefId(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear) throws Exception {
		return service.findByRefId(getMetaData(request, response), refId);
	}

	@ACL.Get.XCalendar.All
	@GetMapping(value = "/requests/xCalendars", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCalendars /**/ @Swagger.Response.XCalendars
	public XCalendarsResponse getXCalendars(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @RequestParam @ISO8601 @SwaggerParam.ChangesSince Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XCalendar.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xCalendars", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCalendarsByXLea /**/ @Swagger.Response.XCalendars
	public XCalendarsResponse getXCalendarsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @RequestParam @ISO8601 @SwaggerParam.ChangesSince Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XCalendar.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xCalendars", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXCalendarsByXSchool /**/ @Swagger.Response.XCalendars
	public XCalendarsResponse getXCalendarsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @RequestParam @ISO8601 @SwaggerParam.ChangesSince Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}
}
