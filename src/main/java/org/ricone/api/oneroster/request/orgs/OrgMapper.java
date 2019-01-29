package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.OrgView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Orgs:OrgMapper")
class OrgMapper extends BaseMapper {
    private Logger logger = LogManager.getLogger(OrgMapper.class);

    OrgMapper() {
    }

    OrgsResponse convert(List<OrgView> instance, ControllerData metadata) {
        List<Org> list = new ArrayList<>();
        for (OrgView view : instance) {
            Org org = map(view);
            if(org != null) {
                list.add(org);
            }
        }

        OrgsResponse response = new OrgsResponse();
        response.setOrgs(list);
        response.setStatusInfoSets(mapErrors(metadata, OrgView.class, Org.class));
        return response;
    }

    OrgResponse convert(OrgView view, ControllerData metadata) {
        if(view != null) {
            OrgResponse response = new OrgResponse();
            response.setOrg(map(view));
            response.setStatusInfoSets(mapErrors(metadata, OrgView.class, Org.class));
            return response;
        }
        return null;
    }

    private Org map(OrgView instance) {
        Org org = new Org();
        org.setSourcedId(instance.getSourcedId());
        org.setStatus(StatusType.active);
        org.setDateLastModified(null);
        org.setMetadata(mapMetadata(instance));

        org.setType(OrgType.valueOf(instance.getType()));
        org.setName(instance.getName());
        org.setIdentifier(instance.getIdentifier());

        org.setParent(MappingUtil.buildGUIDRef("orgs", instance.getParentId(), GUIDType.org));
        instance.getChildren().forEach(child -> {
            org.getChildren().add(MappingUtil.buildGUIDRef("schools", child.getChildId(), GUIDType.org));
        });

        return org;
    }

    private Metadata mapMetadata(OrgView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());

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