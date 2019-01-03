package org.ricone.api.xpress.component;

import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.api.xpress.error.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagingData {
	private final String PAGE = "NavigationPage";
	private final String SIZE = "NavigationPageSize";
	private final String LAST_PAGE = "NavigationLastPage ";

	private HttpServletResponse response;
	private Integer pageNumber = null;
	private Integer pageSize = null;
	private Integer totalObjects = null;

	PagingData(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
		this.response = response;

		if(isPagingHeader(request)) {
			pageNumber = Integer.parseInt(request.getHeader(PAGE));
			pageSize = Integer.parseInt(request.getHeader(SIZE));
		}
		else if(isPagingParameter(request)) {
			pageNumber = Integer.parseInt(request.getParameter(PAGE));
			pageSize = Integer.parseInt(request.getParameter(SIZE));
		}

		if(isPaged()) {
			if(pageNumber < 1) {
				throw new BadRequestException("NavigationPage must not be less than one!");
			}
			if(pageSize < 1) {
				throw new BadRequestException("NavigationPageSize must not be less than one!");
			}
		}
	}

	public boolean isPaged() {
		return pageNumber != null && pageSize != null;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotalObjects() {
		return totalObjects;
	}

	public void setTotalObjects(Integer totalObjects) {
		this.totalObjects = totalObjects;
		setResponseHeaders();
	}


	/* Private */
	private boolean isPagingHeader(HttpServletRequest request) {
		if(request.getHeader(PAGE) != null && request.getHeader(SIZE) != null) {
			return NumberUtils.isDigits(request.getHeader(PAGE)) && NumberUtils.isDigits(request.getHeader(SIZE));
		}
		return false;
	}

	private boolean isPagingParameter(HttpServletRequest request) {
		if(request.getParameter(PAGE) != null && request.getParameter(SIZE) != null) {
			return NumberUtils.isDigits(request.getParameter(PAGE)) && NumberUtils.isDigits(request.getParameter(SIZE));
		}
		return false;
	}

	private void setResponseHeaders() {
		if(isPaged()) {
			response.setHeader(PAGE, String.valueOf(pageNumber));
			response.setHeader(SIZE, String.valueOf(pageSize));

			boolean hasReminder = (totalObjects % pageSize) != 0;
			int lastPage = totalObjects/pageSize + (hasReminder ? 1 : 0);
			response.setHeader(LAST_PAGE, String.valueOf(lastPage));
		}
	}

	@Override
	public String toString() {
		return "Paging{" + "pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", totalObjects=" + totalObjects + '}';
	}
}
