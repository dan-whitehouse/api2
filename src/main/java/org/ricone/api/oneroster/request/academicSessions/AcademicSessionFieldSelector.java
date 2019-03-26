package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.AcademicSession;
import org.ricone.api.oneroster.model.AcademicSessionResponse;
import org.ricone.api.oneroster.model.AcademicSessionsResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-28
 */

@Component("OneRoster:AcademicSessions:AcademicSessionFieldSelector")
public class AcademicSessionFieldSelector extends BaseFieldSelector<AcademicSession> {
	public AcademicSessionFieldSelector() {
		super(AcademicSession.class);
	}

	AcademicSessionsResponse apply(AcademicSessionsResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			for (AcademicSession academicSession : response.getData()) {
				selectBaseFields(academicSession, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	AcademicSessionResponse apply(AcademicSessionResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(AcademicSession instance, RequestData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "title")) {
			instance.setTitle(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "type")) {
			instance.setType(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "schoolYear")) {
			instance.setSchoolYear(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "beginDate")) {
			instance.setStartDate(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "endDate")) {
			instance.setEndDate(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "parent")) {
			instance.setParent(null);
		}
	}
}