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
@JsonPropertyOrder({"studentRefId", "relationshipCode", "restrictions", "livesWith", "primaryContactIndicator", "emergencyContactIndicator", "financialResponsibilityIndicator", "custodialIndicator", "communicationsIndicator", "contactSequence"})
public class Relationship {

	@JsonProperty("studentRefId")
	private String studentRefId;
	@JsonProperty("relationshipCode")
	private String relationshipCode;
	@JsonProperty("restrictions")
	private String restrictions;
	@JsonProperty("livesWith")
	private String livesWith;
	@JsonProperty("primaryContactIndicator")
	private String primaryContactIndicator;
	@JsonProperty("emergencyContactIndicator")
	private String emergencyContactIndicator;
	@JsonProperty("financialResponsibilityIndicator")
	private String financialResponsibilityIndicator;
	@JsonProperty("custodialIndicator")
	private String custodialIndicator;
	@JsonProperty("communicationsIndicator")
	private String communicationsIndicator;
	@JsonProperty("contactSequence")
	private String contactSequence;

	public Relationship() {
	}

	public Relationship(String studentRefId, String relationshipCode, String restrictions, String livesWith, String primaryContactIndicator, String emergencyContactIndicator, String financialResponsibilityIndicator, String custodialIndicator, String communicationsIndicator, String contactSequence) {
		super();
		this.studentRefId = studentRefId;
		this.relationshipCode = relationshipCode;
		this.restrictions = restrictions;
		this.livesWith = livesWith;
		this.primaryContactIndicator = primaryContactIndicator;
		this.emergencyContactIndicator = emergencyContactIndicator;
		this.financialResponsibilityIndicator = financialResponsibilityIndicator;
		this.custodialIndicator = custodialIndicator;
		this.communicationsIndicator = communicationsIndicator;
		this.contactSequence = contactSequence;
	}

	@JsonProperty("studentRefId")
	public String getStudentRefId() {
		return studentRefId;
	}

	@JsonProperty("studentRefId")
	public void setStudentRefId(String studentRefId) {
		this.studentRefId = studentRefId;
	}

	@JsonProperty("relationshipCode")
	public String getRelationshipCode() {
		return relationshipCode;
	}

	@JsonProperty("relationshipCode")
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}

	@JsonProperty("restrictions")
	public String getRestrictions() {
		return restrictions;
	}

	@JsonProperty("restrictions")
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	@JsonProperty("livesWith")
	public String getLivesWith() {
		return livesWith;
	}

	@JsonProperty("livesWith")
	public void setLivesWith(String livesWith) {
		this.livesWith = livesWith;
	}

	@JsonProperty("primaryContactIndicator")
	public String getPrimaryContactIndicator() {
		return primaryContactIndicator;
	}

	@JsonProperty("primaryContactIndicator")
	public void setPrimaryContactIndicator(String primaryContactIndicator) {
		this.primaryContactIndicator = primaryContactIndicator;
	}

	@JsonProperty("emergencyContactIndicator")
	public String getEmergencyContactIndicator() {
		return emergencyContactIndicator;
	}

	@JsonProperty("emergencyContactIndicator")
	public void setEmergencyContactIndicator(String emergencyContactIndicator) {
		this.emergencyContactIndicator = emergencyContactIndicator;
	}

	@JsonProperty("financialResponsibilityIndicator")
	public String getFinancialResponsibilityIndicator() {
		return financialResponsibilityIndicator;
	}

	@JsonProperty("financialResponsibilityIndicator")
	public void setFinancialResponsibilityIndicator(String financialResponsibilityIndicator) {
		this.financialResponsibilityIndicator = financialResponsibilityIndicator;
	}

	@JsonProperty("custodialIndicator")
	public String getCustodialIndicator() {
		return custodialIndicator;
	}

	@JsonProperty("custodialIndicator")
	public void setCustodialIndicator(String custodialIndicator) {
		this.custodialIndicator = custodialIndicator;
	}

	@JsonProperty("communicationsIndicator")
	public String getCommunicationsIndicator() {
		return communicationsIndicator;
	}

	@JsonProperty("communicationsIndicator")
	public void setCommunicationsIndicator(String communicationsIndicator) {
		this.communicationsIndicator = communicationsIndicator;
	}

	@JsonProperty("contactSequence")
	public String getContactSequence() {
		return contactSequence;
	}

	@JsonProperty("contactSequence")
	public void setContactSequence(String contactSequence) {
		this.contactSequence = contactSequence;
	}

	@Override
	public String toString() {
		return "Relationship{" + "studentRefId='" + studentRefId + '\'' + ", relationshipCode='" + relationshipCode + '\'' + ", restrictions='" + restrictions + '\'' + ", livesWith='" + livesWith + '\'' + ", primaryContactIndicator='" + primaryContactIndicator + '\'' + ", emergencyContactIndicator='" + emergencyContactIndicator + '\'' + ", financialResponsibilityIndicator='" + financialResponsibilityIndicator + '\'' + ", custodialIndicator='" + custodialIndicator + '\'' + ", communicationsIndicator='" + communicationsIndicator + '\'' + ", contactSequence='" + contactSequence + '\'' + '}';
	}
}