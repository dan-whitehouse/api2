package org.ricone.api.xpress.request.xContact;

import io.swagger.annotations.*;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XContactsACL;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XContactResponse;
import org.ricone.api.xpress.model.XContactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XContacts:XContactController")
@Api(value = "xContacts", description = "REST API for xContacts", tags = {"xContacts"})
public class XContactController extends BaseController {
	@Autowired
	private XContactService service;

	@XContactsACL.Get.ById
	@GetMapping(value = "/requests/xContacts/{id}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return xContact by refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XContactsACL.Get.All
	@GetMapping(value = "/requests/xContacts", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xContacts", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XContactsACL.Get.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xContacts", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xContacts by xLea refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXContactsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XContactsACL.Get.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xContacts", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xContacts by xSchool refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXContactsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XContactsACL.Get.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xContacts", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xContacts by xStudent refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXContactsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
