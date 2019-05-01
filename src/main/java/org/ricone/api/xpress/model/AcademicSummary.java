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
@JsonPropertyOrder({"cumulativeWeightedGpa", "termWeightedGpa", "classRank"})
public class AcademicSummary {
	@JsonProperty("cumulativeWeightedGpa")
	@ApiModelProperty(position = 1, value = "A measure of average performance in all courses taken by a person during his or her school career as determined for record-keeping purposes. This is obtained by dividing the total grade points received by the total number of credits attempted. This usually includes grade points received and credits attempted in his or her current school as well as those transferred from schools in which the person was previously enrolled")
	private String cumulativeWeightedGpa;

	@JsonProperty("termWeightedGpa")
	@ApiModelProperty(position = 2, value = "A measure of average performance in all courses taken by a person during a given session. This is obtained by dividing the total grade points received by the number of credits attempted for the same session")
	private String termWeightedGpa;

	@JsonProperty("classRank")
	@ApiModelProperty(position = 3, value = "The academic rank of a student in relation to his or her high school graduating class (e.g., 1, 2, 3) based on high school GPA")
	private String classRank;

	public AcademicSummary() {
	}

	public AcademicSummary(String cumulativeWeightedGpa, String termWeightedGpa, String classRank) {
		super();
		this.cumulativeWeightedGpa = cumulativeWeightedGpa;
		this.termWeightedGpa = termWeightedGpa;
		this.classRank = classRank;
	}

	@JsonProperty("cumulativeWeightedGpa")
	public String getCumulativeWeightedGpa() {
		return cumulativeWeightedGpa;
	}

	@JsonProperty("cumulativeWeightedGpa")
	public void setCumulativeWeightedGpa(String cumulativeWeightedGpa) {
		this.cumulativeWeightedGpa = cumulativeWeightedGpa;
	}

	@JsonProperty("termWeightedGpa")
	public String getTermWeightedGpa() {
		return termWeightedGpa;
	}

	@JsonProperty("termWeightedGpa")
	public void setTermWeightedGpa(String termWeightedGpa) {
		this.termWeightedGpa = termWeightedGpa;
	}

	@JsonProperty("classRank")
	public String getClassRank() {
		return classRank;
	}

	@JsonProperty("classRank")
	public void setClassRank(String classRank) {
		this.classRank = classRank;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(cumulativeWeightedGpa, termWeightedGpa, classRank).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "AcademicSummary{" + "cumulativeWeightedGpa='" + cumulativeWeightedGpa + '\'' + ", termWeightedGpa='" + termWeightedGpa + '\'' + ", classRank='" + classRank + '\'' + '}';
	}
}