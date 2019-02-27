package org.ricone.api.oneroster.request.classes;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.error.exception.InvalidDataException;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:Classes:ClassFilterer")
public class ClassFilterer extends BaseFilterer {
	public ClassFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException {
		switch(field) {
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");
			case "sourcedId": return from.get(field);
			case "title": return from.get(field);
			case "classCode": return from.get(field);
			case "classType": return from.get(field);
			case "location": return from.get(field);
			case "grades": return getJoin("grades").get("gradeLevel");
			case "subjects": return getJoin("subjects").get("subject");
			case "subjectCodes": return getJoin("subjects").get("subjectCode");
			case "periods": return getJoin("periods").get("period");

			case "course.sourcedId": return from.get("courseId");
			case "course.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "course.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "school.sourcedId": return from.get("orgId");
			case "school.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "school.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "terms.sourcedId": return getJoin("terms").get("termId");
			case "terms.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "terms.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
