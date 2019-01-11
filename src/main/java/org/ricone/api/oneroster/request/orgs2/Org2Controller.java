package org.ricone.api.oneroster.request.orgs2;

import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
class Org2Controller extends BaseController {
	@Autowired
	private Org2Service service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/orgs2/{id}")
	OrgResponse getOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getOrg(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/orgs2")
	OrgsResponse getAllOrgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllOrgs(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools2/{id}")
	OrgResponse getSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getSchool(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools2")
	OrgsResponse getAllSchools(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllSchools(getMetaData(request, response));
	}
}
