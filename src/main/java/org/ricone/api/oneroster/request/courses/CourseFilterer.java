package org.ricone.api.oneroster.request.courses;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.error.exception.InvalidDataException;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:Courses:CourseFilterer")
public class CourseFilterer extends BaseFilterer {
	public CourseFilterer() {
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

			case "schoolYear.sourcedId": return from.get("academicSession").get("sourcedId");
			case "schoolYear.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "schoolYear.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "courseCode": return from.get(field);
			case "grades": return from.get(field);
			case "subjects": return from.get(field);
			case "subjectCodes": return from.get(field);

			case "org.sourcedId": return from.get("org").get("sourcedId");
			case "org.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "org.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
