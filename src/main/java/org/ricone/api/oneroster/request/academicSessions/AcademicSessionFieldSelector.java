package org.ricone.api.oneroster.request.academicSessions;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
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

	AcademicSessionsResponse apply(AcademicSessionsResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			for (AcademicSession academicSession : response.getData()) {
				selectBaseFields(academicSession, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	AcademicSessionResponse apply(AcademicSessionResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(AcademicSession instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "title")) {
			instance.setTitle(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "type")) {
			instance.setType(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "schoolYear")) {
			instance.setSchoolYear(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "beginDate")) {
			instance.setStartDate(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "endDate")) {
			instance.setEndDate(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "parent")) {
			instance.setParent(null);
		}
	}
}