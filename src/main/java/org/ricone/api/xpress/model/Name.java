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
@JsonPropertyOrder({"type", "prefix", "familyName", "givenName", "middleName", "suffix"})
public class Name {
	@JsonProperty("type")
	@ApiModelProperty(position = 1, value = "The types of previous, alternate or other names for a person. Generally the 'LegalName' for the primary name")
	private String type;

	@JsonProperty("prefix")
	@ApiModelProperty(position = 2, value = "An appellation, if any, used to denote rank, placement, or status (e.g., Mr., Ms., Reverend, etc...)")
	private String prefix;

	@JsonProperty("familyName")
	@ApiModelProperty(position = 3, value = "The full legal last name borne in common by members of a family")
	private String familyName;

	@JsonProperty("givenName")
	@ApiModelProperty(position = 4, value = "The full legal first name given to a person at birth, baptism, or through legal change")
	private String givenName;

	@JsonProperty("middleName")
	@ApiModelProperty(position = 5, value = "A full legal middle name given to a person at birth, baptism, or through legal change")
	private String middleName;

	@JsonProperty("suffix")
	@ApiModelProperty(position = 6, value = "An appendage, if any, used to denote a person's generation in his family (e.g., Jr., Sr., III)")
	private String suffix;

	public Name() {
	}

	public Name(String type, String prefix, String familyName, String givenName, String middleName, String suffix) {
		super();
		this.type = type;
		this.prefix = prefix;
		this.familyName = familyName;
		this.givenName = givenName;
		this.middleName = middleName;
		this.suffix = suffix;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("prefix")
	public String getPrefix() {
		return prefix;
	}

	@JsonProperty("prefix")
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@JsonProperty("familyName")
	public String getFamilyName() {
		return familyName;
	}

	@JsonProperty("familyName")
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@JsonProperty("givenName")
	public String getGivenName() {
		return givenName;
	}

	@JsonProperty("givenName")
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("suffix")
	public String getSuffix() {
		return suffix;
	}

	@JsonProperty("suffix")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(type, prefix, familyName, givenName, middleName, suffix).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Name{" + "type='" + type + '\'' + ", prefix='" + prefix + '\'' + ", familyName='" + familyName + '\'' + ", givenName='" + givenName + '\'' + ", middleName='" + middleName + '\'' + ", suffix='" + suffix + '\'' + '}';
	}
}