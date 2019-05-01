/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"applicableEducationLevel"})
public class ApplicableEducationLevels {
	@JsonProperty("applicableEducationLevel")
	@ApiModelProperty(position = 1, value = "The education level, grade level or primary instructional level at which a course is intended")
	private List<String> applicableEducationLevel;

	public ApplicableEducationLevels() {
		applicableEducationLevel = new ArrayList<>();
	}

	public ApplicableEducationLevels(List<String> applicableEducationLevel) {
		super();
		this.applicableEducationLevel = applicableEducationLevel;
	}

	@JsonProperty("applicableEducationLevel")
	public List<String> getApplicableEducationLevel() {
		return applicableEducationLevel;
	}

	@JsonProperty("applicableEducationLevel")
	public void setApplicableEducationLevel(List<String> applicableEducationLevel) {
		this.applicableEducationLevel = applicableEducationLevel;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return applicableEducationLevel.isEmpty();
	}

	@Override
	public String toString() {
		return "ApplicableEducationLevels{" + "applicableEducationLevel=" + applicableEducationLevel + '}';
	}
}