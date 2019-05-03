/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "name", "otherNames", "localId", "stateProvinceId", "otherIds", "address", "phoneNumber", "otherPhoneNumbers", "email", "otherEmails", "demographics", "enrollment", "otherEnrollments", "academicSummary", "studentContacts", "languages", "metadata"})
@JsonRootName(value = "xStudent")
public class XStudent extends XWrapper {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	@ApiModelProperty(position = 1, value = "The refId of the student")
	private String refId;

	@JsonProperty("name")
	@ApiModelProperty(position = 2, value = "The name of student")
	private Name name;

	@JsonProperty("otherNames")
	@ApiModelProperty(position = 3, value = "Other names the student may be known by")
	private OtherNames otherNames;

	@JsonProperty("localId")
	@ApiModelProperty(position = 4, value = "A unique number or alphanumeric code assigned to a student by a district or LEA")
	private String localId;

	@JsonProperty("stateProvinceId")
	@ApiModelProperty(position = 5, value = "A unique number or alphanumeric code assigned to a student by a state")
	private String stateProvinceId;

	@JsonProperty("otherIds")
	@ApiModelProperty(position = 6, value = "Other Ids for the student")
	private OtherIds otherIds;

	@JsonProperty("address")
	@ApiModelProperty(position = 7, value = "Address of the student")
	private Address address;

	@JsonProperty("phoneNumber")
	@ApiModelProperty(position = 8, value = "Phone number of the student")
	private PhoneNumber phoneNumber;

	@JsonProperty("otherPhoneNumbers")
	@ApiModelProperty(position = 9, value = "Other phone numbers for the student")
	private OtherPhoneNumbers otherPhoneNumbers;

	@JsonProperty("email")
	@ApiModelProperty(position = 10, value = "Email of the student")
	private Email email;

	@JsonProperty("otherEmails")
	@ApiModelProperty(position = 11, value = "Other emails for the student")
	private OtherEmails otherEmails;

	@JsonProperty("demographics")
	@ApiModelProperty(position = 12, value = "Demographic information about the student")
	private Demographics demographics;

	@JsonProperty("enrollment")
	@ApiModelProperty(position = 13, value = "Current enrollment information for the student")
	private Enrollment enrollment;

	@JsonProperty("otherEnrollments")
	@ApiModelProperty(position = 14, value = "Other enrollment information for the student")
	private OtherEnrollments otherEnrollments;

	@JsonProperty("academicSummary")
	@ApiModelProperty(position = 15, value = "Academic summary of the student")
	private AcademicSummary academicSummary;

	@JsonProperty("studentContacts")
	@ApiModelProperty(position = 16, value = "Persons who are contacts of the student")
	private StudentContacts studentContacts;

	@JsonProperty("languages")
	@ApiModelProperty(position = 17, value = "Languages of the student")
	private Languages languages;

	@JsonProperty("appProvisioningInfo")
	@ApiModelProperty(position = 18, value = "")
	private AppProvisioningInfo appProvisioningInfo;

	@JsonProperty("metadata")
	@ApiModelProperty(position = 19, value = "")
	private Metadata metadata;

	public XStudent() {
	}

	public XStudent(String refId, Name name, OtherNames otherNames, String localId, String stateProvinceId, OtherIds otherIds, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Email email, OtherEmails otherEmails, Demographics demographics, Enrollment enrollment, OtherEnrollments otherEnrollments, AcademicSummary academicSummary, StudentContacts studentContacts, Languages languages, Metadata metadata) {
		this.refId = refId;
		this.name = name;
		this.otherNames = otherNames;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.otherIds = otherIds;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.otherPhoneNumbers = otherPhoneNumbers;
		this.email = email;
		this.otherEmails = otherEmails;
		this.demographics = demographics;
		this.enrollment = enrollment;
		this.otherEnrollments = otherEnrollments;
		this.academicSummary = academicSummary;
		this.studentContacts = studentContacts;
		this.languages = languages;
		this.metadata = metadata;
	}

	public XStudent(String refId, AppProvisioningInfo appProvisioningInfo) {
		super();
		this.refId = refId;
		this.appProvisioningInfo = appProvisioningInfo;
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

	@JsonProperty("demographics")
	public Demographics getDemographics() {
		return demographics;
	}

	@JsonProperty("demographics")
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	@JsonProperty("enrollment")
	public Enrollment getEnrollment() {
		return enrollment;
	}

	@JsonProperty("enrollment")
	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	@JsonProperty("otherEnrollments")
	public OtherEnrollments getOtherEnrollments() {
		return otherEnrollments;
	}

	@JsonProperty("otherEnrollments")
	public void setOtherEnrollments(OtherEnrollments otherEnrollments) {
		this.otherEnrollments = otherEnrollments;
	}

	@JsonProperty("academicSummary")
	public AcademicSummary getAcademicSummary() {
		return academicSummary;
	}

	@JsonProperty("academicSummary")
	public void setAcademicSummary(AcademicSummary academicSummary) {
		this.academicSummary = academicSummary;
	}

	@JsonProperty("studentContacts")
	public StudentContacts getStudentContacts() {
		return studentContacts;
	}

	@JsonProperty("studentContacts")
	public void setStudentContacts(StudentContacts studentContacts) {
		this.studentContacts = studentContacts;
	}

	@JsonProperty("languages")
	public Languages getLanguages() {
		return languages;
	}

	@JsonProperty("languages")
	public void setLanguages(Languages languages) {
		this.languages = languages;
	}

	@JsonProperty("appProvisioningInfo")
	public AppProvisioningInfo getAppProvisioningInfo() {
		return appProvisioningInfo;
	}

	@JsonProperty("appProvisioningInfo")
	public void setAppProvisioningInfo(AppProvisioningInfo appProvisioningInfo) {
		this.appProvisioningInfo = appProvisioningInfo;
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
		return Stream.of(refId, name, otherNames, localId, stateProvinceId, otherIds, address, phoneNumber, otherPhoneNumbers, email, otherEmails, demographics, enrollment, otherEnrollments, academicSummary, studentContacts, languages, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XStudent{" + "refId='" + refId + '\'' + ", name=" + name + ", otherNames=" + otherNames + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", otherIds=" + otherIds + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", email=" + email + ", otherEmails=" + otherEmails + ", demographics=" + demographics + ", enrollment=" + enrollment + ", otherEnrollments=" + otherEnrollments + ", academicSummary=" + academicSummary + ", studentContacts=" + studentContacts + ", languages=" + languages + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XStudent)) return false;

		XStudent xStudent = (XStudent) o;

		if(refId != null ? !refId.equals(xStudent.refId) : xStudent.refId != null) return false;
		if(name != null ? !name.equals(xStudent.name) : xStudent.name != null) return false;
		if(otherNames != null ? !otherNames.equals(xStudent.otherNames) : xStudent.otherNames != null) return false;
		if(localId != null ? !localId.equals(xStudent.localId) : xStudent.localId != null) return false;
		if(stateProvinceId != null ? !stateProvinceId.equals(xStudent.stateProvinceId) : xStudent.stateProvinceId != null)
			return false;
		if(otherIds != null ? !otherIds.equals(xStudent.otherIds) : xStudent.otherIds != null) return false;
		if(address != null ? !address.equals(xStudent.address) : xStudent.address != null) return false;
		if(phoneNumber != null ? !phoneNumber.equals(xStudent.phoneNumber) : xStudent.phoneNumber != null) return false;
		if(otherPhoneNumbers != null ? !otherPhoneNumbers.equals(xStudent.otherPhoneNumbers) : xStudent.otherPhoneNumbers != null)
			return false;
		if(email != null ? !email.equals(xStudent.email) : xStudent.email != null) return false;
		if(otherEmails != null ? !otherEmails.equals(xStudent.otherEmails) : xStudent.otherEmails != null) return false;
		if(demographics != null ? !demographics.equals(xStudent.demographics) : xStudent.demographics != null)
			return false;
		if(enrollment != null ? !enrollment.equals(xStudent.enrollment) : xStudent.enrollment != null) return false;
		if(otherEnrollments != null ? !otherEnrollments.equals(xStudent.otherEnrollments) : xStudent.otherEnrollments != null)
			return false;
		if(academicSummary != null ? !academicSummary.equals(xStudent.academicSummary) : xStudent.academicSummary != null)
			return false;
		if(studentContacts != null ? !studentContacts.equals(xStudent.studentContacts) : xStudent.studentContacts != null)
			return false;
		if(languages != null ? !languages.equals(xStudent.languages) : xStudent.languages != null) return false;
		if(appProvisioningInfo != null ? !appProvisioningInfo.equals(xStudent.appProvisioningInfo) : xStudent.appProvisioningInfo != null)
			return false;
		return metadata != null ? metadata.equals(xStudent.metadata) : xStudent.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (otherNames != null ? otherNames.hashCode() : 0);
		result = 31 * result + (localId != null ? localId.hashCode() : 0);
		result = 31 * result + (stateProvinceId != null ? stateProvinceId.hashCode() : 0);
		result = 31 * result + (otherIds != null ? otherIds.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (otherPhoneNumbers != null ? otherPhoneNumbers.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (otherEmails != null ? otherEmails.hashCode() : 0);
		result = 31 * result + (demographics != null ? demographics.hashCode() : 0);
		result = 31 * result + (enrollment != null ? enrollment.hashCode() : 0);
		result = 31 * result + (otherEnrollments != null ? otherEnrollments.hashCode() : 0);
		result = 31 * result + (academicSummary != null ? academicSummary.hashCode() : 0);
		result = 31 * result + (studentContacts != null ? studentContacts.hashCode() : 0);
		result = 31 * result + (languages != null ? languages.hashCode() : 0);
		result = 31 * result + (appProvisioningInfo != null ? appProvisioningInfo.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}