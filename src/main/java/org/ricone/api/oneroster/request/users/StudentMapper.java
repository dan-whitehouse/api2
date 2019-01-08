package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Metadata;
import org.ricone.api.oneroster.util.MappingUtil;
import org.ricone.api.xpress.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("OneRoster:Users:StudentMapper")
class StudentMapper {
    StudentMapper() {
    }

    UsersResponse convert(List<StudentWrapper> instance) {
        List<User> list = new ArrayList<>();
        for (StudentWrapper wrapper : instance) {
            User user = map(wrapper.getStudent(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        UsersResponse response = new UsersResponse();
        response.setUsers(list);
        return response;
    }

    UserResponse convert(StudentWrapper wrapper) {
        if(wrapper != null) {
            UserResponse response = new UserResponse();
            response.setUser(map(wrapper.getStudent(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private User map(Student instance, String districtId) {
        User user = new User();
        user.setSourcedId(instance.getStudentRefId());
        user.setStatus(StatusType.active);
        user.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStudentSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        user.setMetadata(metadata);

        user.setRole(RoleType.student);
        user.setGivenName(instance.getFirstName());
        user.setFamilyName(instance.getLastName());
        user.setMiddleName(instance.getMiddleName());
        //user.setIdentifier();

        //Account
        //user.setUsername();
        //user.setPassword();
        //user.setEnabledUser();

        //Orgs
        if(CollectionUtils.isNotEmpty(instance.getStudentEnrollments())) {
            instance.getStudentEnrollments().forEach(studentEnrollment -> {
                user.getOrgs().add(MappingUtil.buildGUIDRef("schools", studentEnrollment.getSchool().getSchoolRefId(), GUIDType.org));
                user.getOrgs().add(MappingUtil.buildGUIDRef("orgs", studentEnrollment.getSchool().getLea().getLeaRefId(), GUIDType.org));

                //Grades
                user.getGrades().add(studentEnrollment.getCurrentGradeLevel());
            });
        }

        //Agents - ie: Contacts
        if(CollectionUtils.isNotEmpty(instance.getStudentEmails())) {
            instance.getStudentContactRelationships().forEach(studentContactRelationship -> {
                if(studentContactRelationship.getStudentContact() != null) {
                    user.getAgents().add(MappingUtil.buildGUIDRef("users", studentContactRelationship.getStudentContact().getStudentContactRefId(), GUIDType.user));
                }
            });
        }

        //Email
        if(CollectionUtils.isNotEmpty(instance.getStudentEmails())) {
            Optional<StudentEmail> primaryEmail = instance.getStudentEmails().stream().filter(StudentEmail::getPrimaryEmailAddressIndicator).findFirst();
            primaryEmail.ifPresent(studentEmail -> user.setEmail(studentEmail.getEmailAddress()));
        }

        //Phone
        if(CollectionUtils.isNotEmpty(instance.getStudentTelephones())) {
            Optional<StudentTelephone> primaryPhone = instance.getStudentTelephones().stream().filter(StudentTelephone::getPrimaryTelephoneNumberIndicator).findFirst();
            primaryPhone.ifPresent(studentTelephone -> user.setPhone(studentTelephone.getTelephoneNumber()));

            Optional<StudentTelephone> smsPhone = instance.getStudentTelephones().stream().filter(studentTelephone -> "SMS".equalsIgnoreCase(studentTelephone.getTelephoneNumberTypeCode())).findFirst();
            smsPhone.ifPresent(studentTelephone -> user.setSms(studentTelephone.getTelephoneNumber()));
        }

        //Identifiers
        if(CollectionUtils.isNotEmpty(instance.getStudentIdentifiers())) {
            instance.getStudentIdentifiers().forEach(si -> user.getUserIds().add(new UserId(si.getIdentificationSystemCode(), si.getStudentId())));
        }
        return user;
    }
}