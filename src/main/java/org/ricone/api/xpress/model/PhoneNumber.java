package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"phoneNumberType", "number", "primaryIndicator"})
public class PhoneNumber {

	@JsonProperty("phoneNumberType")
	private String phoneNumberType;
	@JsonProperty("number")
	private String number;
	@JsonProperty("primaryIndicator")
	private String primaryIndicator;

	public PhoneNumber() {
	}

	public PhoneNumber(String phoneNumberType, String number, String primaryIndicator) {
		super();
		this.phoneNumberType = phoneNumberType;
		this.number = number;
		this.primaryIndicator = primaryIndicator;
	}

	@JsonProperty("phoneNumberType")
	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	@JsonProperty("phoneNumberType")
	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	@JsonProperty("number")
	public String getNumber() {
		return number;
	}

	@JsonProperty("number")
	public void setNumber(String number) {
		this.number = number;
	}

	@JsonProperty("primaryIndicator")
	public String getPrimaryIndicator() {
		return primaryIndicator;
	}

	@JsonProperty("primaryIndicator")
	public void setPrimaryIndicator(String primaryIndicator) {
		this.primaryIndicator = primaryIndicator;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(phoneNumberType, number, primaryIndicator).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "PhoneNumber{" + "phoneNumberType='" + phoneNumberType + '\'' + ", number='" + number + '\'' + ", primaryIndicator='" + primaryIndicator + '\'' + '}';
	}
}