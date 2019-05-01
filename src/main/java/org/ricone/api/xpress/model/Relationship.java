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
@JsonPropertyOrder({"studentRefId", "relationshipCode", "restrictions", "livesWith", "primaryContactIndicator", "emergencyContactIndicator", "financialResponsibilityIndicator", "custodialIndicator", "communicationsIndicator", "contactSequence"})
public class Relationship {
	@JsonProperty("studentRefId")
	@ApiModelProperty(position = 1, value = "The refId of the student")
	private String studentRefId;

	@JsonProperty("relationshipCode")
	@ApiModelProperty(position = 2, value = "The nature of the person's relationship to a learner. The learner may be an Early Learning Child, K12 Student, Postsecondary Student, or an adult learner in a workforce education program")
	private String relationshipCode;

	@JsonProperty("restrictions")
	@ApiModelProperty(position = 3, value = "Restrictions for student and/or teacher contact with the individual (e.g., the student may not be picked up by the individual")
	private String restrictions;

	@JsonProperty("livesWith")
	@ApiModelProperty(position = 4, value = "Indicates whether or not the learner lives with the related person")
	private String livesWith;

	@JsonProperty("primaryContactIndicator")
	@ApiModelProperty(position = 5, value = "Indicates that a person is a primary contact within the specified context, such as a primary parental contact specified in Person Relationship to Learner or a primary administrative contact for an organization")
	private String primaryContactIndicator;

	@JsonProperty("emergencyContactIndicator")
	@ApiModelProperty(position = 6, value = "Indicates whether or not the person is a designated emergency contact for the learner")
	private String emergencyContactIndicator;

	@JsonProperty("financialResponsibilityIndicator")
	@ApiModelProperty(position = 7, value = "")
	private String financialResponsibilityIndicator;

	@JsonProperty("custodialIndicator")
	@ApiModelProperty(position = 8, value = "")
	private String custodialIndicator;

	@JsonProperty("communicationsIndicator")
	@ApiModelProperty(position = 9, value = "")
	private String communicationsIndicator;

	@JsonProperty("contactSequence")
	@ApiModelProperty(position = 10, value = "The numeric order in the preferred sequence and priority for contacting a person related to the learner")
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

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(studentRefId, relationshipCode, restrictions, livesWith, primaryContactIndicator, emergencyContactIndicator, financialResponsibilityIndicator, custodialIndicator, communicationsIndicator, contactSequence).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Relationship{" + "studentRefId='" + studentRefId + '\'' + ", relationshipCode='" + relationshipCode + '\'' + ", restrictions='" + restrictions + '\'' + ", livesWith='" + livesWith + '\'' + ", primaryContactIndicator='" + primaryContactIndicator + '\'' + ", emergencyContactIndicator='" + emergencyContactIndicator + '\'' + ", financialResponsibilityIndicator='" + financialResponsibilityIndicator + '\'' + ", custodialIndicator='" + custodialIndicator + '\'' + ", communicationsIndicator='" + communicationsIndicator + '\'' + ", contactSequence='" + contactSequence + '\'' + '}';
	}
}