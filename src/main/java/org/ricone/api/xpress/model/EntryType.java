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
@JsonPropertyOrder({"code", "otherCode"})
public class EntryType {

	@JsonProperty("code")
	private String code;
	@JsonProperty("otherCode")
	private List<OtherCode> otherCode;

	public EntryType() {
		otherCode = new ArrayList<>();
	}

	public EntryType(String code, List<OtherCode> otherCode) {
		super();
		this.code = code;
		this.otherCode = otherCode;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("otherCode")
	public List<OtherCode> getOtherCode() {
		return otherCode;
	}

	@JsonProperty("otherCode")
	public void setOtherCode(List<OtherCode> otherCode) {
		this.otherCode = otherCode;
	}

	@Override
	public String toString() {
		return "EntryType{" + "code='" + code + '\'' + ", otherCode=" + otherCode + '}';
	}
}