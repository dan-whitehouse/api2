package org.ricone.api.xpress.request.xContact;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XContactsACL;
import org.ricone.api.xpress.model.XContactResponse;
import org.ricone.api.xpress.model.XContactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "xContacts", description = "REST API for xContacts", tags = {"xContacts"})
public class XContactController extends BaseController {
	@Autowired
	private XContactService service;

	@XContactsACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xContacts/{id}")
	@ApiOperation(value = "Return xContact by refId", tags = {"xContacts"})
	public XContactResponse getXStaffById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XContactsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xContacts")
	@ApiOperation(value = "Return all xContacts", tags = {"xContacts"})
	public XContactsResponse getXStaffs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XContactsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xContacts")
	@ApiOperation(value = "Return all xContacts by xLea refId", tags = {"xContacts"})
	public XContactsResponse getXContactsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XContactsACL.Get.ByXSchool
	@ResponseBody @GetMapping(value = "/requests/xSchools/{refId}/xContacts")
	@ApiOperation(value = "Return all xContacts by xSchool refId", tags = {"xContacts"})
	public XContactsResponse getXContactsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XContactsACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xContacts")
	@ApiOperation(value = "Return all xContacts by xStudent refId", tags = {"xContacts"})
	public XContactsResponse getXContactsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
