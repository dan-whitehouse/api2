package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.SchoolIdentifier;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component("OneRoster:Orgs:SchoolMapper")
class SchoolMapper {
    SchoolMapper() {
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
        org.setMetadata(mapMetadata(instance, districtId));

        org.setType(OrgType.school);
        org.setName(instance.getSchoolName());
        org.setIdentifier(mapIdentifier(instance.getSchoolIdentifiers())); //TODO: Recommended we use the NCES Id

        //Parent - District/Lea
        if(instance.getLea() != null) {
            org.setParent(MappingUtil.buildGUIDRef("orgs", instance.getLea().getLeaRefId(), GUIDType.org));
        }
        return org;
    }

    private Metadata mapMetadata(School instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSchoolSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);

        if(StringUtils.isNotBlank(instance.getStreetNumberAndName())) {
            metadata.getAdditionalProperties().put("address1", instance.getStreetNumberAndName());
        }
        if(StringUtils.isNotBlank(instance.getLine2())) {
            metadata.getAdditionalProperties().put("address2", instance.getLine2());
        }
        if(StringUtils.isNotBlank(instance.getCity())) {
            metadata.getAdditionalProperties().put("city", instance.getCity());
        }
        if(StringUtils.isNotBlank(instance.getStateCode())) {
            metadata.getAdditionalProperties().put("state", instance.getStateCode());
        }
        if(StringUtils.isNotBlank(instance.getPostalCode())) {
            metadata.getAdditionalProperties().put("postCode", instance.getPostalCode());
        }
        if(StringUtils.isNotBlank(instance.getCountryCode())) {
            metadata.getAdditionalProperties().put("country", instance.getCountryCode());
        }
        return metadata;
    }

    private String mapIdentifier(Set<SchoolIdentifier> schoolIdentifiers) {
        Optional<SchoolIdentifier> id = schoolIdentifiers.stream().filter(si -> StringUtils.equalsIgnoreCase(si.getIdentificationSystemCode(), "SEA")).findFirst();
        return id.map(SchoolIdentifier::getSchoolId).orElse(null);
    }
}