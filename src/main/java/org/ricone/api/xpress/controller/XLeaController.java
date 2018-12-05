package org.ricone.api.xpress.controller;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.service.XLeaService;
import org.ricone.error.exception.NotFoundException;
import org.ricone.util.Util;
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
	@GetMapping(value = "/requests/xLeas/{id}")
	@PreAuthorize("hasAuthority('get:/requests/xLeas')")
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
	@GetMapping(value = "/requests/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xLeas')")
	public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response, Pageable pageable) throws Exception {
		return service.findAll(getMetaData(request, response, pageable));
	}

	@ResponseBody
	@GetMapping(value = "/requests/xSchools/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xLeas')")
	public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xCalendars/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xCalendars/{}/xLeas')")
	public XLeasResponse getXLeasByXCalendar(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xCourses/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xLeas')")
	public XLeasResponse getXLeasByXCourse(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xRosters/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xLeas')")
	public XLeasResponse getXLeasByXRoster(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xStaffs/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xLeas')")
	public XLeasResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xStudents/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xLeas')")
	public XLeasResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response, pageable), refId);
	}

	@ResponseBody
	@GetMapping(value = "/requests/xContacts/{refId}/xLeas")
	@PreAuthorize("hasAuthority('get:/requests/xContacts/{}/xLeas')")
	public XLeasResponse getXLeasByXContact(HttpServletRequest request, HttpServletResponse response, Pageable pageable, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByContact(getMetaData(request, response, pageable), refId);
	}
}
