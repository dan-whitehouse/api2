package org.ricone.api.xpress.request.xContact;

import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.ISO8601;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XContactResponse;
import org.ricone.api.xpress.model.XContactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController("XPress:XContacts:XContactController")
@Swagger.Controller.XContactController
public class XContactController extends BaseController {
	private final XContactService service;

	public XContactController(XContactService service) {this.service = service;}

	@ACL.Get.XContact.ByRefId
	@GetMapping(value = "/requests/xContacts/{refId}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXContactByRefId /**/ @Swagger.Response.XContact
	public XContactResponse getXContactByRefId(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear) throws Exception {
		return service.findByRefId(getMetaData(request, response), refId);
	}

	@ACL.Get.XContact.All
	@GetMapping(value = "/requests/xContacts", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXContacts /**/ @Swagger.Response.XContacts
	public XContactsResponse getXContacts(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XContact.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xContacts", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXContactsByXLea /**/ @Swagger.Response.XContacts
	public XContactsResponse getXContactsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XContact.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xContacts", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXContactsByXSchool /**/ @Swagger.Response.XContacts
	public XContactsResponse getXContactsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ACL.Get.XContact.ByXStudent
	@GetMapping(value = "/requests/xStudents/{refId}/xContacts", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXContactsByXStudent /**/ @Swagger.Response.XContacts
	public XContactsResponse getXContactsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @SwaggerParam.ChangesSince @RequestParam @ISO8601 Optional<LocalDateTime> changesSinceMarker) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}
}
