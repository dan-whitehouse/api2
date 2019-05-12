package org.ricone.api.oneroster.request.orgs;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.component.error.exception.InvalidDataException;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:Orgs:OrgFilterer")
public class OrgFilterer extends BaseFilterer {
	public OrgFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException {
		switch(field) {
			case "sourcedId": return from.get(field);
			case "status": return from.get(field);
			case "dateLastModified": return from.get(field);
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");
			case "metadata.address1": return from.get("line1");
			case "metadata.address2": return from.get("line2");
			case "metadata.city": return from.get("city");
			case "metadata.state": return from.get("state");
			case "metadata.postCode": return from.get("postCode");
			case "metadata.country": return from.get("country");
			case "name": return from.get(field);
			case "type": return from.get(field);
			case "identifier": return from.get(field);
			case "parent.sourcedId": return from.get("org").get("sourcedId");
			case "parent.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "parent.type": throw new InvalidDataException(buildInvalidDataException(field));
			case "children.sourcedId": return getJoin("children").get("child").get("sourcedId");
			case "children.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "children.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
