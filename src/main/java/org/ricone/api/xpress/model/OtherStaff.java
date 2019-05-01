/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"staffPersonReference", "teacherOfRecord", "percentResponsible"})
public class OtherStaff {
	@JsonProperty("staffPersonReference")
	@ApiModelProperty(position = 1, value = "A collection of information about a staff person")
	private StaffPersonReference staffPersonReference;

	@JsonProperty("teacherOfRecord")
	@ApiModelProperty(position = 2, value = "Staff member who has a Teacher of Record responsibility for a Class Section based upon the state's definition of Teacher of Record")
	private Boolean teacherOfRecord;

	@JsonProperty("percentResponsible")
	@ApiModelProperty(position = 3, value = "A percentage used to weight the educator's assigned responsibility for student learning in a Class Section, particularly when more than one educator is assigned to the class section")
	private BigDecimal percentResponsible;

	public OtherStaff() {
	}

	public OtherStaff(StaffPersonReference staffPersonReference, Boolean teacherOfRecord, BigDecimal percentResponsible) {
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
		return "OtherStaff{" + "staffPersonReference=" + staffPersonReference + ", teacherOfRecord='" + teacherOfRecord + '\'' + ", percentResponsible='" + percentResponsible + '\'' + '}';
	}
}