package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StaffMapper {

    public StaffMapper() {
    }

    UsersResponse convert(List<StaffWrapper> instance) {
        List<User> list = new ArrayList<>();
        for (StaffWrapper wrapper : instance) {
            User user = map(wrapper.getStaff(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        UsersResponse response = new UsersResponse();
        response.setUsers(list);
        return response;
    }

    UserResponse convert(StaffWrapper wrapper) {
        UserResponse response = new UserResponse();
        response.setUser(map(wrapper.getStaff(), wrapper.getDistrictId()));
        return response;
    }

    private User map(Staff instance, String districtId) {
        User user = new User();
        user.setSourcedId(instance.getStaffRefId());
        user.setStatus(StatusType.active);
        user.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStaffSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        user.setMetadata(metadata);

        user.setRole(RoleType.teacher);
        user.setGivenName(instance.getFirstName());
        user.setFamilyName(instance.getLastName());
        user.setMiddleName(instance.getMiddleName());
        //user.setIdentifier();

        //Account
        //user.setUsername();
        //user.setPassword();
        //user.setEnabledUser();

        //Orgs
        if(CollectionUtils.isNotEmpty(instance.getStaffAssignments())) {
            instance.getStaffAssignments().forEach(sa -> {
                String hrefSchool = "http://localhost:8080/ims/oneroster/v1p1/schools/" + sa.getSchool().getSchoolRefId();
                user.getOrgs().add(new GUIDRef(hrefSchool, sa.getSchool().getSchoolRefId(), GUIDType.org));

                String hrefLea = "http://localhost:8080/ims/oneroster/v1p1/orgs/" + sa.getSchool().getLea().getLeaRefId();
                user.getOrgs().add(new GUIDRef(hrefLea, sa.getSchool().getLea().getLeaRefId(), GUIDType.org));
            });
        }

        //Email
        Optional<StaffEmail> primaryEmail = instance.getStaffEmails().stream().filter(StaffEmail::getPrimaryEmailAddressIndicator).findFirst();
        primaryEmail.ifPresent(studentEmail -> user.setEmail(studentEmail.getEmailAddress()));

        //Phone

        //Identifiers
        instance.getStaffIdentifiers().forEach(staffIdentifier -> {
            user.getUserIds().add(new UserId(staffIdentifier.getIdentificationSystemCode(), staffIdentifier.getStaffId()));
        });

        return user;
    }
}