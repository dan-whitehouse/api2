package org.ricone.api.xpress.request.xStudent;

import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XStudentsACL;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XErrorResponse;
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
import java.util.Optional;

@RestController("XPress:XStudents:XStudentController")
@Api(value = "xStudents", description = "REST API for xStudents", tags = {"xStudents"})
public class XStudentController extends BaseController {
	@Autowired private XStudentService service;
	private final Logger logger = LogManager.getLogger(XStaffController.class);

	@XStudentsACL.Get.ById
	@GetMapping(value = "/requests/xStudents/{id}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return xStudent by refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
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
	@GetMapping(value = "/requests/xStudents", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xStudents", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XStudentsACL.Get.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xStudents by xLea refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentsResponse getXStudentsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xStudents by xSchool refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentsResponse getXStudentsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDateTime> changesSinceMarker) throws Exception {
		if(changesSinceMarker.isPresent()) {
			//Todo: Create service for getting AUPP
		}
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xStudents by xRoster refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentsResponse getXStudentsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xStudents by xStaff refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentsResponse getXStudentsByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XStudentsACL.Get.ByXContact
	@GetMapping(value = "/requests/xContacts/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xStudents by xContact refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XStudentsResponse getXStudentsByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
