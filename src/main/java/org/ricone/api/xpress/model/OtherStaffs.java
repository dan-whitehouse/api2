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
@JsonPropertyOrder({"otherStaff"})
public class OtherStaffs {

	@JsonProperty("otherStaff")
	private List<OtherStaff> otherStaff;

	public OtherStaffs() {
		otherStaff = new ArrayList<>();
	}

	public OtherStaffs(List<OtherStaff> otherStaff) {
		super();
		this.otherStaff = otherStaff;
	}

	@JsonProperty("otherStaff")
	public List<OtherStaff> getOtherStaff() {
		return otherStaff;
	}

	@JsonProperty("otherStaff")
	public void setOtherStaff(List<OtherStaff> otherStaff) {
		this.otherStaff = otherStaff;
	}

	@Override
	public String toString() {
		return "OtherStaffs{" + "otherStaff=" + otherStaff + '}';
	}
}