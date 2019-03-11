package org.ricone.api.oneroster.v1p1.request.users;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.error.exception.InvalidDataException;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster2:Users:UserFilterer")
public class UserFilterer extends BaseFilterer {
	public UserFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException {
		switch(field) {
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");
			case "sourcedId": return from.get(field);
			case "username": return from.get(field);
			case "userIds.type": return getJoin("userIds").get("code");
			case "userIds.identifier": return getJoin("userIds").get("id");
			case "enabledUser": return from.get(field);
			case "givenName": return from.get(field);
			case "familyName": return from.get(field);
			case "middleName": return from.get(field);
			case "role": return from.get(field);
			case "identifier": return from.get(field);
			case "email": return from.get(field);
			case "sms": return from.get(field);
			case "phone": return from.get(field);

			case "agents.sourcedId": return getJoin("agents").get("agentId");
			case "agents.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "agents.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "orgs.sourcedId": return getJoin("orgs").get("orgId");
			case "orgs.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "orgs.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "grades": return getJoin("grades").get("gradeLevel");
			case "password": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
