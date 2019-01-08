package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("OneRoster:Users:TeacherMapper")
class TeacherMapper {
	TeacherMapper() {
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
		if(wrapper != null) {
			UserResponse response = new UserResponse();
			response.setUser(map(wrapper.getStaff(), wrapper.getDistrictId()));
			return response;
		}
		return null;
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
				user.getOrgs().add(MappingUtil.buildGUIDRef("schools", sa.getSchool().getSchoolRefId(), GUIDType.org));
				user.getOrgs().add(MappingUtil.buildGUIDRef("orgs", sa.getSchool().getLea().getLeaRefId(), GUIDType.org));
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