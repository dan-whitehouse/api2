package org.ricone.api.oneroster.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
    public BaseController() {
    }

    protected RequestData getMetaData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new RequestData(request, response);
    }
}