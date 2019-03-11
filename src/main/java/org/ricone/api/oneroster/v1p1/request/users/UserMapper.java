package org.ricone.api.oneroster.v1p1.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QUser;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

@Component("OneRoster2:Users:UserMapper")
class UserMapper extends BaseMapper<QUser, User, UsersResponse, UserResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    UserMapper() throws NoSuchMethodException {
        super(QUser.class, User.class, UsersResponse.class, UserResponse.class);
    }

    @Override protected User map(QUser instance) {
        User user = new User();
        user.setSourcedId(instance.getSourcedId());
        user.setStatus(StatusType.active);
        user.setDateLastModified(null);
        user.setMetadata(mapMetadata(instance));

        if("student".equalsIgnoreCase(instance.getRole())) {
            user.setRole(RoleType.student);
        }
        else if("teacher".equalsIgnoreCase(instance.getRole())) {
            user.setRole(RoleType.teacher);
        }
        else if("contact".equalsIgnoreCase(instance.getRole())) {
            user.setRole(RoleType.parent);
        }

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

        //TODO: Grades
        /*instance.getUserGrades().forEach(grade -> {
            user.getGrades().add(grade.getGradeLevel());
        });*/

        return user;
    }

    @Override protected Metadata mapMetadata(QUser instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}