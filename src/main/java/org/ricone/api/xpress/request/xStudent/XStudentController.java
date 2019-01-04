package org.ricone.api.xpress.request.xStudent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XStudentsACL;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XStudentResponse;
import org.ricone.api.xpress.model.XStudentsResponse;
import org.ricone.api.xpress.request.xStaff.XStaffController;
import org.ricone.api.xpress.util.ControllerUtil;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@Api(value = "xStudents", description = "REST API for xStudents", tags = {"xStudents"})
public class XStudentController extends BaseController {
	@Autowired private XStudentService service;
	private final Logger logger = LogManager.getLogger(XStaffController.class);

	@XStudentsACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xStudents/{id}")
	@ApiOperation(value = "Return xStudent by refId", tags = {"xStudents"})
	public XStudentResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
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

	@XStudentsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xStudents")
	@ApiOperation(value = "Return all xStudents", tags = {"xStudents"})
	public XStudentsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XStudentsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xStudents")
	@ApiOperation(value = "Return all xStudents by xLea refId", tags = {"xStudents"})
	public XStudentsResponse getXStudentsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xStudents")
	@ApiOperation(value = "Return all xStudents by xSchool refId", tags = {"xStudents"})
	public XStudentsResponse getXStudentsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDateTime> changesSinceMarker) throws Exception {
		if(changesSinceMarker.isPresent()) {
			//Todo: Create service for getting AUPP
		}
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXRoster
	@ResponseBody @GetMapping(value = "/requests/xRosters/{refId}/xStudents")
	@ApiOperation(value = "Return all xStudents by xRoster refId", tags = {"xStudents"})
	public XStudentsResponse getXStudentsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXStaff
	@ResponseBody @GetMapping(value = "/requests/xStaffs/{refId}/xStudents")
	@ApiOperation(value = "Return all xStudents by xStaff refId", tags = {"xStudents"})
	public XStudentsResponse getXStudentsByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXContact
	@ResponseBody @GetMapping(value = "/requests/xContacts/{refId}/xStudents")
	@ApiOperation(value = "Return all xStudents by xContact refId", tags = {"xStudents"})
	public XStudentsResponse getXStudentsByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
