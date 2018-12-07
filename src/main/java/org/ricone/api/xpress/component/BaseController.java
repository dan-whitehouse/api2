package org.ricone.api.xpress.component;

import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    public BaseController() {
    }

    protected ControllerData getMetaData(HttpServletRequest request, HttpServletResponse response, Pageable pageRequest) throws Exception {
        return new ControllerData(request, response, pageRequest);
    }
}