package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:Demographics:DemographicFilterer")
public class DemographicFilterer extends BaseFilterer {
	public DemographicFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException {
		switch(field) {
			case "sourcedId": return from.get(field);
			case "status": return from.get(field);
			case "dateLastModified": return from.get(field);
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");

			case "birthDate": return from.get(field);
			case "sex": return from.get(field);
			case "americanIndianOrAlaskaNative": return from.get(field);
			case "asian": return from.get(field);
			case "blackOrAfricanAmerican": return from.get(field);
			case "nativeHawaiianOrOtherPacificIslander": return from.get(field);
			case "white": return from.get(field);
			case "demographicRaceTwoOrMoreRaces": return from.get(field);
			case "hispanicOrLatinoEthnicity": return from.get(field);
			case "countryOfBirthCode": return from.get(field);
			case "stateOfBirthAbbreviation": return from.get(field);
			case "cityOfBirth": return from.get(field);
			case "publicSchoolResidenceStatus": return from.get(field);
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
