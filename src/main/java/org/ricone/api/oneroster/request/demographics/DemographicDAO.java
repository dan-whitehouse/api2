package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface DemographicDAO {
	StudentWrapper getDemographic(ControllerData metadata, String refId);

	List<StudentWrapper> getAllDemographics(ControllerData metadata);
}