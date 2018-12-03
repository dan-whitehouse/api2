/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "leaRefId", "localId", "stateProvinceId", "otherIds", "schoolName", "gradeLevels", "address", "phoneNumber", "otherPhoneNumbers", "metadata"})
@JsonRootName(value = "xSchool")
public class XSchool {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("leaRefId")
	private String leaRefId;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("stateProvinceId")
	private String stateProvinceId;
	@JsonProperty("otherIds")
	private OtherIds otherIds;
	@JsonProperty("schoolName")
	private String schoolName;
	@JsonProperty("gradeLevels")
	private GradeLevels gradeLevels;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("otherPhoneNumbers")
	private OtherPhoneNumbers otherPhoneNumbers;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XSchool() {
	}

	public XSchool(String refId, String leaRefId, String localId, String stateProvinceId, OtherIds otherIds, String schoolName, GradeLevels gradeLevels, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Metadata metadata) {
		this.refId = refId;
		this.leaRefId = leaRefId;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.otherIds = otherIds;
		this.schoolName = schoolName;
		this.gradeLevels = gradeLevels;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.otherPhoneNumbers = otherPhoneNumbers;
		this.metadata = metadata;
	}

	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	public String getRefId() {
		return refId;
	}

	@JsonProperty("@refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("leaRefId")
	public String getLeaRefId() {
		return leaRefId;
	}

	@JsonProperty("leaRefId")
	public void setLeaRefId(String leaRefId) {
		this.leaRefId = leaRefId;
	}

	@JsonProperty("localId")
	public String getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@JsonProperty("stateProvinceId")
	public String getStateProvinceId() {
		return stateProvinceId;
	}

	@JsonProperty("stateProvinceId")
	public void setStateProvinceId(String stateProvinceId) {
		this.stateProvinceId = stateProvinceId;
	}

	@JsonProperty("otherIds")
	public OtherIds getOtherIds() {
		return otherIds;
	}

	@JsonProperty("otherIds")
	public void setOtherIds(OtherIds otherIds) {
		this.otherIds = otherIds;
	}

	@JsonProperty("schoolName")
	public String getSchoolName() {
		return schoolName;
	}

	@JsonProperty("schoolName")
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@JsonProperty("gradeLevels")
	public GradeLevels getGradeLevels() {
		return gradeLevels;
	}

	@JsonProperty("gradeLevels")
	public void setGradeLevels(GradeLevels gradeLevels) {
		this.gradeLevels = gradeLevels;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("phoneNumber")
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("otherPhoneNumbers")
	public OtherPhoneNumbers getOtherPhoneNumbers() {
		return otherPhoneNumbers;
	}

	@JsonProperty("otherPhoneNumbers")
	public void setOtherPhoneNumbers(OtherPhoneNumbers otherPhoneNumbers) {
		this.otherPhoneNumbers = otherPhoneNumbers;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(refId, leaRefId, localId, stateProvinceId, otherIds, schoolName, gradeLevels, address, phoneNumber, otherPhoneNumbers, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XSchool{" + "refId='" + refId + '\'' + ", leaRefId='" + leaRefId + '\'' + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", otherIds=" + otherIds + ", schoolName='" + schoolName + '\'' + ", gradeLevels=" + gradeLevels + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XSchool)) return false;

		XSchool xSchool = (XSchool) o;

		if(refId != null ? !refId.equals(xSchool.refId) : xSchool.refId != null) return false;
		if(leaRefId != null ? !leaRefId.equals(xSchool.leaRefId) : xSchool.leaRefId != null) return false;
		if(localId != null ? !localId.equals(xSchool.localId) : xSchool.localId != null) return false;
		if(stateProvinceId != null ? !stateProvinceId.equals(xSchool.stateProvinceId) : xSchool.stateProvinceId != null)
			return false;
		if(otherIds != null ? !otherIds.equals(xSchool.otherIds) : xSchool.otherIds != null) return false;
		if(schoolName != null ? !schoolName.equals(xSchool.schoolName) : xSchool.schoolName != null) return false;
		if(gradeLevels != null ? !gradeLevels.equals(xSchool.gradeLevels) : xSchool.gradeLevels != null) return false;
		if(address != null ? !address.equals(xSchool.address) : xSchool.address != null) return false;
		if(phoneNumber != null ? !phoneNumber.equals(xSchool.phoneNumber) : xSchool.phoneNumber != null) return false;
		if(otherPhoneNumbers != null ? !otherPhoneNumbers.equals(xSchool.otherPhoneNumbers) : xSchool.otherPhoneNumbers != null)
			return false;
		return metadata != null ? metadata.equals(xSchool.metadata) : xSchool.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (leaRefId != null ? leaRefId.hashCode() : 0);
		result = 31 * result + (localId != null ? localId.hashCode() : 0);
		result = 31 * result + (stateProvinceId != null ? stateProvinceId.hashCode() : 0);
		result = 31 * result + (otherIds != null ? otherIds.hashCode() : 0);
		result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
		result = 31 * result + (gradeLevels != null ? gradeLevels.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (otherPhoneNumbers != null ? otherPhoneNumbers.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}