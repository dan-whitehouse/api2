package org.ricone.api.oneroster.request.classes;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.component.error.exception.InvalidDataException;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:Classes:ClassFilterer")
public class ClassFilterer extends BaseFilterer {
	public ClassFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException {
		switch(field) {
			case "sourcedId": return from.get(field);
			case "status": return from.get(field);
			case "dateLastModified": return from.get(field);
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");

			case "title": return from.get(field);
			case "classCode": return from.get(field);
			case "classType": return from.get(field);
			case "location": return from.get(field);
			case "grades": return from.get(field);
			case "subjects": return from.get(field);
			case "subjectCodes": return from.get(field);
			case "periods": return from.get(field);

			case "course.sourcedId": return from.get("course").get("sourcedId");
			case "course.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "course.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "school.sourcedId": return from.get("org").get("sourcedId");
			case "school.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "school.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "terms.sourcedId": return getJoin("terms").get("academicSession").get("sourcedId");
			case "terms.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "terms.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
