package org.ricone.api.oneroster.request.demographics;

import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.List;

public interface DemographicDAO {
	/* Find */
	StudentWrapper getDemographic(ControllerData metadata, String refId);

	List<StudentWrapper> getAllDemographics(ControllerData metadata);
}