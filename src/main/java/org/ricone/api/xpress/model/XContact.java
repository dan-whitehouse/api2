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
@JsonPropertyOrder({"@refId", "name", "otherNames", "localId", "otherIds", "address", "phoneNumber", "otherPhoneNumbers", "email", "otherEmails", "sex", "employerType", "relationships", "metadata"})
@JsonRootName(value = "xContact")
public class XContact extends XWrapper {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("name")
	private Name name;
	@JsonProperty("otherNames")
	private OtherNames otherNames;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("otherIds")
	private OtherIds otherIds;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("otherPhoneNumbers")
	private OtherPhoneNumbers otherPhoneNumbers;
	@JsonProperty("email")
	private Email email;
	@JsonProperty("otherEmails")
	private OtherEmails otherEmails;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("employerType")
	private String employerType;
	@JsonProperty("relationships")
	private Relationships relationships;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XContact() {
	}

	public XContact(String refId, Name name, OtherNames otherNames, String localId, OtherIds otherIds, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Email email, OtherEmails otherEmails, String sex, String employerType, Relationships relationships, Metadata metadata) {
		this.refId = refId;
		this.name = name;
		this.otherNames = otherNames;
		this.localId = localId;
		this.otherIds = otherIds;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.otherPhoneNumbers = otherPhoneNumbers;
		this.email = email;
		this.otherEmails = otherEmails;
		this.sex = sex;
		this.employerType = employerType;
		this.relationships = relationships;
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

	@JsonProperty("name")
	public Name getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(Name name) {
		this.name = name;
	}

	@JsonProperty("otherNames")
	public OtherNames getOtherNames() {
		return otherNames;
	}

	@JsonProperty("otherNames")
	public void setOtherNames(OtherNames otherNames) {
		this.otherNames = otherNames;
	}

	@JsonProperty("localId")
	public String getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@JsonProperty("otherIds")
	public OtherIds getOtherIds() {
		return otherIds;
	}

	@JsonProperty("otherIds")
	public void setOtherIds(OtherIds otherIds) {
		this.otherIds = otherIds;
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

	@JsonProperty("email")
	public Email getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(Email email) {
		this.email = email;
	}

	@JsonProperty("otherEmails")
	public OtherEmails getOtherEmails() {
		return otherEmails;
	}

	@JsonProperty("otherEmails")
	public void setOtherEmails(OtherEmails otherEmails) {
		this.otherEmails = otherEmails;
	}

	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	@JsonProperty("employerType")
	public String getEmployerType() {
		return employerType;
	}

	@JsonProperty("employerType")
	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}

	@JsonProperty("relationships")
	public Relationships getRelationships() {
		return relationships;
	}

	@JsonProperty("relationships")
	public void setRelationships(Relationships relationships) {
		this.relationships = relationships;
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
		return Stream.of(refId, name, otherNames, localId, otherIds, address, phoneNumber, otherPhoneNumbers, email, otherEmails, sex, employerType, relationships, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XContact{" + "refId='" + refId + '\'' + ", name=" + name + ", otherNames=" + otherNames + ", localId='" + localId + '\'' + ", otherIds=" + otherIds + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", email=" + email + ", otherEmails=" + otherEmails + ", sex='" + sex + '\'' + ", employerType='" + employerType + '\'' + ", relationships=" + relationships + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XContact)) return false;

		XContact xContact = (XContact) o;

		if(refId != null ? !refId.equals(xContact.refId) : xContact.refId != null) return false;
		if(name != null ? !name.equals(xContact.name) : xContact.name != null) return false;
		if(otherNames != null ? !otherNames.equals(xContact.otherNames) : xContact.otherNames != null) return false;
		if(localId != null ? !localId.equals(xContact.localId) : xContact.localId != null) return false;
		if(otherIds != null ? !otherIds.equals(xContact.otherIds) : xContact.otherIds != null) return false;
		if(address != null ? !address.equals(xContact.address) : xContact.address != null) return false;
		if(phoneNumber != null ? !phoneNumber.equals(xContact.phoneNumber) : xContact.phoneNumber != null) return false;
		if(otherPhoneNumbers != null ? !otherPhoneNumbers.equals(xContact.otherPhoneNumbers) : xContact.otherPhoneNumbers != null)
			return false;
		if(email != null ? !email.equals(xContact.email) : xContact.email != null) return false;
		if(otherEmails != null ? !otherEmails.equals(xContact.otherEmails) : xContact.otherEmails != null) return false;
		if(sex != null ? !sex.equals(xContact.sex) : xContact.sex != null) return false;
		if(employerType != null ? !employerType.equals(xContact.employerType) : xContact.employerType != null)
			return false;
		if(relationships != null ? !relationships.equals(xContact.relationships) : xContact.relationships != null)
			return false;
		return metadata != null ? metadata.equals(xContact.metadata) : xContact.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (otherNames != null ? otherNames.hashCode() : 0);
		result = 31 * result + (localId != null ? localId.hashCode() : 0);
		result = 31 * result + (otherIds != null ? otherIds.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (otherPhoneNumbers != null ? otherPhoneNumbers.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (otherEmails != null ? otherEmails.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (employerType != null ? employerType.hashCode() : 0);
		result = 31 * result + (relationships != null ? relationships.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}