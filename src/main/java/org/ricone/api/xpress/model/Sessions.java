/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sessionList"})
public class Sessions {
	@JsonProperty("sessionList")
	@ApiModelProperty(position = 1, value = "")
	private List<SessionList> sessionList;

	public Sessions() {
		sessionList = new ArrayList<>();
	}

	public Sessions(List<SessionList> sessionList) {
		super();
		this.sessionList = sessionList;
	}

	@JsonProperty("sessionList")
	public List<SessionList> getSessionList() {
		return sessionList;
	}

	@JsonProperty("sessionList")
	public void setSessionList(List<SessionList> sessionList) {
		this.sessionList = sessionList;
	}

	@Override
	public String toString() {
		return "Sessions{" + "sessionList=" + sessionList + '}';
	}
}