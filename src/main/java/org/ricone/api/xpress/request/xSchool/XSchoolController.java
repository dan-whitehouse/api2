package org.ricone.api.xpress.request.xSchool;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XSchoolsACL;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XSchoolResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XSchools:XSchoolController")
@Api(value = "xSchools", description = "REST API for xSchools", tags = {"xSchools"})
public class XSchoolController extends BaseController {
	@Autowired
	private XSchoolService service;

	@XSchoolsACL.Get.ById
	@GetMapping(value = "/requests/xSchools/{id}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return xSchool by refId or localId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolResponse getXSchoolById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(StringUtils.equalsIgnoreCase(request.getHeader("idType"), "local")) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
	}

	@XSchoolsACL.Get.All
	@GetMapping(value = "/requests/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchools(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XSchoolsACL.Get.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xLea refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXCalendar
	@GetMapping(value = "/requests/xCalendars/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xCalendar refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXCourse
	@GetMapping(value = "/requests/xCourses/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xCourse refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xRoster refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xStaff refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xStudent refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXContact
	@GetMapping(value = "/requests/xContacts/{refId}/xSchools", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xSchools by xContact refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
