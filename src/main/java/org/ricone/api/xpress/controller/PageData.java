package org.ricone.api.xpress.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.error.exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

class PageData {
    private Pageable pageable;

    PageData(HttpServletRequest request) throws BadRequestException {
        if(isPagingHeader(request)) {
            pageable = createPagableFromHeaders(request);
        }
        else if(isPagingParameter(request)) {
            pageable = createPagableFromParameters(request);
        }
        else {
            pageable = Pageable.unpaged();
        }
    }

    private boolean isPagingHeader(HttpServletRequest request) {
        return NumberUtils.isDigits(request.getHeader("NavigationPage")) && NumberUtils.isDigits(request.getHeader("NavigationPageSize"));
    }

    private boolean isPagingParameter(HttpServletRequest request) {
        return NumberUtils.isDigits(request.getParameter("NavigationPage")) && NumberUtils.isDigits(request.getParameter("NavigationPageSize"));
    }

    private Pageable createPagableFromHeaders(HttpServletRequest request) throws BadRequestException {
        int page = Integer.parseInt(request.getHeader("NavigationPage"));
        int size = Integer.parseInt(request.getHeader("NavigationPageSize"));
        if(page < 1) {
            throw new BadRequestException("Page index must not be less than one!");
        }
        return PageRequest.of(page-1, size, null);
    }

    private Pageable createPagableFromParameters(HttpServletRequest request) throws BadRequestException {
        int page = Integer.parseInt(request.getParameter("NavigationPage"));
        int size = Integer.parseInt(request.getParameter("NavigationPageSize"));
        if(page < 1) {
            throw new BadRequestException("Page index must not be less than one!");
        }
        return PageRequest.of(page-1, size, null);
    }

    public Pageable getPageable() {
        return pageable;
    }
}
