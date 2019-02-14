package org.ricone.api.oneroster.request.orgs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("OneRoster:Orgs:OrgController")
@Api(value = "Org", description = "One Roster - Orgs", tags = {"Org"})
class OrgController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired private OrgService service;

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/orgs/{id}")
	@ApiOperation(value = "getOrg", tags = {"Org"})
	OrgResponse getOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getOrg(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/orgs")
	@ApiOperation(value = "getAllOrgs", tags = {"Org"})
	OrgsResponse getAllOrgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllOrgs(getMetaData(request, response));
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools/{id}")
	@ApiOperation(value = "getSchool", tags = {"Org"})
	OrgResponse getSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getSchool(getMetaData(request, response), id);
	}

	@ResponseBody @GetMapping(value = "/ims/oneroster/v1p1/schools")
	@ApiOperation(value = "getAllSchools", tags = {"Org"})
	OrgsResponse getAllSchools(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllSchools(getMetaData(request, response));
	}
}
