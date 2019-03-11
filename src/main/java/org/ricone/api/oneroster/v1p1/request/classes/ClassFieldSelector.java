package org.ricone.api.oneroster.v1p1.request.classes;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.model.ClassResponse;
import org.ricone.api.oneroster.model.ClassesResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster2:Classes:ClassFieldSelector")
public class ClassFieldSelector extends BaseFieldSelector<Class> {
	public ClassFieldSelector() {
		super(Class.class);
	}

	ClassesResponse apply(ClassesResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			for (Class c : response.getData()) {
				selectBaseFields(c, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	ClassResponse apply(ClassResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Class instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "title")) {
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
		}
	}
}