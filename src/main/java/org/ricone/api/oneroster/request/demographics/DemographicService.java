package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.DemographicResponse;
import org.ricone.api.oneroster.model.DemographicsResponse;

interface DemographicService {
	DemographicResponse getDemographic(RequestData metadata, String refId) throws Exception;

	DemographicsResponse getAllDemographics(RequestData metadata) throws Exception;
}