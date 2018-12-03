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
@JsonPropertyOrder({"staffPersonAssignment"})
public class OtherAssignments {

	@JsonProperty("staffPersonAssignment")
	private List<StaffPersonAssignment> staffPersonAssignment;

	public OtherAssignments() {
		staffPersonAssignment = new ArrayList<>();
	}

	public OtherAssignments(List<StaffPersonAssignment> staffPersonAssignment) {
		super();
		this.staffPersonAssignment = staffPersonAssignment;
	}

	@JsonProperty("staffPersonAssignment")
	public List<StaffPersonAssignment> getStaffPersonAssignment() {
		return staffPersonAssignment;
	}

	@JsonProperty("staffPersonAssignment")
	public void setStaffPersonAssignment(List<StaffPersonAssignment> staffPersonAssignment) {
		this.staffPersonAssignment = staffPersonAssignment;
	}

	@Override
	public String toString() {
		return "OtherAssignments{" + "staffPersonAssignment=" + staffPersonAssignment + '}';
	}
}