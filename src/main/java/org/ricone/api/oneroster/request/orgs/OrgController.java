package org.ricone.api.oneroster.request.orgs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster2:Orgs:OrgController")
@Api(value = "Org", description = "One Roster - Orgs", tags = {"Org"})
class OrgController extends BaseController {
	@Autowired private OrgService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/orgs/{id}")
	@ApiOperation(value = "getOrg", tags = {"Org"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = OrgResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	OrgResponse getOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getOrg(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/orgs")
	@ApiOperation(value = "getAllOrgs", tags = {"Org"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = OrgsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	OrgsResponse getAllOrgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllOrgs(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/schools/{id}")
	@ApiOperation(value = "getSchool", tags = {"Org"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = OrgsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	OrgResponse getSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster2/v1p1/schools")
	@ApiOperation(value = "getAllSchools", tags = {"Org"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = OrgsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
	})
	OrgsResponse getAllSchools(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllSchools(getMetaData(request, response));
	}
}
