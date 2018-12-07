package org.ricone.api.xpress.requests.xLea;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.error.exception.NotFoundException;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@GetMapping(value = "/requests/xLea/{id}")
	@PreAuthorize("hasAuthority('get:/requests/xLea')")
	public XLeaResponse getXLeaById(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "id") String id) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response, pageable), id);
		}
		else if(StringUtils.equalsIgnoreCase(request.getHeader("idType"), "local")) {
			return service.findByLocalId(getMetaData(request, response, pageable), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'idType' header.");
	}

	@ResponseBody
	@GetMapping(value = "/requests/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xLea')")
	public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response, Pageable pageable) throws Exception {
		return service.findAll(getMetaData(request, response, pageable));
	}

	@ResponseBody
	@GetMapping(value = "/requests/xSchools/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xLea')")
	public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xCalendars/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xCalendars/{}/xLea')")
	public XLeasResponse getXLeasByXCalendar(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xCourses/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xLea')")
	public XLeasResponse getXLeasByXCourse(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xRosters/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xLea')")
	public XLeasResponse getXLeasByXRoster(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xStaffs/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xLea')")
	public XLeasResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xStudents/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xLea')")
	public XLeasResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xContacts/{refId}/xLea")
	@PreAuthorize("hasAuthority('get:/requests/xContacts/{}/xLea')")
	public XLeasResponse getXLeasByXContact(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByContact(getMetaData(request, response, pageable), refId);
	}
}
