package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.oneroster.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class AcademicSessionController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/academicSessions/{id}")
	public AcademicSessionResponse getAcademicSession(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {

		AcademicSession academicSession = new AcademicSession();
		academicSession.setTitle("Test");
		academicSession.setSourcedId(UUID.randomUUID().toString());
		academicSession.setStatus(StatusType.active);

		AcademicSessionResponse academicSessionResponse = new AcademicSessionResponse();
		academicSessionResponse.setAcademicSession(academicSession);
		return academicSessionResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/academicSessions")
	public AcademicSessionsResponse getAllAcademicSessions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AcademicSession academicSession1 = new AcademicSession();
		academicSession1.setSourcedId(UUID.randomUUID().toString());
		academicSession1.setStatus(StatusType.active);
		academicSession1.setDateLastModified(Instant.now().toString());

		academicSession1.setTitle("Test 1");
		academicSession1.setStartDate(Instant.now().toString());
		academicSession1.setEndDate(Instant.now().toString());
		academicSession1.setType(SessionType.semester);


		GUIDRef guidRef = new GUIDRef();
		guidRef.setHref("http://whatever....");
		guidRef.setSourcedId(UUID.randomUUID().toString());
		guidRef.setType(GUIDType.org);

		List<GUIDRef> children = new ArrayList<>();
		children.add(guidRef);

		academicSession1.setChildren(children);
		academicSession1.setSchoolYear("2019");


		AcademicSession academicSession2 = new AcademicSession();
		academicSession2.setTitle("Test 2");
		academicSession2.setSourcedId(UUID.randomUUID().toString());
		academicSession2.setStatus(StatusType.active);


		List<AcademicSession> academicSessionList = new ArrayList<>();
		academicSessionList.add(academicSession1);
		academicSessionList.add(academicSession2);

		AcademicSessionsResponse academicSessionsResponse = new AcademicSessionsResponse();
		academicSessionsResponse.setAcademicSessions(academicSessionList);

		return academicSessionsResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms/{id}")
	public AcademicSessionResponse getTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms")
	public AcademicSessionsResponse getAllTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods/{id}")
	public AcademicSessionResponse getGradingPeriod(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/gradingPeriods")
	public AcademicSessionsResponse getAllGradingPeriods(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/gradingPeriods")
	public AcademicSessionsResponse getGradingPeriodsForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}
}
