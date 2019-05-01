package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"addressType", "line1", "line2", "city", "stateProvince", "countryCode", "postalCode"})
public class Address {
	@JsonProperty("addressType")
	@ApiModelProperty(value = "The type of address listed", position = 1)
	private String addressType;

	@JsonProperty("line1")
	@ApiModelProperty(value = "The first line of the address", position = 2)
	private String line1;

	@JsonProperty("line2")
	@ApiModelProperty(value = "The second line of the address", position = 3)
	private String line2;

	@JsonProperty("city")
	@ApiModelProperty(value = "The name of the city in which an address is located", position = 4)
	private String city;

	@JsonProperty("stateProvince")
	@ApiModelProperty(value = "The abbreviation for the state, province or outlying area in which an address is located", position = 5)
	private String stateProvince;

	@JsonProperty("countryCode")
	@ApiModelProperty(value = "The unique two character International Organization for Standardization (ISO) code for the country in which an address is located", position = 6)
	private String countryCode;

	@JsonProperty("postalCode")
	@ApiModelProperty(value = "A number that identifies each postal delivery area in the locale used as a portion of an address", position = 7)
	private String postalCode;

	public Address() {
	}

	public Address(String addressType, String line1, String line2, String city, String stateProvince, String countryCode, String postalCode) {
		super();
		this.addressType = addressType;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.stateProvince = stateProvince;
		this.countryCode = countryCode;
		this.postalCode = postalCode;
	}

	@JsonProperty("addressType")
	public String getAddressType() {
		return addressType;
	}

	@JsonProperty("addressType")
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@JsonProperty("line1")
	public String getLine1() {
		return line1;
	}

	@JsonProperty("line1")
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	@JsonProperty("line2")
	public String getLine2() {
		return line2;
	}

	@JsonProperty("line2")
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("stateProvince")
	public String getStateProvince() {
		return stateProvince;
	}

	@JsonProperty("stateProvince")
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(addressType, line1, line2, city, stateProvince, countryCode, postalCode).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Address{" + "addressType='" + addressType + '\'' + ", line1='" + line1 + '\'' + ", line2='" + line2 + '\'' + ", city='" + city + '\'' + ", stateProvince='" + stateProvince + '\'' + ", countryCode='" + countryCode + '\'' + ", postalCode='" + postalCode + '\'' + '}';
	}
}