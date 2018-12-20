package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ContactMapper {
    public ContactMapper() {
    }

    UsersResponse convert(List<StudentContactWrapper> instance) {
        List<User> list = new ArrayList<>();
        for (StudentContactWrapper wrapper : instance) {
            User user = map(wrapper.getStudentContact(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        UsersResponse response = new UsersResponse();
        response.setUsers(list);
        return response;
    }

    UserResponse convert(StudentContactWrapper wrapper) {
        if(wrapper != null) {
            UserResponse response = new UserResponse();
            response.setUser(map(wrapper.getStudentContact(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private User map(StudentContact instance, String districtId) {
        User user = new User();
        user.setSourcedId(instance.getStudentContactRefId());
        user.setStatus(StatusType.active);
        user.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStudentContactSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        user.setMetadata(metadata);

        user.setRole(getRoleTypeFromRelationships(instance.getStudentContactRelationships()));

        user.setGivenName(instance.getFirstName());
        user.setFamilyName(instance.getLastName());
        user.setMiddleName(instance.getMiddleName());
        //user.setIdentifier();

        //Account
        //user.setUsername();
        //user.setPassword();
        //user.setEnabledUser();

        //Orgs
        /*if(CollectionUtils.isNotEmpty(instance.getStudentEnrollments())) {
            instance.getStudentEnrollments().forEach(studentEnrollment -> {
                String hrefSchool = "http://localhost:8080/ims/oneroster/v1p1/schools/" + studentEnrollment.getSchool().getSchoolRefId();
                user.getOrgs().add(new GUIDRef(hrefSchool, studentEnrollment.getSchool().getSchoolRefId(), GUIDType.org));

                String hrefLea = "http://localhost:8080/ims/oneroster/v1p1/orgs/" + studentEnrollment.getSchool().getLea().getLeaRefId();
                user.getOrgs().add(new GUIDRef(hrefLea, studentEnrollment.getSchool().getLea().getLeaRefId(), GUIDType.org));
            });
        }*/




        //Agents - ie: Contacts
        if(CollectionUtils.isNotEmpty(instance.getStudentContactRelationships())) {
            instance.getStudentContactRelationships().forEach(scr -> {
                if(scr.getStudent() != null) {
                    String href = "http://localhost:8080/ims/oneroster/v1p1/students/" + scr.getStudent().getStudentRefId();
                    user.getAgents().add(new GUIDRef(href, scr.getStudent().getStudentRefId(), GUIDType.user));
                }
            });
        }


        //Email
        if(CollectionUtils.isNotEmpty(instance.getStudentContactEmails())) {
            Optional<StudentContactEmail> primaryEmail = instance.getStudentContactEmails().stream().filter(email -> BooleanUtils.isTrue(email.getPrimaryEmailAddressIndicator())).findFirst();
            primaryEmail.ifPresent(studentEmail -> user.setEmail(studentEmail.getEmailAddress()));
        }


        //Phone
        if(CollectionUtils.isNotEmpty(instance.getStudentContactTelephones())) {
            Optional<StudentContactTelephone> primaryPhone = instance.getStudentContactTelephones().stream().filter(telephone -> BooleanUtils.isTrue(telephone.getPrimaryTelephoneNumberIndicator())).findFirst();
            primaryPhone.ifPresent(telephone -> user.setPhone(telephone.getTelephoneNumber()));

            Optional<StudentContactTelephone> smsPhone = instance.getStudentContactTelephones().stream().filter(telephone -> "SMS".equalsIgnoreCase(telephone.getTelephoneNumberTypeCode())).findFirst();
            smsPhone.ifPresent(telephone -> user.setSms(telephone.getTelephoneNumber()));
        }

        //Identifiers
        if(CollectionUtils.isNotEmpty(instance.getStudentContactIdentifiers())) {
            instance.getStudentContactIdentifiers().forEach(si -> user.getUserIds().add(new UserId(si.getIdentificationSystemCode(), si.getStudentContactId())));
        }
        return user;
    }

    private RoleType getRoleTypeFromRelationships(Set<StudentContactRelationship> studentContactRelationships) {
        int parent;
        int guardian;

        parent = (int) studentContactRelationships.stream().filter(scr -> StringUtils.containsIgnoreCase(scr.getRelationshipCode(), "Mother") || StringUtils.containsIgnoreCase(scr.getRelationshipCode(), "Father")).count();
        guardian = (int) studentContactRelationships.stream().filter(scr -> StringUtils.containsIgnoreCase(scr.getRelationshipCode(), "Guardian")).count();

        if(parent > 0) {
            return RoleType.parent;
        }

        if(guardian > 0) {
            return RoleType.guardian;
        }

        return RoleType.relative;
    }
}