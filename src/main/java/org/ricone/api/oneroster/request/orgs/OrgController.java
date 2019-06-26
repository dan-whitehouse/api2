package org.ricone.api.oneroster.request.orgs;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.component.springfox.Swagger;
import org.ricone.api.oneroster.component.springfox.SwaggerParam;
import org.ricone.api.oneroster.model.ErrorResponse;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Orgs:OrgController")
@Swagger.Controller.Org
class OrgController extends BaseController {
	private final OrgService service;

	public OrgController(OrgService service) {this.service = service;}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/orgs/{id}")
	@Swagger.Operation.Org.GetOrg /**/ @Swagger.Response.Org
	OrgResponse getOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getOrg(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/orgs")
	@Swagger.Operation.Org.GetAllOrgs /**/ @Swagger.Response.Orgs
	OrgsResponse getAllOrgs(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllOrgs(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}")
	@Swagger.Operation.Org.GetSchool /**/ @Swagger.Response.Org
	OrgResponse getSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id, @SwaggerParam.Field String fields) throws Exception {
		return service.getSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools")
	@Swagger.Operation.Org.GetAllSchools /**/ @Swagger.Response.Orgs
	OrgsResponse getAllSchools(HttpServletRequest request, HttpServletResponse response, @SwaggerParam.Limit String limit, @SwaggerParam.Offset String offset, @SwaggerParam.Sort String sort, @SwaggerParam.OrderBy String orderBy, @SwaggerParam.Filter String filter, @SwaggerParam.Field String fields) throws Exception {
		return service.getAllSchools(getMetaData(request, response));
	}
}
