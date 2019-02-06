package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.util.Util;
import org.ricone.security.jwt.Application;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2018-11-20
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class ControllerData {
	private Logger logger = LogManager.getLogger(ControllerData.class);
	public static final String LEA_LOCAL_ID = "leaId";
	public static final String SCHOOL_YEAR_KEY = "SchoolYear";

	/* MetaData Vars */
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PagingData pageable;
	private SortingData sortable;
	private FieldSelectionData fieldSelection;
	private FilteringData filterable;
	private FilteringDataTest filterableTest;
	private String providerId;
	private Application application;

	public ControllerData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super();
		this.request = request;
		this.response = response;
		this.pageable = new PagingData(request, response);
		this.sortable = new SortingData(request);
		this.fieldSelection = new FieldSelectionData(request);
		this.filterable = new FilteringData(request);
		this.filterableTest = new FilteringDataTest(request);
		this.application = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	// Custom Methods - Headers
	public String getHeader(String header) {
		return request.getHeader(header);
	}

	public boolean isHeaderValue(String headerName, String value) { return StringUtils.equalsIgnoreCase(request.getHeader(headerName), value); }

	public boolean isRequestParameterValue(String param, String value) { return StringUtils.equalsIgnoreCase(request.getParameter(param), value); }

	public boolean hasUrlQueryParameter(String param) { return StringUtils.isNotBlank(request.getParameter(param)); }

	// Custom Methods - School Year Rollover
	public boolean hasSchoolYear() {
		String schoolYear = request.getHeader(SCHOOL_YEAR_KEY);
		return StringUtils.isNotBlank(schoolYear) && Util.isValidSchoolYear(schoolYear);
	}

	public String getSchoolYear() {
		return request.getHeader(SCHOOL_YEAR_KEY);
	}
	// Custom Methods - Basic_Single & Basic_Multi App


	// GETTERS & SETTERS
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Application getApplication() {
		return application;
	}

	public PagingData getPaging() {
		return pageable;
	}

	public SortingData getSorting() { return sortable; }

	public FieldSelectionData getFieldSelection() { return fieldSelection; }

	public FilteringData getFiltering() {
		return filterable;
	}

	public FilteringDataTest getFilterableTest() {
		return filterableTest;
	}
}