package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QOrg;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component("OneRoster:Orgs:OrgMapper")
class OrgMapper extends BaseMapper<QOrg, Org, OrgsResponse, OrgResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    OrgMapper() throws NoSuchMethodException {
        super(QOrg.class, Org.class, OrgsResponse.class, OrgResponse.class);
    }

    @Override public Org map(QOrg instance) {
        Org org = new Org();
        org.setSourcedId(instance.getSourcedId());
        org.setStatus(StatusType.valueOf(instance.getStatus()));
        org.setDateLastModified(instance.getDateLastModified().atZone(ZoneId.systemDefault()));
        org.setMetadata(mapMetadata(instance));

        org.setType(OrgType.valueOf(instance.getType()));
        org.setName(instance.getName());
        org.setIdentifier(instance.getIdentifier());

        if(instance.getOrg() != null) {
            org.setParent(MappingUtil.buildGUIDRef("orgs", instance.getOrg().getSourcedId(), GUIDType.org));
        }
        instance.getChildren().forEach(child -> {
            org.getChildren().add(MappingUtil.buildGUIDRef("schools", child.getChild().getSourcedId(), GUIDType.org));
        });

        return org;
    }

    @Override public Metadata mapMetadata(QOrg instance) {
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