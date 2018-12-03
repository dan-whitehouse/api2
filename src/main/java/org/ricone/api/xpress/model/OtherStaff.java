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

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"staffPersonReference", "teacherOfRecord", "percentResponsible"})
public class OtherStaff {

	@JsonProperty("staffPersonReference")
	private StaffPersonReference staffPersonReference;
	@JsonProperty("teacherOfRecord")
	private String teacherOfRecord;
	@JsonProperty("percentResponsible")
	private String percentResponsible;

	public OtherStaff() {
	}

	public OtherStaff(StaffPersonReference staffPersonReference, String teacherOfRecord, String percentResponsible) {
		super();
		this.staffPersonReference = staffPersonReference;
		this.teacherOfRecord = teacherOfRecord;
		this.percentResponsible = percentResponsible;
	}

	@JsonProperty("staffPersonReference")
	public StaffPersonReference getStaffPersonReference() {
		return staffPersonReference;
	}

	@JsonProperty("staffPersonReference")
	public void setStaffPersonReference(StaffPersonReference staffPersonReference) {
		this.staffPersonReference = staffPersonReference;
	}

	@JsonProperty("teacherOfRecord")
	public String getTeacherOfRecord() {
		return teacherOfRecord;
	}

	@JsonProperty("teacherOfRecord")
	public void setTeacherOfRecord(String teacherOfRecord) {
		this.teacherOfRecord = teacherOfRecord;
	}

	@JsonProperty("percentResponsible")
	public String getPercentResponsible() {
		return percentResponsible;
	}

	@JsonProperty("percentResponsible")
	public void setPercentResponsible(String percentResponsible) {
		this.percentResponsible = percentResponsible;
	}

	@Override
	public String toString() {
		return "OtherStaff{" + "staffPersonReference=" + staffPersonReference + ", teacherOfRecord='" + teacherOfRecord + '\'' + ", percentResponsible='" + percentResponsible + '\'' + '}';
	}
}