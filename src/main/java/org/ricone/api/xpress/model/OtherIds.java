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

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"otherId"})
public class OtherIds {

	@JsonProperty("otherId")
	private List<OtherId> otherId;

	public OtherIds() {
		otherId = new ArrayList<>();
	}

	public OtherIds(List<OtherId> otherId) {
		super();
		this.otherId = otherId;
	}

	@JsonProperty("otherId")
	public List<OtherId> getOtherId() {
		return otherId;
	}

	@JsonProperty("otherId")
	public void setOtherId(List<OtherId> otherId) {
		this.otherId = otherId;
	}

	@Override
	public String toString() {
		return "OtherIds{" + "otherId=" + otherId + '}';
	}
}