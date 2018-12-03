package org.ricone.api.xpress.controller;

import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class BaseController {
    BaseController() {
    }

    ControllerData getMetaData(HttpServletRequest request, HttpServletResponse response, Pageable pageRequest) throws Exception {
        return new ControllerData(request, response, pageRequest);
    }
}