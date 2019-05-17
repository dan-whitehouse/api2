package org.ricone.api.xpress.request.validation;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.validation.Validation;
import org.ricone.api.xpress.request.app.Application;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-17
 */

public interface ValidationService {
	Validation findByLeaRefId(ControllerData metadata, String refId) throws Exception;
}