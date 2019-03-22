package org.ricone.api.oneroster.request.classes;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.RequestData;
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

	ClassesResponse apply(ClassesResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			for (Class c : response.getData()) {
				selectBaseFields(c, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	ClassResponse apply(ClassResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Class instance, RequestData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "title")) {
			instance.setTitle(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "classType")) {
			instance.setClassType(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "location")) {
			instance.setLocation(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "classCode")) {
			instance.setClassCode(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "grades")) {
			instance.setGrades(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "subjects")) {
			instance.setSubjects(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "subjectCodes")) {
			instance.setSubjectCodes(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "course")) {
			instance.setCourse(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "school")) {
			instance.setSchool(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "periods")) {
			instance.setPeriods(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "terms")) {
			instance.setTerms(null);
		}
	}
}