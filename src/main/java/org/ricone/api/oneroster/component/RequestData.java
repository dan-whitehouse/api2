package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.security.Application;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2018-11-20
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class RequestData {
	private Logger logger = LogManager.getLogger(RequestData.class);

	/* MetaData Vars */
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Paging pageable;
	private Sorter sorter;
	private FieldSelector fieldSelector;
	private Filterer filterer;
	private String providerId;
	private Application application;

	public RequestData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super();
		this.request = request;
		this.response = response;
		this.pageable = new Paging(request, response);
		this.sorter = new Sorter(request);
		this.fieldSelector = new FieldSelector(request);
		this.filterer = new Filterer(request);
		this.application = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	// Custom Methods - Headers
	public String getHeader(String header) {
		return request.getHeader(header);
	}

	public boolean isHeaderValue(String headerName, String value) { return StringUtils.equalsIgnoreCase(request.getHeader(headerName), value); }

	public boolean isRequestParameterValue(String param, String value) { return StringUtils.equalsIgnoreCase(request.getParameter(param), value); }

	public boolean hasUrlQueryParameter(String param) { return StringUtils.isNotBlank(request.getParameter(param)); }


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

	public Paging getPaging() {
		return pageable;
	}

	public Sorter getSorter() { return sorter; }

	public FieldSelector getFieldSelector() { return fieldSelector; }

	public Filterer getFilterer() {
		return filterer;
	}
}