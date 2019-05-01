/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sessionType", "sessionCode", "description", "markingTerm", "schedulingTerm", "linkedSessionCode", "startDate", "endDate"})
public class SessionList {
	@JsonProperty("sessionType")
	@ApiModelProperty(position = 1, value = "A prescribed span of time when an education institution is open, instruction is provided, and students are under the direction and guidance of teachers and/or education institution administration. A session may be interrupted by one or more vacations")
	private String sessionType;

	@JsonProperty("sessionCode")
	@ApiModelProperty(position = 2, value = "A local code given to the session, usually for a session that represents a term within the school year such as a marking term")
	private String sessionCode;

	@JsonProperty("description")
	@ApiModelProperty(position = 3, value = "A short description of the session")
	private String description;

	@JsonProperty("markingTerm")
	@ApiModelProperty(position = 4, value = "Indicates that the session is a marking term")
	private String markingTerm;

	@JsonProperty("schedulingTerm")
	@ApiModelProperty(position = 5, value = "Indicates that the session is a scheduling term.")
	private String schedulingTerm;

	@JsonProperty("linkedSessionCode")
	@ApiModelProperty(position = 6, value = "Enables sessions to be linked (e.g. link a marking term to a scheduling term)")
	private String linkedSessionCode;

	@JsonProperty("startDate")
	@ApiModelProperty(position = 7, value = "The year, month and day on which a session begins")
	private String startDate;

	@JsonProperty("endDate")
	@ApiModelProperty(position = 8, value = "The year, month and day on which a session ends")
	private String endDate;

	public SessionList() {
	}

	public SessionList(String sessionType, String sessionCode, String description, String markingTerm, String schedulingTerm, String linkedSessionCode, String startDate, String endDate) {
		super();
		this.sessionType = sessionType;
		this.sessionCode = sessionCode;
		this.description = description;
		this.markingTerm = markingTerm;
		this.schedulingTerm = schedulingTerm;
		this.linkedSessionCode = linkedSessionCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@JsonProperty("sessionType")
	public String getSessionType() {
		return sessionType;
	}

	@JsonProperty("sessionType")
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	@JsonProperty("sessionCode")
	public String getSessionCode() {
		return sessionCode;
	}

	@JsonProperty("sessionCode")
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("markingTerm")
	public String getMarkingTerm() {
		return markingTerm;
	}

	@JsonProperty("markingTerm")
	public void setMarkingTerm(String markingTerm) {
		this.markingTerm = markingTerm;
	}

	@JsonProperty("schedulingTerm")
	public String getSchedulingTerm() {
		return schedulingTerm;
	}

	@JsonProperty("schedulingTerm")
	public void setSchedulingTerm(String schedulingTerm) {
		this.schedulingTerm = schedulingTerm;
	}

	@JsonProperty("linkedSessionCode")
	public String getLinkedSessionCode() {
		return linkedSessionCode;
	}

	@JsonProperty("linkedSessionCode")
	public void setLinkedSessionCode(String linkedSessionCode) {
		this.linkedSessionCode = linkedSessionCode;
	}

	@JsonProperty("startDate")
	public String getStartDate() {
		return startDate;
	}

	@JsonProperty("startDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(sessionType, sessionCode, description, markingTerm, schedulingTerm, linkedSessionCode, startDate, endDate).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "SessionList{" + "sessionType='" + sessionType + '\'' + ", sessionCode='" + sessionCode + '\'' + ", description='" + description + '\'' + ", markingTerm='" + markingTerm + '\'' + ", schedulingTerm='" + schedulingTerm + '\'' + ", linkedSessionCode='" + linkedSessionCode + '\'' + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
	}
}