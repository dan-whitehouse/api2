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
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");
			case "sourcedId": return from.get(field);
			case "status": return null;
			case "title": return from.get(field);

			case "schoolYear.sourcedId": return from.get("academicSessionId");
			case "schoolYear.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "schoolYear.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "courseCode": return from.get(field);
			case "grades": return getJoin("grades").get("gradeLevel");
			case "subjects": return getJoin("subjects").get("subject");
			case "subjectCodes": return getJoin("subjects").get("subjectCode");

			case "org.sourcedId": return from.get("orgId");
			case "org.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "org.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
