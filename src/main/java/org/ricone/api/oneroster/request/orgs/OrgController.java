package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.request.users.UserService;
import org.ricone.api.xpress.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@RestController
public class OrgController extends BaseController {
	@Autowired
	private OrgService service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/orgs/{id}")
	public OrgResponse getOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getOrg(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/orgs")
	public OrgsResponse getAllOrgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllOrgs(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}")
	public OrgResponse getSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getSchool(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools")
	public OrgsResponse getAllSchools(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllSchools(getMetaData(request, response));
	}
}
