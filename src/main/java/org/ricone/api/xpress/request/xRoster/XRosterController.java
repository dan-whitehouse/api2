package org.ricone.api.xpress.request.xRoster;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XRostersACL;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.model.XRosterResponse;
import org.ricone.api.xpress.model.XRostersResponse;
import org.ricone.api.xpress.request.xLea.XLeaService;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "xRosters", description = "REST API for xRosters", tags = {"xRosters"})
public class XRosterController extends BaseController {
	@Autowired
	private XRosterService service;

	@XRostersACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xRosters/{id}")
	@ApiOperation(value = "Return xRoster by refId", tags = {"xRosters"})
	public XRosterResponse getXLeaById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XRostersACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xRosters")
	@ApiOperation(value = "Return all xRosters", tags = {"xRosters"})
	public XRostersResponse getXRosters(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XRostersACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xRosters")
	@ApiOperation(value = "Return all xRosters by xLea refId", tags = {"xRosters"})
	public XRostersResponse getXRostersByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XRostersACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xRosters")
	@ApiOperation(value = "Return all xRosters by xSchool refId", tags = {"xRosters"})
	public XRostersResponse getXRostersByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XRostersACL.Get.ByXCourse
	@ResponseBody @GetMapping(value = "/requests/xCourses/{refId}/xRosters")
	@ApiOperation(value = "Return all xRosters by xCourse refId", tags = {"xRosters"})
	public XRostersResponse getXRostersByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XRostersACL.Get.ByXStaff
	@ResponseBody @GetMapping(value = "/requests/xStaffs/{refId}/xRosters")
	@ApiOperation(value = "Return all xRosters by xStaff refId", tags = {"xRosters"})
	public XRostersResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XRostersACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xRosters")
	@ApiOperation(value = "Return all xRosters by xStudent refId", tags = {"xRosters"})
	public XRostersResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
