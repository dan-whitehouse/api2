package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.SchoolIdentifier;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class SchoolMapper {
    public SchoolMapper() {
    }

    OrgsResponse convert(List<SchoolWrapper> instance) {
        List<Org> list = new ArrayList<>();
        for (SchoolWrapper wrapper : instance) {
            Org org = map(wrapper.getSchool(), wrapper.getDistrictId());
            if(org != null) {
                list.add(org);
            }
        }

        OrgsResponse response = new OrgsResponse();
        response.setOrgs(list);
        return response;
    }

    OrgResponse convert(SchoolWrapper wrapper) {
        if(wrapper != null) {
            OrgResponse response = new OrgResponse();
            response.setOrg(map(wrapper.getSchool(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Org map(School instance, String districtId) {
        Org org = new Org();
        org.setSourcedId(instance.getSchoolRefId());
        org.setStatus(StatusType.active);
        org.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSchoolSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        org.setMetadata(metadata);

        org.setType(OrgType.school);
        org.setName(instance.getSchoolName());
        org.setIdentifier(mapIdentifier(instance.getSchoolIdentifiers()));

        //Parent - District/Lea
        String href = "http://localhost:8080/ims/oneroster/v1p1/orgs/" + instance.getLea().getLeaRefId();
        org.setParent(new GUIDRef(href, instance.getLea().getLeaRefId(), GUIDType.org));

        return org;
    }

    private String mapIdentifier(Set<SchoolIdentifier> schoolIdentifiers) {
        Optional<SchoolIdentifier> id = schoolIdentifiers.stream().filter(si -> StringUtils.equalsIgnoreCase(si.getIdentificationSystemCode(), "SEA")).findFirst();
        return id.map(SchoolIdentifier::getSchoolId).orElse(null);
    }
}