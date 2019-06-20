package org.ricone.logging;

import org.slf4j.event.Level;

public class LogBuilder {

    private Level level;
    private String provider;
    private String component;
    private String app;
    private String request;
    private String requestHeaders;
    private String requestDatetime;
    private String responseDatetime;
    private String duration;
    private String statusCode;
    private String errorMessage;
    private String errorDescription;

    LogBuilder() {

    }

    public LogBuilder level(Level level) {
        this.level = level;
        return this;
    }

    public LogBuilder provider(String provider) {
        this.provider = provider;
        return this;
    }

    public LogBuilder component(String component) {
        this.component = component;
        return this;
    }

    public LogBuilder app(String app) {
        this.app = app;
        return this;
    }

    public LogBuilder request(String request) {
        this.request = request;
        return this;
    }

    public LogBuilder requestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    public LogBuilder requestDatetime(String requestDatetime) {
        this.requestDatetime = requestDatetime;
        return this;
    }

    public LogBuilder responseDatetime(String responseDatetime) {
        this.responseDatetime = responseDatetime;
        return this;
    }

    public LogBuilder duration(String duration) {
        this.duration = duration;
        return this;
    }

    public LogBuilder statusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public LogBuilder errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public LogBuilder errorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
        return this;
    }

    public Log build() {
        return new Log(level, provider, component, app, request, requestHeaders, requestDatetime, responseDatetime, duration, statusCode, errorMessage, errorDescription);
    }
}