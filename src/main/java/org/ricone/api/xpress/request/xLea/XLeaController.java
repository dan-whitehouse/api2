package org.ricone.api.xpress.request.xLea;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.acl.XLeasACL;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.util.Util;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = {"xLeas"})
public class XLeaController extends BaseController {
	@Autowired
	private XLeaService service;

	@XLeasACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xLeas/{id}")
	@ApiOperation(value = "Return xLea by refId or localId", tags = {"xLeas"})
	public XLeaResponse getXLeaById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(StringUtils.equalsIgnoreCase(request.getHeader("idType"), "local")) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'idType' header.");
	}

	@XLeasACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xLeas")
	@ApiOperation(value = "Return all xLeas", tags = {"xLeas"})
	public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XLeasACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xLeas")
	@ApiOperation(value = "Return all xLeas by xSchool refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXCalendar
	@ResponseBody @GetMapping(value = "/requests/xCalendars/{refId}/xLeas")
	@ApiOperation(value = "Return all xLeas by xCalendar refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXCourse
	@ResponseBody @GetMapping(value = "/requests/xCourses/{refId}/xLeas")
	@ApiOperation(value = "Return all xLeas by xCourse refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXRoster
	@ResponseBody @GetMapping(value = "/requests/xRosters/{refId}/xLea")
	@ApiOperation(value = "Return all xLeas by xRoster refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXStaff
	@ResponseBody @GetMapping(value = "/requests/xStaffs/{refId}/xLeas")
	@ApiOperation(value = "Return all xLeas by xStaff refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xLeas")
	@ApiOperation(value = "Return all xLeas by xStudent refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXContact
	@ResponseBody @GetMapping(value = "/requests/xContacts/{refId}/xLeas")
	@ApiOperation(value = "Return all xLeas by xContact refId", tags = {"xLeas"})
	public XLeasResponse getXLeasByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
