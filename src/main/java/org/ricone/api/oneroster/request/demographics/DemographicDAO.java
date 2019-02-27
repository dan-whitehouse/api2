package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface DemographicDAO {
	DemographicView getDemographic(ControllerData metadata, String refId) throws Exception;

	List<DemographicView> getAllDemographics(ControllerData metadata) throws Exception;

	int countAllDemographics(ControllerData metadata) throws Exception;
}