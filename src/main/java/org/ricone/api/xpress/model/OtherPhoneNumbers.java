package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"phoneNumber"})
public class OtherPhoneNumbers {
	@JsonProperty("phoneNumber")
	@ApiModelProperty(position = 1, value = "Other phone numbers for the person or organization")
	private List<PhoneNumber> phoneNumber;

	public OtherPhoneNumbers() {
		phoneNumber = new ArrayList<>();
	}

	public OtherPhoneNumbers(List<PhoneNumber> phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public List<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "OtherPhoneNumbers{" + "phoneNumber=" + phoneNumber + '}';
	}
}