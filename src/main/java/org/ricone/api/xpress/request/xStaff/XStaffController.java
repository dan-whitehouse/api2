package org.ricone.api.xpress.request.xStaff;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.ISO8601;
import org.ricone.api.xpress.component.acl.XStaffsACL;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.api.xpress.util.ControllerUtil;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController("XPress:XStaffs:XStaffController")
@Api(value = "xStaffs", description = "REST API for xStaffs", tags = {"xStaffs"})
public class XStaffController extends BaseController {
	@Autowired private XStaffService service;
	private final Logger logger = LogManager.getLogger(XStaffController.class);

	@XStaffsACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xStaffs/{id}")
	@ApiOperation(value = "Return xStaff by refId", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(ControllerUtil.isRequestHeaderIdTypeLocal(request)) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		else if(ControllerUtil.isIdTypeState(request)) {
			return service.findByStateId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
	}

	@XStaffsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xStaffs")
	@ApiOperation(value = "Return all xStaffs", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XStaffsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xLea refId", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffsResponse getXStaffsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xSchool refId", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffsResponse getXStaffsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		//Todo: Create service for getting AUPP
		changesSinceMarker.ifPresent(localDateTime -> logger.debug("THIS LOOKS LIKE A DATE: " + localDateTime));
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXCourse
	@ResponseBody @GetMapping(value = "/requests/xCourses/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xCourse refId", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffsResponse getXStaffsByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXRoster
	@ResponseBody @GetMapping(value = "/requests/xRosters/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xRoster refId", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffsResponse getXStaffsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XStaffsACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xStaffs")
	@ApiOperation(value = "Return all xStaffs by xStudent refId", tags = {"xStaffs"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStaffsResponse getXStaffsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
