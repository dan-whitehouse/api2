package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.component.error.exception.InvalidDataException;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:Enrollments:EnrollmentFilterer")
public class EnrollmentFilterer extends BaseFilterer {
	public EnrollmentFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException {
		switch(field) {
			case "sourcedId": return from.get(field);
			case "status": return from.get(field);
			case "dateLastModified": return from.get(field);
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");

			case "role": return from.get(field);
			case "primary": return from.get(field);
			case "beginDate": from.get(field);
			case "endDate": from.get(field);

			case "user.sourcedId": return from.get("user").get("sourcedId");
			case "user.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "user.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "class.sourcedId": return from.get("clazz").get("sourcedId");
			case "class.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "class.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "school.sourcedId": return from.get("org").get("sourcedId");
			case "school.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "school.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
