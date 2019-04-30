package org.ricone.api.xpress.request.xContact;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XContactsACL;
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
	@ResponseBody @GetMapping(value = "/requests/xContacts/{id}")
	@ApiOperation(value = "Return xContact by refId", tags = {"xContacts"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XContactsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xContacts")
	@ApiOperation(value = "Return all xContacts", tags = {"xContacts"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XContactsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xContacts")
	@ApiOperation(value = "Return all xContacts by xLea refId", tags = {"xContacts"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXContactsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XContactsACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xContacts")
	@ApiOperation(value = "Return all xContacts by xSchool refId", tags = {"xContacts"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXContactsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XContactsACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xContacts")
	@ApiOperation(value = "Return all xContacts by xStudent refId", tags = {"xContacts"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XContactsResponse getXContactsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
