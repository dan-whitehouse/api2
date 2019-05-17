package org.ricone.api.xpress.request.app;

import org.ricone.api.xpress.component.ControllerData;

public interface AppService {
	Application find(ControllerData metadata) throws Exception;

	Application findByAppId(ControllerData metadata, String appId) throws Exception;
}