package org.ricone.api.oneroster.v1p1.request.demographics;

import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;

interface DemographicService {
	DemographicResponse getDemographic(ControllerData metadata, String refId) throws Exception;

	DemographicsResponse getAllDemographics(ControllerData metadata) throws Exception;
}