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
@JsonPropertyOrder({"contactPersonRefId"})
public class StudentContacts {
	@JsonProperty("contactPersonRefId")
	@ApiModelProperty(position = 1, value = "The RefId of the contact object")
	private List<String> contactPersonRefId;

	public StudentContacts() {
		contactPersonRefId = new ArrayList<>();
	}

	public StudentContacts(List<String> contactPersonRefId) {
		super();
		this.contactPersonRefId = contactPersonRefId;
	}

	@JsonProperty("contactPersonRefId")
	public List<String> getContactPersonRefId() {
		return contactPersonRefId;
	}

	@JsonProperty("contactPersonRefId")
	public void setContactPersonRefId(List<String> contactPersonRefId) {
		this.contactPersonRefId = contactPersonRefId;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return contactPersonRefId.isEmpty();
	}

	@Override
	public String toString() {
		return "StudentContacts{" + "contactPersonRefId=" + contactPersonRefId + '}';
	}
}