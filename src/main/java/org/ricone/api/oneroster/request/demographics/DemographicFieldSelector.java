package org.ricone.api.oneroster.request.demographics;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
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

	DemographicsResponse apply(DemographicsResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
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
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getDemographics(), metadata);
			if (response.getDemographics() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Demographic instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "birthDate")) {
			instance.setBirthDate(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "sex")) {
			instance.setSex(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "americanIndianOrAlaskaNative")) {
			instance.setAmericanIndianOrAlaskaNative(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "asian")) {
			instance.setAsian(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "blackOrAfricanAmerican")) {
			instance.setBlackOrAfricanAmerican(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "nativeHawaiianOrOtherPacificIslander")) {
			instance.setNativeHawaiianOrOtherPacificIslander(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "white")) {
			instance.setWhite(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "hispanicOrLatinoEthnicity")) {
			instance.setHispanicOrLatinoEthnicity(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "countryOfBirthCode")) {
			instance.setCountryOfBirthCode(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "stateOfBirthAbbreviation")) {
			instance.setStateOfBirthAbbreviation(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "cityOfBirth")) {
			instance.setCityOfBirth(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "publicSchoolResidenceStatus")) {
			instance.setPublicSchoolResidenceStatus(null);
		}
	}
}