package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.Demographic;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.ricone.api.oneroster.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster:Demographics:DemographicFieldSelector")
public class DemographicFieldSelector extends BaseFieldSelector<Demographic> {
	public DemographicFieldSelector() {
	}

	DemographicsResponse apply(DemographicsResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(User.class)) {
			for (Demographic demographic : response.getDemographics()) {
				selectBaseFields(demographic, metadata);
			}

			if (CollectionUtils.isEmpty(response.getDemographics())) {
				return null;
			}
		}
		return response;
	}

	DemographicResponse apply(DemographicResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(User.class)) {
			selectBaseFields(response.getDemographics(), metadata);
			if (response.getDemographics() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Demographic instance, ControllerData metaData) {
		/*if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "title")) {
			instance.setTitle(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "classType")) {
			instance.setClassType(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "location")) {
			instance.setLocation(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "classCode")) {
			instance.setClassCode(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "grades")) {
			instance.setGrades(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "subjects")) {
			instance.setSubjects(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "subjectCodes")) {
			instance.setSubjectCodes(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "course")) {
			instance.setCourse(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "school")) {
			instance.setSchool(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "periods")) {
			instance.setPeriods(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "terms")) {
			instance.setTerms(null);
		}*/
	}
}