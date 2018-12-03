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
@JsonPropertyOrder({"emailType", "emailAddress"})
public class Email {

	@JsonProperty("emailType")
	private String emailType;
	@JsonProperty("emailAddress")
	private String emailAddress;

	public Email() {
	}

	public Email(String emailType, String emailAddress) {
		super();
		this.emailType = emailType;
		this.emailAddress = emailAddress;
	}

	@JsonProperty("emailType")
	public String getEmailType() {
		return emailType;
	}

	@JsonProperty("emailType")
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Email{" + "emailType='" + emailType + '\'' + ", emailAddress='" + emailAddress + '\'' + '}';
	}
}