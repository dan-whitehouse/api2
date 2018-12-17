package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.oneroster.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@RestController
public class OrgController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/orgs/{id}")
	public OrgResponse getOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		Org org = new Org();
		org.setSourcedId(UUID.randomUUID().toString());
		org.setStatus(StatusType.active);
		org.setDateLastModified(Instant.now().toString());

		org.setName("District");
		org.setType(OrgType.district);
		org.setIdentifier(UUID.randomUUID().toString());

		OrgResponse orgResponse = new OrgResponse();
		orgResponse.setOrg(org);

		return orgResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/orgs")
	public OrgsResponse getAllOrgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Org org1 = new Org();
		org1.setSourcedId(UUID.randomUUID().toString());
		org1.setStatus(StatusType.active);
		org1.setDateLastModified(Instant.now().toString());

		org1.setName("School Name");
		org1.setType(OrgType.school);
		org1.setIdentifier(UUID.randomUUID().toString());

		//Org 2
		Org org2 = new Org();
		org2.setSourcedId(UUID.randomUUID().toString());
		org2.setStatus(StatusType.active);
		org2.setDateLastModified(Instant.now().toString());

		org2.setName("District Name");
		org2.setType(OrgType.district);
		org2.setIdentifier(UUID.randomUUID().toString());


		GUIDRef guidRef = new GUIDRef();
		guidRef.setHref("http://localhost:8080/ims/oneroster/v1p1/orgs/");
		guidRef.setSourcedId(UUID.randomUUID().toString());
		guidRef.setType(GUIDType.org);
		org2.getChildren().add(guidRef);

		OrgsResponse orgsResponse = new OrgsResponse();
		orgsResponse.getOrgs().add(org1);
		orgsResponse.getOrgs().add(org2);

		return orgsResponse;
	}
}
