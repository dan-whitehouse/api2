package org.ricone.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.event.Level;

public class Log {
	private String uuid;
	private Level level;
	private String provider;
	private String component;
	private String app;
	private String request;
	private String requestHeaders;
	private String requestDatetime;
	private String responseDatetime;
	private String duration;
	private String size;
	private String statusCode;
	private String errorMessage;
	private String errorDescription;

	public Log() {
		super();
	}

	public Log(String uuid, Level level, String provider, String component, String app, String request, String requestHeaders, String requestDatetime, String responseDatetime, String duration, String size, String statusCode, String errorMessage, String errorDescription) {
		this.uuid = uuid;
		this.level = level;
		this.provider = provider;
		this.component = component;
		this.app = app;
		this.request = request;
		this.requestHeaders = requestHeaders;
		this.requestDatetime = requestDatetime;
		this.responseDatetime = responseDatetime;
		this.duration = duration;
		this.size = size;
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
	}

	String toLog() {
		return "UUID='" + uuid + '\'' + ", Severity='" + level + '\'' + ", Provider='" + provider + '\'' + ", Component='" + component + '\'' + ", App='" + app + '\'' + ", Request='" + request + '\'' + ", RequestHeaders='" + requestHeaders + '\'' + ", RequestDateTime='" + requestDatetime + '\'' + ", ResponseDateTime='" + responseDatetime + '\'' + ", Duration='" + duration + '\'' + ", Size='" + size + '\'' + ", StatusCode='" + statusCode + '\'';
	}

	String toLogWithErrors() {
		return "UUID='" + uuid + '\'' + ", Severity='" + level + '\'' + ", Provider='" + provider + '\'' + ", Component='" + component + '\'' + ", App='" + app + '\'' + ", Request='" + request + '\'' + ", RequestHeaders='" + requestHeaders + '\'' + ", RequestDateTime='" + requestDatetime + '\'' + ", ResponseDateTime='" + responseDatetime + '\'' + ", Duration='" + duration + '\'' + ", Size='" + size + '\'' + ", StatusCode='" + statusCode + '\'' + ", ErrorMessage='" + errorMessage + '\'' + ", ErrorDescription='" + errorDescription + '\'';
	}

	String toLogWithStacktrace() {
		return "UUID='" + uuid + '\'' + ", Severity='" + level + '\'' + ", Provider='" + provider + '\'' + ", Component='" + component + '\'' + ", StatusCode='" + statusCode + '\'' + ", ErrorMessage='" + errorMessage + '\'' + ", ErrorDescription='" + StringUtils.normalizeSpace(errorDescription) + '\'';
	}
}