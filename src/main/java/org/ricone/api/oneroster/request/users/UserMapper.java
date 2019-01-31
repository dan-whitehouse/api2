package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Users:UserMapper")
class UserMapper extends BaseMapper {
    UserMapper() {
    }

    UsersResponse convert(List<UserView> instance, ControllerData metadata) {
        List<User> list = new ArrayList<>();
        for (UserView view : instance) {
            User user = map(view);
            if(user != null) {
                list.add(user);
            }
        }
        return new UsersResponse(list, mapErrors(metadata, UserView.class, User.class));
    }

    UserResponse convert(UserView view, ControllerData metadata) {
        if(view != null) {
            return new UserResponse(map(view), mapErrors(metadata, UserView.class, User.class));
        }
        return null;
    }

    private User map(UserView instance) {
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

        //Grades
        instance.getUserGrades().forEach(grade -> {
            user.getGrades().add(grade.getGradeLevel());
        });

        return user;
    }

    private Metadata mapMetadata(UserView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}