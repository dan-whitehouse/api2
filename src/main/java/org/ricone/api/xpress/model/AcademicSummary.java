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
@JsonPropertyOrder({"cumulativeWeightedGpa", "termWeightedGpa", "classRank"})
public class AcademicSummary {

	@JsonProperty("cumulativeWeightedGpa")
	private String cumulativeWeightedGpa;
	@JsonProperty("termWeightedGpa")
	private String termWeightedGpa;
	@JsonProperty("classRank")
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

	@Override
	public String toString() {
		return "AcademicSummary{" + "cumulativeWeightedGpa='" + cumulativeWeightedGpa + '\'' + ", termWeightedGpa='" + termWeightedGpa + '\'' + ", classRank='" + classRank + '\'' + '}';
	}
}