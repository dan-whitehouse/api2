/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "givenName", "familyName", "localId"})
public class StudentReference {

	@JsonProperty("refId")
	private String refId;
	@JsonProperty("givenName")
	private String givenName;
	@JsonProperty("familyName")
	private String familyName;
	@JsonProperty("localId")
	private String localId;

	public StudentReference() {
	}

	public StudentReference(String refId, String givenName, String familyName, String localId) {
		super();
		this.refId = refId;
		this.givenName = givenName;
		this.familyName = familyName;
		this.localId = localId;
	}

	@JsonProperty("refId")
	public String getRefId() {
		return refId;
	}

	@JsonProperty("refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("givenName")
	public String getGivenName() {
		return givenName;
	}

	@JsonProperty("givenName")
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@JsonProperty("familyName")
	public String getFamilyName() {
		return familyName;
	}

	@JsonProperty("familyName")
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@JsonProperty("localId")
	public String getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(refId, localId, givenName, familyName).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "StudentReference{" + "refId='" + refId + '\'' + ", givenName='" + givenName + '\'' + ", familyName='" + familyName + '\'' + ", localId='" + localId + '\'' + '}';
	}
}