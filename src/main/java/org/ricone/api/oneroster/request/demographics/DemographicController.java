package org.ricone.api.oneroster.request.demographics;

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
public class DemographicController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/demographics/{id}")
	public DemographicResponse getDemographic(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {

		Demographic demographic = new Demographic();
		demographic.setSourcedId(UUID.randomUUID().toString());
		demographic.setStatus(StatusType.active);
		demographic.setDateLastModified(Instant.now().toString());

		demographic.setBirthDate("12/10/1987");
		demographic.setSex(Gender.male);
		demographic.setAmericanIndianOrAlaskaNative("false");
		demographic.setAsian("false");
		demographic.setBlackOrAfricanAmerican("false");
		demographic.setNativeHawaiianOrOtherPacificIslander("false");
		demographic.setWhite("true");
		demographic.setDemographicRaceTwoOrMoreRaces("false");
		demographic.setHispanicOrLatinoEthnicity("false");
		demographic.setCountryOfBirthCode("USA");
		demographic.setStateOfBirthAbbreviation("NY");
		demographic.setCityOfBirth("Schenectady");
		demographic.setPublicSchoolResidenceStatus("01652");

		DemographicResponse demographicResponse = new DemographicResponse();
		demographicResponse.setDemographics(demographic);

		return demographicResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/demographics")
	public DemographicsResponse getAllDemographics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Demographic demographic = new Demographic();
		demographic.setSourcedId(UUID.randomUUID().toString());
		demographic.setStatus(StatusType.active);
		demographic.setDateLastModified(Instant.now().toString());

		demographic.setBirthDate("12/10/1987");
		demographic.setSex(Gender.male);
		demographic.setAmericanIndianOrAlaskaNative("false");
		demographic.setAsian("false");
		demographic.setBlackOrAfricanAmerican("false");
		demographic.setNativeHawaiianOrOtherPacificIslander("false");
		demographic.setWhite("true");
		demographic.setDemographicRaceTwoOrMoreRaces("false");
		demographic.setHispanicOrLatinoEthnicity("false");
		demographic.setCountryOfBirthCode("USA");
		demographic.setStateOfBirthAbbreviation("NY");
		demographic.setCityOfBirth("Schenectady");
		demographic.setPublicSchoolResidenceStatus("01652");

		DemographicsResponse demographicsResponse = new DemographicsResponse();
		demographicsResponse.getDemographics().add(demographic);

		return demographicsResponse;
	}
}
