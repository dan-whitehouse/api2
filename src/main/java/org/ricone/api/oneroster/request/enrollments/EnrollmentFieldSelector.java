package org.ricone.api.oneroster.request.enrollments;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.Enrollment;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster2:Enrollments:EnrollmentFieldSelector")
public class EnrollmentFieldSelector extends BaseFieldSelector<Enrollment> {
	public EnrollmentFieldSelector() {
		super(Enrollment.class);
	}

	EnrollmentsResponse apply(EnrollmentsResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			for (Enrollment enrollment : response.getData()) {
				selectBaseFields(enrollment, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	EnrollmentResponse apply(EnrollmentResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Enrollment instance, RequestData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "role")) {
			instance.setRole(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "primary")) {
			instance.setPrimary(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "user")) {
			instance.setUser(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "class")) {
			instance.setClass_(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "school")) {
			instance.setSchool(null);
		}
	}
}