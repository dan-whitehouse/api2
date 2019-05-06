package org.ricone.api.xpress.request.xCourse;

import io.swagger.annotations.*;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XCourseACL;
import org.ricone.api.xpress.component.swagger.SwaggerParam;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XCourseResponse;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XCourses:XCourseController")
@Api(value = "xCourses", description = "REST API for xCourses", tags = {"xCourses"})
public class XCourseController extends BaseController {
	@Autowired
	private XCourseService service;

	@XCourseACL.Get.ById
	@GetMapping(value = "/requests/xCourses/{id}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return xCourse by refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCourseResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCourseResponse getXCourseById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.IdType String idType) throws Exception {
		return service.findByRefId(getMetaData(request, response), id);
	}

	@XCourseACL.Get.All
	@GetMapping(value = "/requests/xCourses", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCourses", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCoursesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCoursesResponse getXCourses(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XCourseACL.Get.ByXLea
	@GetMapping(value = "/requests/xLeas/{refId}/xCourses", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCourses by xLea refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCoursesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCoursesResponse getXCoursesByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XCourseACL.Get.ByXSchool
	@GetMapping(value = "/requests/xSchools/{refId}/xCourses", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCourses by xSchool refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCoursesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCoursesResponse getXCoursesByXSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllBySchool(getMetaData(request, response), refId);
	}

	@XCourseACL.Get.ByXRoster
	@GetMapping(value = "/requests/xRosters/{refId}/xCourses", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Return all xCourses by xSchool refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XCoursesResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XCoursesResponse getXCoursesByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId, @SwaggerParam.SchoolYear Integer schoolYear, @SwaggerParam.NavigationPage Integer navigationPage, @SwaggerParam.NavigationPageSize Integer navigationPageSize) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}
}
