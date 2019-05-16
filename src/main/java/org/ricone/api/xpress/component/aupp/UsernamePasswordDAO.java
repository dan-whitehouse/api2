package org.ricone.api.xpress.component.aupp;

import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;

import java.util.List;

public interface UsernamePasswordDAO {
	void initAppLeas(ControllerData metadata, String refId);
}