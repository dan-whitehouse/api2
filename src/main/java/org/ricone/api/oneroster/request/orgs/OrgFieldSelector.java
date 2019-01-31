package org.ricone.api.oneroster.request.orgs;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.model.Org;
import org.ricone.api.oneroster.model.OrgResponse;
import org.ricone.api.oneroster.model.OrgsResponse;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-22
 */

@Component("OneRoster:Orgs:OrgFieldSelector")
public class OrgFieldSelector extends BaseFieldSelector<Org> {
	public OrgFieldSelector() {
		super(Org.class);
	}

	OrgsResponse apply(OrgsResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			for (Org org : response.getData()) {
				selectBaseFields(org, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	OrgResponse apply(OrgResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Org instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "name")) {
			instance.setName(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "type")) {
			instance.setType(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "identifier")) {
			instance.setIdentifier(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "parent")) {
			instance.setParent(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "children")) {
			instance.setChildren(null);
		}
	}
}