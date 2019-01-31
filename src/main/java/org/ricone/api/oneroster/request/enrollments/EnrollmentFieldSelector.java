package org.ricone.api.oneroster.request.enrollments;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster:Enrollments:EnrollmentFieldSelector")
public class EnrollmentFieldSelector extends BaseFieldSelector<Enrollment> {
	public EnrollmentFieldSelector() {
		super(Enrollment.class);
	}

	EnrollmentsResponse apply(EnrollmentsResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			for (Enrollment enrollment : response.getData()) {
				selectBaseFields(enrollment, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	EnrollmentResponse apply(EnrollmentResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Enrollment instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "role")) {
			instance.setRole(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "primary")) {
			instance.setPrimary(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "user")) {
			instance.setUser(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "class")) {
			instance.setClass_(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "school")) {
			instance.setSchool(null);
		}
	}
}