package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.model.Base;

public abstract class BaseFieldSelector<T extends Base> {
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

	protected abstract void selectFields(T instance, ControllerData metaData);

	protected Class<T> getModelClass() {
		return model;
	}
}
