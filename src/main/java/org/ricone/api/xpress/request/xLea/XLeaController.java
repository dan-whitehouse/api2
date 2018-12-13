package org.ricone.api.xpress.request.xLea;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.acl.XLeasACL;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.util.Util;
import org.ricone.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class XLeaController extends BaseController {
	@Autowired
	private XLeaService service;

	@ResponseBody
	@GetMapping(value = "/requests/xLeas/{id}")
	@XLeasACL.Get.ById
	public XLeaResponse getXLeaById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {

		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(StringUtils.equalsIgnoreCase(request.getHeader("idType"), "local")) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'idType' header.");
	}

	@ResponseBody
	@GetMapping(value = "/requests/xLeas")
	@XLeasACL.Get.All
	public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/requests/xSchools/{refId}/xLeas")
	@XLeasACL.Get.ByXSchools
	public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xCalendars/{refId}/xLeas")
	@XLeasACL.Get.ByXCalendars
	public XLeasResponse getXLeasByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xCourses/{refId}/xLeas")
	@XLeasACL.Get.ByXCourses
	public XLeasResponse getXLeasByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xRosters/{refId}/xLea")
	@XLeasACL.Get.ByXRosters
	public XLeasResponse getXLeasByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xStaffs/{refId}/xLeas")
	@XLeasACL.Get.ByXStaffs
	public XLeasResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xStudents/{refId}/xLeas")
	@XLeasACL.Get.ByXStudents
	public XLeasResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xContacts/{refId}/xLeas")
	@XLeasACL.Get.ByXContacts
	public XLeasResponse getXLeasByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
