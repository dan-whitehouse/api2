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
@JsonPropertyOrder({"@refId", "name", "localId", "stateProvinceId", "otherIds", "sex", "email", "primaryAssignment", "otherAssignments", "metadata"})
@JsonRootName(value = "xStaff")
public class XStaff {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("name")
	private Name name;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("stateProvinceId")
	private String stateProvinceId;
	@JsonProperty("otherIds")
	private OtherIds otherIds;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("email")
	private Email email;
	@JsonProperty("primaryAssignment")
	private PrimaryAssignment primaryAssignment;
	@JsonProperty("otherAssignments")
	private OtherAssignments otherAssignments;
	@JsonProperty("appProvisioningInfo")
	private AppProvisioningInfo appProvisioningInfo;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XStaff() {
	}

	public XStaff(String refId, Name name, String localId, String stateProvinceId, OtherIds otherIds, String sex, Email email, PrimaryAssignment primaryAssignment, OtherAssignments otherAssignments, Metadata metadata) {
		this.refId = refId;
		this.name = name;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.otherIds = otherIds;
		this.sex = sex;
		this.email = email;
		this.primaryAssignment = primaryAssignment;
		this.otherAssignments = otherAssignments;
		this.metadata = metadata;
	}

	public XStaff(String refId, AppProvisioningInfo appProvisioningInfo) {
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

	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	@JsonProperty("email")
	public Email getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(Email email) {
		this.email = email;
	}

	@JsonProperty("primaryAssignment")
	public PrimaryAssignment getPrimaryAssignment() {
		return primaryAssignment;
	}

	@JsonProperty("primaryAssignment")
	public void setPrimaryAssignment(PrimaryAssignment primaryAssignment) {
		this.primaryAssignment = primaryAssignment;
	}

	@JsonProperty("otherAssignments")
	public OtherAssignments getOtherAssignments() {
		return otherAssignments;
	}

	@JsonProperty("otherAssignments")
	public void setOtherAssignments(OtherAssignments otherAssignments) {
		this.otherAssignments = otherAssignments;
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
		return Stream.of(refId, name, localId, stateProvinceId, otherIds, appProvisioningInfo, sex, email, primaryAssignment, otherAssignments, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XStaff{" + "refId='" + refId + '\'' + ", name=" + name + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", otherIds=" + otherIds + ", sex='" + sex + '\'' + ", email=" + email + ", primaryAssignment=" + primaryAssignment + ", otherAssignments=" + otherAssignments + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XStaff)) return false;

		XStaff xStaff = (XStaff) o;

		if(refId != null ? !refId.equals(xStaff.refId) : xStaff.refId != null) return false;
		if(name != null ? !name.equals(xStaff.name) : xStaff.name != null) return false;
		if(localId != null ? !localId.equals(xStaff.localId) : xStaff.localId != null) return false;
		if(stateProvinceId != null ? !stateProvinceId.equals(xStaff.stateProvinceId) : xStaff.stateProvinceId != null)
			return false;
		if(otherIds != null ? !otherIds.equals(xStaff.otherIds) : xStaff.otherIds != null) return false;
		if(sex != null ? !sex.equals(xStaff.sex) : xStaff.sex != null) return false;
		if(email != null ? !email.equals(xStaff.email) : xStaff.email != null) return false;
		if(primaryAssignment != null ? !primaryAssignment.equals(xStaff.primaryAssignment) : xStaff.primaryAssignment != null)
			return false;
		if(otherAssignments != null ? !otherAssignments.equals(xStaff.otherAssignments) : xStaff.otherAssignments != null)
			return false;
		if(appProvisioningInfo != null ? !appProvisioningInfo.equals(xStaff.appProvisioningInfo) : xStaff.appProvisioningInfo != null)
			return false;
		return metadata != null ? metadata.equals(xStaff.metadata) : xStaff.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (localId != null ? localId.hashCode() : 0);
		result = 31 * result + (stateProvinceId != null ? stateProvinceId.hashCode() : 0);
		result = 31 * result + (otherIds != null ? otherIds.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (primaryAssignment != null ? primaryAssignment.hashCode() : 0);
		result = 31 * result + (otherAssignments != null ? otherAssignments.hashCode() : 0);
		result = 31 * result + (appProvisioningInfo != null ? appProvisioningInfo.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}