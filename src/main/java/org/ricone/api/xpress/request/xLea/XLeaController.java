package org.ricone.api.xpress.request.xLea;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.acl.XLeasACL;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.util.Util;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XLeas:XLeaController")
@Api(value = "xLeas", description = "REST API for xLeas", tags = {"xLeas"})
public class XLeaController extends BaseController {
	@Autowired
	private XLeaService service;

	@XLeasACL.Get.ById
	@GetMapping(value = "/requests/xLeas/{id}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return xLea by refId or localId", tags = {"xLeas"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeaResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeaResponse getXLeaById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(StringUtils.equalsIgnoreCase(request.getHeader("IdType"), "local")) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
	}

	@XLeasACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class, responseHeaders = {
					@ResponseHeader(name = "SchoolYear")
			}),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeas(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XLeasACL.Get.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xSchool refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXCalendar
	@GetMapping(value = "/requests/xCalendars/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xCalendar refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXCourse
	@GetMapping(value = "/requests/xCourses/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xCourse refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xLea", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xRoster refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xStaff refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xStudent refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@XLeasACL.Get.ByXContact
	@GetMapping(value = "/requests/xContacts/{refId}/xLeas", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xLeas by xContact refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XLeasResponse getXLeasByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
