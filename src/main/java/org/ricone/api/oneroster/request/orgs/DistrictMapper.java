package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.StudentEmail;
import org.ricone.api.core.model.StudentTelephone;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getLeaSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        org.setMetadata(metadata);

        org.setType(OrgType.district);
        org.setName(instance.getLeaName());
        org.setIdentifier(instance.getLeaId());

        //Children - Schools
        if(CollectionUtils.isNotEmpty(instance.getSchools())) {
            instance.getSchools().forEach(school -> {
                org.getChildren().add(MappingUtil.buildGUIDRef("schools", school.getSchoolRefId(), GUIDType.org));
            });
        }
        return org;
    }
}