package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Users:UserMapper")
class UserMapper {
    UserMapper() {
    }

    UsersResponse convert(List<UserView> instance) {
        List<User> list = new ArrayList<>();
        for (UserView wrapper : instance) {
            User user = map(wrapper, null);
            if(user != null) {
                list.add(user);
            }
        }

        UsersResponse response = new UsersResponse();
        response.setUsers(list);
        return response;
    }

    UserResponse convert(UserView wrapper) {
        if(wrapper != null) {
            UserResponse response = new UserResponse();
            response.setUser(map(wrapper, null));
            return response;
        }
        return null;
    }

    private User map(UserView instance, String districtId) {
        User user = new User();
        user.setSourcedId(instance.getSourceId());
        user.setStatus(StatusType.active);
        user.setDateLastModified(null);
        user.setMetadata(mapMetadata(instance, districtId));

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
        user.setEnabledUser(BooleanUtils.toStringTrueFalse(instance.getEnabledUser()));

        user.setPhone(instance.getPhone());
        user.setSms(instance.getSms());
        user.setEmail(instance.getEmail());

        //Orgs
        if(CollectionUtils.isNotEmpty(instance.getUserOrgs())) {
            instance.getUserOrgs().forEach(org -> {
                if("district".equalsIgnoreCase(org.getOrgType())) {
                    user.getOrgs().add(MappingUtil.buildGUIDRef("orgs", org.getOrgId(), GUIDType.org));
                }
                else if("school".equalsIgnoreCase(org.getOrgType())) {
                    user.getOrgs().add(MappingUtil.buildGUIDRef("schools", org.getOrgId(), GUIDType.org));
                }
            });
        }

        //Agents - ie: Contacts
        if(CollectionUtils.isNotEmpty(instance.getUserAgents())) {
            instance.getUserAgents().forEach(agent -> {
                user.getAgents().add(MappingUtil.buildGUIDRef("users", agent.getAgentId(), GUIDType.user));
            });
        }

        //UserIds
        if(CollectionUtils.isNotEmpty(instance.getUserIds())) {
            instance.getUserIds().forEach(id -> {
                user.getUserIds().add(new UserId(id.getCode(), id.getId()));
            });
        }
        return user;
    }

    private Metadata mapMetadata(UserView instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourceSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}