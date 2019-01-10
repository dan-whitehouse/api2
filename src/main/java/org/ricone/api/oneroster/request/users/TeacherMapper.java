package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.*;

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
		user.setMetadata(mapMetadata(instance, districtId));

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
			Set<String> schools = new HashSet<>();
			Set<String> districts = new HashSet<>();

			instance.getStaffAssignments().forEach(sa -> {
				if(sa.getSchool() != null) {
					schools.add(sa.getSchool().getSchoolRefId());

					if(sa.getSchool().getLea() != null) {
						districts.add(sa.getSchool().getLea().getLeaRefId());
					}
				}
			});

			schools.forEach(schoolRefId -> {
				user.getOrgs().add(MappingUtil.buildGUIDRef("schools", schoolRefId, GUIDType.org));
			});

			districts.forEach(districtRefId -> {
				user.getOrgs().add(MappingUtil.buildGUIDRef("orgs", districtRefId, GUIDType.org));
			});
		}

		//Email
		Optional<StaffEmail> primaryEmail = instance.getStaffEmails().stream().filter(StaffEmail::getPrimaryEmailAddressIndicator).findFirst();
		primaryEmail.ifPresent(studentEmail -> user.setEmail(studentEmail.getEmailAddress()));

		//Phone

		//UserIds
		instance.getStaffIdentifiers().forEach(staffIdentifier -> {
			user.getUserIds().add(new UserId(staffIdentifier.getIdentificationSystemCode(), staffIdentifier.getStaffId()));
		});

		return user;
	}

	private Metadata mapMetadata(Staff instance, String districtId) {
		Metadata metadata = new Metadata();
		metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStaffSchoolYear());
		metadata.getAdditionalProperties().put("ricone.districtId", districtId);
		return metadata;
	}
}