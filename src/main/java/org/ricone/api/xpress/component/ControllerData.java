package org.ricone.api.xpress.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.error.exception.BadRequestException;
import org.ricone.security.jwt.Application;
import org.ricone.api.xpress.util.Util;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
	private final String CHANGES_SINCE_MARKER = "changesSinceMarker";
	private final String AUPP_CREATE = "createUsers";
	private final String AUPP_GET = "getUsers";
	private final String AUPP_DELETE = "deleteUsers";
	private final String AUPP_DELETE_PASSWORDS = "deletePasswords";
	private final String AUPP_DELETE_USERNAMES = "usernames";
	public final String ID_TYPE = "IdType";
	private final String BASIC_SINGLE = "Basic_Single";
	private final String BASIC_MULTI = "Basic_Multi";
	private final String SORT = "sort";
	private final String ORDER_BY = "orderBy";

	/* MetaData Vars */
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PagingData pageable;
	private String providerId;
	private Application application;


	public ControllerData(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
		super();
		this.request = request;
		this.response = response;
		this.pageable = new PagingData(request, response);
		this.application = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	// Custom Methods - Headers
	public String getHeader(String header) {
		return request.getHeader(header);
	}

	public boolean isHeaderValue(String headerName, String value) { return StringUtils.equalsIgnoreCase(request.getHeader(headerName), value); }

	public boolean isRequestParameterValue(String param, String value) { return StringUtils.equalsIgnoreCase(request.getParameter(param), value); }

	public boolean hasUrlQueryParameter(String param) { return StringUtils.isNotBlank(request.getParameter(param)); }


	// Custom Methods - Query Parameters - AUPP
	public boolean isCreateAUPP() {
		return "true".equalsIgnoreCase(request.getParameter(AUPP_CREATE));
	}

	public boolean isGetAUPP() {
		return "true".equalsIgnoreCase(request.getParameter(AUPP_GET));
	}

	public boolean isDeleteAUPP() { return "true".equalsIgnoreCase(request.getParameter(AUPP_DELETE)); }

	public boolean isDeletePasswordsAUPP() { return "true".equalsIgnoreCase(request.getParameter(AUPP_DELETE_PASSWORDS)); }

	public boolean isDeleteUsernamesAUPP() { return "true".equalsIgnoreCase(request.getParameter(AUPP_DELETE_USERNAMES)); }


	// Custom Methods - Changes Since - Event Log
	public boolean hasChangesSinceMarker() { return request.getParameter(CHANGES_SINCE_MARKER) != null; }

	private String getChangesSinceMarker() {
		return request.getParameter(CHANGES_SINCE_MARKER);
	}

	public Date getChangesSinceLocalDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime ldt = LocalDateTime.parse(getChangesSinceMarker(), formatter);
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

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
}