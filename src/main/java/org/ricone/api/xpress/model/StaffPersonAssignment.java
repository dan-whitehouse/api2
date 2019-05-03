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
@JsonPropertyOrder({"leaRefId", "schoolRefId", "jobFunction"})
public class StaffPersonAssignment {
	@JsonProperty("leaRefId")
	@ApiModelProperty(position = 1, value = "The RefId for the LEA object")
	private String leaRefId;

	@JsonProperty("schoolRefId")
	@ApiModelProperty(position = 2, value = "The RefId of the school object")
	private String schoolRefId;

	@JsonProperty("jobFunction")
	@ApiModelProperty(position = 3, value = "The titles of employment, official status, or rank of education staff")
	private String jobFunction;

	public StaffPersonAssignment() {
	}

	public StaffPersonAssignment(String leaRefId, String schoolRefId, String jobFunction) {
		super();
		this.leaRefId = leaRefId;
		this.schoolRefId = schoolRefId;
		this.jobFunction = jobFunction;
	}

	@JsonProperty("leaRefId")
	public String getLeaRefId() {
		return leaRefId;
	}

	@JsonProperty("leaRefId")
	public void setLeaRefId(String leaRefId) {
		this.leaRefId = leaRefId;
	}

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("jobFunction")
	public String getJobFunction() {
		return jobFunction;
	}

	@JsonProperty("jobFunction")
	public void setJobFunction(String jobFunction) {
		this.jobFunction = jobFunction;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(leaRefId, schoolRefId, jobFunction).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "StaffPersonAssignment{" + "leaRefId='" + leaRefId + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", jobFunction='" + jobFunction + '\'' + '}';
	}
}