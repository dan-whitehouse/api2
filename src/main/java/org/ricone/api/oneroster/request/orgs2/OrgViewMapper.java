package org.ricone.api.oneroster.request.orgs2;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.view.OrgView;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Orgs2:DistrictMapper")
class OrgViewMapper {
    private Logger logger = LogManager.getLogger(OrgViewMapper.class);

    OrgViewMapper() {
    }

    OrgsResponse convert(List<OrgView> instance) {
        List<Org> list = new ArrayList<>();
        for (OrgView wrapper : instance) {
            Org org = map(wrapper, wrapper.getLea().getLeaId());
            if(org != null) {
                list.add(org);
            }
        }

        OrgsResponse response = new OrgsResponse();
        response.setOrgs(list);
        return response;
    }

    OrgResponse convert(OrgView wrapper) {
        if(wrapper != null) {
            OrgResponse response = new OrgResponse();
            response.setOrg(map(wrapper, wrapper.getLea().getLeaId()));
            return response;
        }
        return null;
    }

    private Org map(OrgView instance, String districtId) {
        Org org = new Org();
        org.setSourcedId(instance.getSourceId());
        org.setStatus(StatusType.active);
        org.setDateLastModified(null);
        org.setMetadata(mapMetadata(instance, districtId));

        org.setType(OrgType.valueOf(instance.getType()));
        org.setName(instance.getName());
        org.setIdentifier(instance.getIdentifier());

        if(org.getType().equals(OrgType.district)) {
            if(instance.getLea() != null && CollectionUtils.isNotEmpty(instance.getLea().getSchools())) {
                instance.getLea().getSchools().forEach(school -> {
                    org.getChildren().add(MappingUtil.buildGUIDRef("schools2", school.getSchoolRefId(), GUIDType.org));
                });
            }
        }
        else {
            org.setParent(MappingUtil.buildGUIDRef("orgs2", instance.getLea().getLeaRefId(), GUIDType.org));
        }
        return org;
    }

    private Metadata mapMetadata(OrgView instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getLea().getLeaSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);

        if(StringUtils.isNotBlank(instance.getLine1())) {
            metadata.getAdditionalProperties().put("address1", instance.getLine1());
        }
        if(StringUtils.isNotBlank(instance.getLine2())) {
            metadata.getAdditionalProperties().put("address2", instance.getLine2());
        }
        if(StringUtils.isNotBlank(instance.getCity())) {
            metadata.getAdditionalProperties().put("city", instance.getCity());
        }
        if(StringUtils.isNotBlank(instance.getState())) {
            metadata.getAdditionalProperties().put("state", instance.getState());
        }
        if(StringUtils.isNotBlank(instance.getPostCode())) {
            metadata.getAdditionalProperties().put("postCode", instance.getPostCode());
        }
        if(StringUtils.isNotBlank(instance.getCountry())) {
            metadata.getAdditionalProperties().put("country", instance.getCountry());
        }
        return metadata;
    }

}