/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"staffPersonReference", "teacherOfRecord", "percentResponsible"})
public class PrimaryStaff {

	@JsonProperty("staffPersonReference")
	private StaffPersonReference staffPersonReference;
	@JsonProperty("teacherOfRecord")
	private Boolean teacherOfRecord;
	@JsonProperty("percentResponsible")
	private BigDecimal percentResponsible;

	public PrimaryStaff() {
	}

	public PrimaryStaff(StaffPersonReference staffPersonReference, Boolean teacherOfRecord, BigDecimal percentResponsible) {
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
	public Boolean getTeacherOfRecord() {
		return teacherOfRecord;
	}

	@JsonProperty("teacherOfRecord")
	public void setTeacherOfRecord(Boolean teacherOfRecord) {
		this.teacherOfRecord = teacherOfRecord;
	}

	@JsonProperty("percentResponsible")
	public BigDecimal getPercentResponsible() {
		return percentResponsible;
	}

	@JsonProperty("percentResponsible")
	public void setPercentResponsible(BigDecimal percentResponsible) {
		this.percentResponsible = percentResponsible;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(staffPersonReference, teacherOfRecord, percentResponsible).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "PrimaryStaff{" + "staffPersonReference=" + staffPersonReference + ", teacherOfRecord='" + teacherOfRecord + '\'' + ", percentResponsible='" + percentResponsible + '\'' + '}';
	}
}