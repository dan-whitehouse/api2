package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.model.Base;

public class BaseFieldSelector<T extends Base> {
	private Class<T> model;

	public BaseFieldSelector(Class<T> model) {
		this.model = model;
	}

	protected void selectBaseFields(T instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "sourcedId")) {
			instance.setSourcedId(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "status")) {
			instance.setStatus(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "dateLastModified")) {
			instance.setDateLastModified(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "metadata")) {
			instance.setMetadata(null);
		}

		selectFields(instance, metaData);
	}

	public void selectFields(T instance, ControllerData metaData) {
		//Will be overridden by child extension
	}

	public Class<T> getModelClass() {
		return model;
	}
}
