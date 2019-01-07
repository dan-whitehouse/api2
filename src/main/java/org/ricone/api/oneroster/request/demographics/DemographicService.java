package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;
import org.ricone.api.xpress.component.ControllerData;

public interface DemographicService {
	DemographicResponse getDemographic(ControllerData metadata, String refId) throws Exception;

	DemographicsResponse getAllDemographics(ControllerData metadata) throws Exception;
}