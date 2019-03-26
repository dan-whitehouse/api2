package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.Demographic;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-28
 */

@Component("OneRoster:Demographics:DemographicFieldSelector")
public class DemographicFieldSelector extends BaseFieldSelector<Demographic> {
	public DemographicFieldSelector() {
		super(Demographic.class);
	}

	DemographicsResponse apply(DemographicsResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			for (Demographic demographic : response.getData()) {
				selectBaseFields(demographic, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	DemographicResponse apply(DemographicResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Demographic instance, RequestData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "birthDate")) {
			instance.setBirthDate(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "sex")) {
			instance.setSex(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "americanIndianOrAlaskaNative")) {
			instance.setAmericanIndianOrAlaskaNative(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "asian")) {
			instance.setAsian(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "blackOrAfricanAmerican")) {
			instance.setBlackOrAfricanAmerican(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "nativeHawaiianOrOtherPacificIslander")) {
			instance.setNativeHawaiianOrOtherPacificIslander(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "white")) {
			instance.setWhite(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "hispanicOrLatinoEthnicity")) {
			instance.setHispanicOrLatinoEthnicity(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "countryOfBirthCode")) {
			instance.setCountryOfBirthCode(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "stateOfBirthAbbreviation")) {
			instance.setStateOfBirthAbbreviation(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "cityOfBirth")) {
			instance.setCityOfBirth(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "publicSchoolResidenceStatus")) {
			instance.setPublicSchoolResidenceStatus(null);
		}
	}
}