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
@JsonPropertyOrder({"emailType", "emailAddress"})
public class Email {
	@JsonProperty("emailType")
	@ApiModelProperty(position = 1, value = "The type of electronic mail (e-mail) address listed for a person or organization")
	private String emailType;

	@JsonProperty("emailAddress")
	@ApiModelProperty(position = 2, value = "The numbers, letters, and symbols used to identify an electronic mail (e-mail) user within the network to which the person or organization belongs")
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

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(emailType, emailAddress).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Email{" + "emailType='" + emailType + '\'' + ", emailAddress='" + emailAddress + '\'' + '}';
	}
}