package org.ricone.api.xpress.request.xStaff;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XStaffsACL;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.api.xpress.util.RequestUtil;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "xStaffs", description = "REST API for xStaffs", tags = {"xStaffs"})
public class XStaffController extends BaseController {
	@Autowired
	private XStaffService service;

	@XStaffsACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xStaffs/{id}")
	@ApiOperation(value = "Return xStaff by refId", tags = {"xStaffs"})
	public XStaffResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(RequestUtil.isIdTypeLocal(request)) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		else if(RequestUtil.isIdTypeState(request)) {
			return service.findByStateId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
	}

	@XStaffsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xStaffs")
	@ApiOperation(value = "Return all xStaffs", tags = {"xStaffs"})
	public XStaffsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XStaffsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xLea refId", tags = {"xStaffs"})
	public XStaffsResponse getXStaffsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xSchool refId", tags = {"xStaffs"})
	public XStaffsResponse getXStaffsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXCourse
	@ResponseBody @GetMapping(value = "/requests/xCourses/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xCourse refId", tags = {"xStaffs"})
	public XStaffsResponse getXStaffsByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXRoster
	@ResponseBody @GetMapping(value = "/requests/xRosters/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xRoster refId", tags = {"xStaffs"})
	public XStaffsResponse getXStaffsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xStudent refId", tags = {"xStaffs"})
	public XStaffsResponse getXStaffsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
