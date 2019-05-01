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
@JsonPropertyOrder({"enrollment"})
public class OtherEnrollments {
	@JsonProperty("enrollment")
	@ApiModelProperty(position = 1, value = "Enrollment information for the student")
	private List<Enrollment> enrollment;

	public OtherEnrollments() {
		enrollment = new ArrayList<>();
	}

	public OtherEnrollments(List<Enrollment> enrollment) {
		super();
		this.enrollment = enrollment;
	}

	@JsonProperty("enrollment")
	public List<Enrollment> getEnrollment() {
		return enrollment;
	}

	@JsonProperty("enrollment")
	public void setEnrollment(List<Enrollment> enrollment) {
		this.enrollment = enrollment;
	}

	@Override
	public String toString() {
		return "OtherEnrollments{" + "enrollment=" + enrollment + '}';
	}
}