package org.ricone.api.xpress.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
    public BaseController() {
    }

    protected ControllerData getMetaData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ControllerData(request, response);
    }
}