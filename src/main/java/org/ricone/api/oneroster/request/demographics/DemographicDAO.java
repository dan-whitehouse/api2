package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface DemographicDAO {
	DemographicView getDemographic(ControllerData metadata, String refId);

	List<DemographicView> getAllDemographics(ControllerData metadata);
}