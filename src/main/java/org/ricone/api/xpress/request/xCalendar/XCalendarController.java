package org.ricone.api.xpress.request.xCalendar;

import io.swagger.annotations.*;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XCalendarsACL;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XCalendars:XCalendarController")
@Api(value = "xCalendars", description = "REST API for xCalendars", tags = {"xCalendars"})
public class XCalendarController extends BaseController {
	@Autowired
	private XCalendarService service;

	@XCalendarsACL.Get.ById
	@GetMapping(value = "/requests/xCalendars/{id}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return xCalendar by refId", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCalendarResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCalendarResponse getXCalendarById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XCalendarsACL.Get.All
	@GetMapping(value = "/requests/xCalendars", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCalendars", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCalendarsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCalendarsResponse getXCalendars(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XCalendarsACL.Get.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xCalendars", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCalendars by xLea refId", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCalendarsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCalendarsResponse getXCalendarsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XCalendarsACL.Get.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xCalendars", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCalendars by xSchool refId", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCalendarsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCalendarsResponse getXCalendarsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}
}
