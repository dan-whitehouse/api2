package org.ricone.api.xpress.request.xStudent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.ACL;
import org.ricone.api.xpress.component.acl.XStudentsACL;
import org.ricone.api.xpress.component.swagger.Swagger;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XStudentResponse;
import org.ricone.api.xpress.model.XStudentsResponse;
import org.ricone.api.xpress.request.xStaff.XStaffController;
import org.ricone.api.xpress.util.ControllerUtil;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController("XPress:XStudents:XStudentController")
@Swagger.Controller.XStudentController
public class XStudentController extends BaseController {
	@Autowired private XStudentService service;
	private final Logger logger = LogManager.getLogger(XStaffController.class);

	@ACL.Get.XStudent.ById
	@GetMapping(value = "/requests/xStudents/{id}", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudentById /**/ @Swagger.Response.XStudent
	public XStudentResponse getXStudentById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
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

	@ACL.Get.XStudent.All
	@GetMapping(value = "/requests/xStudents", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudents /**/ @Swagger.Response.XStudents
	public XStudentsResponse getXStudents(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@ACL.Get.XStudent.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudentsByXLea /**/ @Swagger.Response.XStudents
	public XStudentsResponse getXStudentsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@ACL.Get.XStudent.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudentsByXSchool /**/ @Swagger.Response.XStudents
	public XStudentsResponse getXStudentsByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDateTime> changesSinceMarker) throws Exception {
		if(changesSinceMarker.isPresent()) {
			//Todo: Create service for getting AUPP
		}
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@ACL.Get.XStudent.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudentsByXRoster /**/ @Swagger.Response.XStudents
	public XStudentsResponse getXStudentsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@ACL.Get.XStudent.ByXStaff
	@GetMapping(value = "/requests/xStaffs/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudentsByXStaff /**/ @Swagger.Response.XStudents
	public XStudentsResponse getXStudentsByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@ACL.Get.XStudent.ByXContact
	@GetMapping(value = "/requests/xContacts/{refId}/xStudents", produces = {"application/json", "application/xml"})
	@Swagger.Operation.GetXStudentsByXContact /**/ @Swagger.Response.XStudents
	public XStudentsResponse getXStudentsByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
