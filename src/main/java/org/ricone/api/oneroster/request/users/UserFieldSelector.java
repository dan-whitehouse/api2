package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.User;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster:Users:UserFieldSelector")
public class UserFieldSelector extends BaseFieldSelector<User> {
	public UserFieldSelector() {
		super(User.class);
	}

	UsersResponse apply(UsersResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			for (User user : response.getData()) {
				selectBaseFields(user, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	UserResponse apply(UserResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(User instance, RequestData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "username")) {
			instance.setUsername(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "userIds")) {
			instance.setUserIds(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "enabledUser")) {
			instance.setEnabledUser(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "givenName")) {
			instance.setGivenName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "familyName")) {
			instance.setFamilyName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "middleName")) {
			instance.setMiddleName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "role")) {
			instance.setRole(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "identifier")) {
			instance.setIdentifier(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "email")) {
			instance.setEmail(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "sms")) {
			instance.setSms(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "phone")) {
			instance.setPhone(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "agents")) {
			instance.setAgents(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "orgs")) {
			instance.setOrgs(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "grades")) {
			instance.setGrades(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "password")) {
			instance.setPassword(null);
		}
	}
}