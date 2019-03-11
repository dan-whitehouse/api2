package org.ricone.api.oneroster.v1p1.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.User;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster2:Users:UserFieldSelector")
public class UserFieldSelector extends BaseFieldSelector<User> {
	public UserFieldSelector() {
		super(User.class);
	}

	UsersResponse apply(UsersResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			for (User user : response.getData()) {
				selectBaseFields(user, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	UserResponse apply(UserResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(User instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "username")) {
			instance.setUsername(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "userIds")) {
			instance.setUserIds(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "enabledUser")) {
			instance.setEnabledUser(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "givenName")) {
			instance.setGivenName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "familyName")) {
			instance.setFamilyName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "middleName")) {
			instance.setMiddleName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "role")) {
			instance.setRole(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "identifier")) {
			instance.setIdentifier(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "email")) {
			instance.setEmail(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "sms")) {
			instance.setSms(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "phone")) {
			instance.setPhone(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "agents")) {
			instance.setAgents(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "orgs")) {
			instance.setOrgs(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "grades")) {
			instance.setGrades(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "password")) {
			instance.setPassword(null);
		}
	}
}