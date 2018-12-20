package org.ricone.api.xpress.component;

import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.api.xpress.error.exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class PageData {
    private final String PAGE = "NavigationPage";
    private final String SIZE = "NavigationPageSize";
    private final String LAST_PAGE = "NavigationLastPage ";

    private Pageable pageable;

    PageData(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
        if(isPagingHeader(request)) {
            pageable = createPagableFromHeaders(request);
        }
        else if(isPagingParameter(request)) {
            pageable = createPagableFromParameters(request);
        }
        else {
            pageable = Pageable.unpaged();
        }

        setResponseHeaders(response);
    }

    Pageable getPageable() {
        return pageable;
    }

    private boolean isPagingHeader(HttpServletRequest request) {
        return NumberUtils.isDigits(request.getHeader(PAGE)) && NumberUtils.isDigits(request.getHeader(SIZE));
    }

    private boolean isPagingParameter(HttpServletRequest request) {
        return NumberUtils.isDigits(request.getParameter(PAGE)) && NumberUtils.isDigits(request.getParameter(SIZE));
    }

    private Pageable createPagableFromHeaders(HttpServletRequest request) throws BadRequestException {
        int page = Integer.parseInt(request.getHeader(PAGE));
        int size = Integer.parseInt(request.getHeader(SIZE));
        if(page < 1) {
            throw new BadRequestException("Page index must not be less than one!");
        }
        if(size < 1) {
            throw new BadRequestException("Page size must not be less than one!");
        }
        return PageRequest.of(page-1, size, null);
    }

    private Pageable createPagableFromParameters(HttpServletRequest request) throws BadRequestException {
        int page = Integer.parseInt(request.getParameter(PAGE));
        int size = Integer.parseInt(request.getParameter(SIZE));
        if(page < 1) {
            throw new BadRequestException("Page index must not be less than one!");
        }
        if(size < 1) {
            throw new BadRequestException("Page size must not be less than one!");
        }
        return PageRequest.of(page-1, size, null);
    }

    private void setResponseHeaders(HttpServletResponse response) {
        if(pageable.isPaged()) {
            response.setHeader(PAGE, String.valueOf(pageable.getPageNumber()-1));
            response.setHeader(SIZE, String.valueOf(pageable.getPageSize()));
        }
    }
}
