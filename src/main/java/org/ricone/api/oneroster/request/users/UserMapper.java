package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.oneroster.QUser;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Component("OneRoster:Users:UserMapper")
class UserMapper extends BaseMapper<QUser, User, UsersResponse, UserResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    UserMapper() throws NoSuchMethodException {
        super(QUser.class, User.class, UsersResponse.class, UserResponse.class);
    }

    @Override protected User map(QUser instance) {
        User user = new User();
        user.setSourcedId(instance.getSourcedId());
        user.setStatus(StatusType.valueOf(instance.getStatus()));
        user.setDateLastModified(instance.getDateLastModified().atZone(ZoneId.systemDefault()));
        user.setMetadata(mapMetadata(instance));

        user.setRole(RoleType.valueOf(instance.getRole()));
        user.setGivenName(instance.getGivenName());
        user.setFamilyName(instance.getFamilyName());
        user.setMiddleName(instance.getMiddleName());
        user.setIdentifier(instance.getIdentifier());

        //Account
        user.setUsername(null);
        user.setPassword(null);
        user.setEnabledUser(instance.getEnabledUser());

        user.setPhone(instance.getPhone());
        user.setSms(instance.getSms());
        user.setEmail(instance.getEmail());

        //Orgs
        if(CollectionUtils.isNotEmpty(instance.getOrgs())) {
            instance.getOrgs().forEach(org -> {
                if("district".equalsIgnoreCase(org.getOrg().getType())) {
                    user.getOrgs().add(MappingUtil.buildGUIDRef("orgs", org.getOrg().getSourcedId(), GUIDType.org));
                }
                else if("school".equalsIgnoreCase(org.getOrg().getType())) {
                    user.getOrgs().add(MappingUtil.buildGUIDRef("schools", org.getOrg().getSourcedId(), GUIDType.org));
                }
            });
        }

        //Agents - ie: Contacts
        if(CollectionUtils.isNotEmpty(instance.getAgents())) {
            instance.getAgents().forEach(agent -> {
                user.getAgents().add(MappingUtil.buildGUIDRef("users", agent.getAgent().getSourcedId(), GUIDType.user));
            });
        }

        //UserIds
        if(CollectionUtils.isNotEmpty(instance.getIdentifiers())) {
            instance.getIdentifiers().forEach(id -> {
                user.getUserIds().add(new UserId(id.getCode(), id.getId()));
            });
        }

        //Grades
        if(StringUtils.isNotBlank(instance.getGrades())) {
            List<String> grades = Arrays.asList(StringUtils.splitByWholeSeparator(instance.getGrades(), ","));
            grades.forEach(grade -> {
                user.getGrades().add(grade);
            });
        }
        return user;
    }

    @Override protected Metadata mapMetadata(QUser instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}