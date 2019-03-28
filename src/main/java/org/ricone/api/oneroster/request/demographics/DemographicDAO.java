package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.core.model.oneroster.QDemographic;
import org.ricone.api.oneroster.component.RequestData;

import java.util.List;

interface DemographicDAO {
	QDemographic getDemographic(RequestData metadata, String refId) throws Exception;

	List<QDemographic> getAllDemographics(RequestData metadata) throws Exception;

	int countAllDemographics(RequestData metadata) throws Exception;
}