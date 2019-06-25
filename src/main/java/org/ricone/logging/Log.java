package org.ricone.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.event.Level;

import java.time.LocalDateTime;

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public String getRequestDatetime() {
		return requestDatetime;
	}

	public void setRequestDatetime(String requestDatetime) {
		this.requestDatetime = requestDatetime;
	}

	public String getResponseDatetime() {
		return responseDatetime;
	}

	public void setResponseDatetime(String responseDatetime) {
		this.responseDatetime = responseDatetime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	String getLog() {
		return "UUID='" + uuid + '\'' + ", Severity='" + level + '\'' + ", Provider='" + provider + '\'' + ", Component='" + component + '\'' + ", App='" + app + '\'' + ", Request='" + request + '\'' + ", RequestHeaders='" + requestHeaders + '\'' + ", RequestDateTime='" + requestDatetime + '\'' + ", ResponseDateTime='" + responseDatetime + '\'' + ", Duration='" + duration + '\'' + ", Size='" + size + '\'' + ", StatusCode='" + statusCode + '\'';
	}

	String getLogWithErrors() {
		return "UUID='" + uuid + '\'' + ", Severity='" + level + '\'' + ", Provider='" + provider + '\'' + ", Component='" + component + '\'' + ", App='" + app + '\'' + ", Request='" + request + '\'' + ", RequestHeaders='" + requestHeaders + '\'' + ", RequestDateTime='" + requestDatetime + '\'' + ", ResponseDateTime='" + responseDatetime + '\'' + ", Duration='" + duration + '\'' + ", Size='" + size + '\'' + ", StatusCode='" + statusCode + '\'' + ", ErrorMessage='" + errorMessage + '\'' + ", ErrorDescription='" + errorDescription + '\'';
	}

	String getLogWithStacktrace() {
		return "UUID='" + uuid + '\'' + ", Severity='" + level + '\'' + ", Provider='" + provider + '\'' + ", Component='" + component + '\'' + ", StatusCode='" + statusCode + '\'' + ", ErrorMessage='" + errorMessage + '\'' + ", ErrorDescription='" + StringUtils.normalizeSpace(errorDescription) + '\'';
	}
}