package org.ricone.api.oneroster.v1p1.request.demographics;

import org.ricone.api.core.model.v1p1.QDemographic;
import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface DemographicDAO {
	QDemographic getDemographic(ControllerData metadata, String refId) throws Exception;

	List<QDemographic> getAllDemographics(ControllerData metadata) throws Exception;

	int countAllDemographics(ControllerData metadata) throws Exception;
}