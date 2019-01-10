package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Orgs:DistrictMapper")
class DistrictMapper {
    DistrictMapper() {
    }

    OrgsResponse convert(List<LeaWrapper> instance) {
        List<Org> list = new ArrayList<>();
        for (LeaWrapper wrapper : instance) {
            Org org = map(wrapper.getLea(), wrapper.getDistrictId());
            if(org != null) {
                list.add(org);
            }
        }

        OrgsResponse response = new OrgsResponse();
        response.setOrgs(list);
        return response;
    }

    OrgResponse convert(LeaWrapper wrapper) {
        if(wrapper != null) {
            OrgResponse response = new OrgResponse();
            response.setOrg(map(wrapper.getLea(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Org map(Lea instance, String districtId) {
        Org org = new Org();
        org.setSourcedId(instance.getLeaRefId());
        org.setStatus(StatusType.active);
        org.setDateLastModified(null);
        org.setMetadata(mapMetadata(instance, districtId));

        org.setType(OrgType.district);
        org.setName(instance.getLeaName());
        org.setIdentifier(instance.getLeaId()); //TODO: Recommended we use the NCES Id

        //Children - Schools
        if(CollectionUtils.isNotEmpty(instance.getSchools())) {
            instance.getSchools().forEach(school -> {
                org.getChildren().add(MappingUtil.buildGUIDRef("schools", school.getSchoolRefId(), GUIDType.org));
            });
        }
        return org;
    }

    private Metadata mapMetadata(Lea instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getLeaSchoolYear());
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
}