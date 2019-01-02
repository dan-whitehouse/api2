package org.ricone.api.xpress.request.xCalendar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XCalendarsACL;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "xCalendars", description = "REST API for xCalendars", tags = {"xCalendars"})
public class XCalendarController extends BaseController {
	@Autowired
	private XCalendarService service;

	@XCalendarsACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xCalendars/{id}")
	@ApiOperation(value = "Return xCalendar by refId", tags = {"xCalendars"})
	public XCalendarResponse getXCalendarById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XCalendarsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xCalendars")
	@ApiOperation(value = "Return all xCalendars", tags = {"xCalendars"})
	public XCalendarsResponse getXCalendars(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XCalendarsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xCalendars")
	@ApiOperation(value = "Return all xCalendars by xLea refId", tags = {"xCalendars"})
	public XCalendarsResponse getXCalendarsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XCalendarsACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xCalendars")
	@ApiOperation(value = "Return all xCalendars by xSchool refId", tags = {"xCalendars"})
	public XCalendarsResponse getXCalendarsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}
}
