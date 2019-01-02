package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "nametype", "nameprefix", "namefamilyName", "namegivenName", "namemiddleName", "namesuffix", "otherNamesnametype", "otherNamesnameprefix", "otherNamesnamefamilyName", "otherNamesnamegivenName", "otherNamesnamemiddleName", "otherNamesnamesuffix", "localId", "loginId", "otherIdsotherIdtype", "otherIdsotherIdid", "addressaddressType", "addressline1", "addressline2", "addresscity", "addressstateProvince", "addresspostalCode", "addresscountryCode", "phoneNumberphoneNumberType", "phoneNumbernumber", "phoneNumberprimaryIndicator", "otherPhoneNumbersphoneNumberphoneNumberType", "otherPhoneNumbersphoneNumbernumber", "otherPhoneNumbersphoneNumberprimaryIndicator", "emailemailType", "emailemailAddress", "otherEmailsemailemailType", "otherEmailsemailemailAddress", "sex", "employerType", "relationshipsrelationshipstudentRefId", "relationshipsrelationshiprelationshipCode", "relationshipsrelationshiprestrictions", "relationshipsrelationshiplivesWith", "relationshipsrelationshipprimaryContactIndicator", "relationshipsrelationshipemergencyContactIndicator", "relationshipsrelationshipfinancialResponsibilityIndicator", "relationshipsrelationshipcustodialIndicator", "relationshipsrelationshipcommunicationsIndicator", "relationshipsrelationshipcontactSequence", "id"})
public class XContactFilter implements IFilter<XContactFilter>, Serializable {

    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("nametype")
    private Boolean nametype;
    @JsonProperty("nameprefix")
    private Boolean nameprefix;
    @JsonProperty("namefamilyName")
    private Boolean namefamilyName;
    @JsonProperty("namegivenName")
    private Boolean namegivenName;
    @JsonProperty("namemiddleName")
    private Boolean namemiddleName;
    @JsonProperty("namesuffix")
    private Boolean namesuffix;
    @JsonProperty("otherNamesnametype")
    private Boolean otherNamesnametype;
    @JsonProperty("otherNamesnameprefix")
    private Boolean otherNamesnameprefix;
    @JsonProperty("otherNamesnamefamilyName")
    private Boolean otherNamesnamefamilyName;
    @JsonProperty("otherNamesnamegivenName")
    private Boolean otherNamesnamegivenName;
    @JsonProperty("otherNamesnamemiddleName")
    private Boolean otherNamesnamemiddleName;
    @JsonProperty("otherNamesnamesuffix")
    private Boolean otherNamesnamesuffix;
    @JsonProperty("localId")
    private Boolean localId;
    @JsonProperty("loginId")
    private Boolean loginId;
    @JsonProperty("otherIdsotherIdtype")
    private Boolean otherIdsotherIdtype;
    @JsonProperty("otherIdsotherIdid")
    private Boolean otherIdsotherIdid;
    @JsonProperty("addressaddressType")
    private Boolean addressaddressType;
    @JsonProperty("addressline1")
    private Boolean addressline1;
    @JsonProperty("addressline2")
    private Boolean addressline2;
    @JsonProperty("addresscity")
    private Boolean addresscity;
    @JsonProperty("addressstateProvince")
    private Boolean addressstateProvince;
    @JsonProperty("addresspostalCode")
    private Boolean addresspostalCode;
    @JsonProperty("addresscountryCode")
    private Boolean addresscountryCode;
    @JsonProperty("phoneNumberphoneNumberType")
    private Boolean phoneNumberphoneNumberType;
    @JsonProperty("phoneNumbernumber")
    private Boolean phoneNumbernumber;
    @JsonProperty("phoneNumberprimaryIndicator")
    private Boolean phoneNumberprimaryIndicator;
    @JsonProperty("otherPhoneNumbersphoneNumberphoneNumberType")
    private Boolean otherPhoneNumbersphoneNumberphoneNumberType;
    @JsonProperty("otherPhoneNumbersphoneNumbernumber")
    private Boolean otherPhoneNumbersphoneNumbernumber;
    @JsonProperty("otherPhoneNumbersphoneNumberprimaryIndicator")
    private Boolean otherPhoneNumbersphoneNumberprimaryIndicator;
    @JsonProperty("emailemailType")
    private Boolean emailemailType;
    @JsonProperty("emailemailAddress")
    private Boolean emailemailAddress;
    @JsonProperty("otherEmailsemailemailType")
    private Boolean otherEmailsemailemailType;
    @JsonProperty("otherEmailsemailemailAddress")
    private Boolean otherEmailsemailemailAddress;
    @JsonProperty("sex")
    private Boolean sex;
    @JsonProperty("employerType")
    private Boolean employerType;
    @JsonProperty("relationshipsrelationshipstudentRefId")
    private Boolean relationshipsrelationshipstudentRefId;
    @JsonProperty("relationshipsrelationshiprelationshipCode")
    private Boolean relationshipsrelationshiprelationshipCode;
    @JsonProperty("relationshipsrelationshiprestrictions")
    private Boolean relationshipsrelationshiprestrictions;
    @JsonProperty("relationshipsrelationshiplivesWith")
    private Boolean relationshipsrelationshiplivesWith;
    @JsonProperty("relationshipsrelationshipprimaryContactIndicator")
    private Boolean relationshipsrelationshipprimaryContactIndicator;
    @JsonProperty("relationshipsrelationshipemergencyContactIndicator")
    private Boolean relationshipsrelationshipemergencyContactIndicator;
    @JsonProperty("relationshipsrelationshipfinancialResponsibilityIndicator")
    private Boolean relationshipsrelationshipfinancialResponsibilityIndicator;
    @JsonProperty("relationshipsrelationshipcustodialIndicator")
    private Boolean relationshipsrelationshipcustodialIndicator;
    @JsonProperty("relationshipsrelationshipcommunicationsIndicator")
    private Boolean relationshipsrelationshipcommunicationsIndicator;
    @JsonProperty("relationshipsrelationshipcontactSequence")
    private Boolean relationshipsrelationshipcontactSequence;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = 6275802241316282677L;

    /**
     * No args constructor for use in serialization
     */
    public XContactFilter() {
        refId = true;
        nametype = true;
        nameprefix = true;
        namefamilyName = true;
        namegivenName = true;
        namemiddleName = true;
        namesuffix = true;
        otherNamesnametype = true;
        otherNamesnameprefix = true;
        otherNamesnamefamilyName = true;
        otherNamesnamegivenName = true;
        otherNamesnamemiddleName = true;
        otherNamesnamesuffix = true;
        localId = true;
        loginId = true;
        otherIdsotherIdtype = true;
        otherIdsotherIdid = true;
        addressaddressType = true;
        addressline1 = true;
        addressline2 = true;
        addresscity = true;
        addressstateProvince = true;
        addresspostalCode = true;
        addresscountryCode = true;
        phoneNumberphoneNumberType = true;
        phoneNumbernumber = true;
        phoneNumberprimaryIndicator = true;
        otherPhoneNumbersphoneNumberphoneNumberType = true;
        otherPhoneNumbersphoneNumbernumber = true;
        otherPhoneNumbersphoneNumberprimaryIndicator = true;
        emailemailType = true;
        emailemailAddress = true;
        otherEmailsemailemailType = true;
        otherEmailsemailemailAddress = true;
        sex = true;
        employerType = true;
        relationshipsrelationshipstudentRefId = true;
        relationshipsrelationshiprelationshipCode = true;
        relationshipsrelationshiprestrictions = true;
        relationshipsrelationshiplivesWith = true;
        relationshipsrelationshipprimaryContactIndicator = true;
        relationshipsrelationshipemergencyContactIndicator = true;
        relationshipsrelationshipfinancialResponsibilityIndicator = true;
        relationshipsrelationshipcustodialIndicator = true;
        relationshipsrelationshipcommunicationsIndicator = true;
        relationshipsrelationshipcontactSequence = true;
    }

    /**
     * @param otherIdsotherIdtype
     * @param loginId
     * @param sex
     * @param namemiddleName
     * @param relationshipsrelationshipfinancialResponsibilityIndicator
     * @param namesuffix
     * @param refId
     * @param otherEmailsemailemailAddress
     * @param localId
     * @param emailemailType
     * @param emailemailAddress
     * @param otherPhoneNumbersphoneNumbernumber
     * @param addresscountryCode
     * @param id
     * @param employerType
     * @param otherNamesnameprefix
     * @param relationshipsrelationshiplivesWith
     * @param otherNamesnamesuffix
     * @param relationshipsrelationshiprestrictions
     * @param relationshipsrelationshiprelationshipCode
     * @param otherEmailsemailemailType
     * @param addresscity
     * @param relationshipsrelationshipcommunicationsIndicator
     * @param phoneNumberprimaryIndicator
     * @param nameprefix
     * @param relationshipsrelationshipstudentRefId
     * @param namefamilyName
     * @param nametype
     * @param namegivenName
     * @param phoneNumbernumber
     * @param addressstateProvince
     * @param addressaddressType
     * @param otherPhoneNumbersphoneNumberphoneNumberType
     * @param relationshipsrelationshipemergencyContactIndicator
     * @param phoneNumberphoneNumberType
     * @param relationshipsrelationshipcustodialIndicator
     * @param otherNamesnamegivenName
     * @param addressline2
     * @param addressline1
     * @param otherPhoneNumbersphoneNumberprimaryIndicator
     * @param otherNamesnamemiddleName
     * @param otherNamesnametype
     * @param otherNamesnamefamilyName
     * @param addresspostalCode
     * @param relationshipsrelationshipprimaryContactIndicator
     * @param relationshipsrelationshipcontactSequence
     * @param otherIdsotherIdid
     */
    public XContactFilter(Boolean refId, Boolean nametype, Boolean nameprefix, Boolean namefamilyName, Boolean namegivenName, Boolean namemiddleName, Boolean namesuffix, Boolean otherNamesnametype, Boolean otherNamesnameprefix, Boolean otherNamesnamefamilyName, Boolean otherNamesnamegivenName, Boolean otherNamesnamemiddleName, Boolean otherNamesnamesuffix, Boolean localId, Boolean loginId, Boolean otherIdsotherIdtype, Boolean otherIdsotherIdid, Boolean addressaddressType, Boolean addressline1, Boolean addressline2, Boolean addresscity, Boolean addressstateProvince, Boolean addresspostalCode, Boolean addresscountryCode, Boolean phoneNumberphoneNumberType, Boolean phoneNumbernumber, Boolean phoneNumberprimaryIndicator, Boolean otherPhoneNumbersphoneNumberphoneNumberType, Boolean otherPhoneNumbersphoneNumbernumber, Boolean otherPhoneNumbersphoneNumberprimaryIndicator, Boolean emailemailType, Boolean emailemailAddress, Boolean otherEmailsemailemailType, Boolean otherEmailsemailemailAddress, Boolean sex, Boolean employerType, Boolean relationshipsrelationshipstudentRefId, Boolean relationshipsrelationshiprelationshipCode, Boolean relationshipsrelationshiprestrictions, Boolean relationshipsrelationshiplivesWith, Boolean relationshipsrelationshipprimaryContactIndicator, Boolean relationshipsrelationshipemergencyContactIndicator, Boolean relationshipsrelationshipfinancialResponsibilityIndicator, Boolean relationshipsrelationshipcustodialIndicator, Boolean relationshipsrelationshipcommunicationsIndicator, Boolean relationshipsrelationshipcontactSequence, Integer id) {
        super();
        this.refId = refId;
        this.nametype = nametype;
        this.nameprefix = nameprefix;
        this.namefamilyName = namefamilyName;
        this.namegivenName = namegivenName;
        this.namemiddleName = namemiddleName;
        this.namesuffix = namesuffix;
        this.otherNamesnametype = otherNamesnametype;
        this.otherNamesnameprefix = otherNamesnameprefix;
        this.otherNamesnamefamilyName = otherNamesnamefamilyName;
        this.otherNamesnamegivenName = otherNamesnamegivenName;
        this.otherNamesnamemiddleName = otherNamesnamemiddleName;
        this.otherNamesnamesuffix = otherNamesnamesuffix;
        this.localId = localId;
        this.loginId = loginId;
        this.otherIdsotherIdtype = otherIdsotherIdtype;
        this.otherIdsotherIdid = otherIdsotherIdid;
        this.addressaddressType = addressaddressType;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.addresscity = addresscity;
        this.addressstateProvince = addressstateProvince;
        this.addresspostalCode = addresspostalCode;
        this.addresscountryCode = addresscountryCode;
        this.phoneNumberphoneNumberType = phoneNumberphoneNumberType;
        this.phoneNumbernumber = phoneNumbernumber;
        this.phoneNumberprimaryIndicator = phoneNumberprimaryIndicator;
        this.otherPhoneNumbersphoneNumberphoneNumberType = otherPhoneNumbersphoneNumberphoneNumberType;
        this.otherPhoneNumbersphoneNumbernumber = otherPhoneNumbersphoneNumbernumber;
        this.otherPhoneNumbersphoneNumberprimaryIndicator = otherPhoneNumbersphoneNumberprimaryIndicator;
        this.emailemailType = emailemailType;
        this.emailemailAddress = emailemailAddress;
        this.otherEmailsemailemailType = otherEmailsemailemailType;
        this.otherEmailsemailemailAddress = otherEmailsemailemailAddress;
        this.sex = sex;
        this.employerType = employerType;
        this.relationshipsrelationshipstudentRefId = relationshipsrelationshipstudentRefId;
        this.relationshipsrelationshiprelationshipCode = relationshipsrelationshiprelationshipCode;
        this.relationshipsrelationshiprestrictions = relationshipsrelationshiprestrictions;
        this.relationshipsrelationshiplivesWith = relationshipsrelationshiplivesWith;
        this.relationshipsrelationshipprimaryContactIndicator = relationshipsrelationshipprimaryContactIndicator;
        this.relationshipsrelationshipemergencyContactIndicator = relationshipsrelationshipemergencyContactIndicator;
        this.relationshipsrelationshipfinancialResponsibilityIndicator = relationshipsrelationshipfinancialResponsibilityIndicator;
        this.relationshipsrelationshipcustodialIndicator = relationshipsrelationshipcustodialIndicator;
        this.relationshipsrelationshipcommunicationsIndicator = relationshipsrelationshipcommunicationsIndicator;
        this.relationshipsrelationshipcontactSequence = relationshipsrelationshipcontactSequence;
        this.id = id;
    }

    @JsonProperty("refId")
    public Boolean getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(Boolean refId) {
        this.refId = refId;
    }

    @JsonProperty("nametype")
    public Boolean getNametype() {
        return nametype;
    }

    @JsonProperty("nametype")
    public void setNametype(Boolean nametype) {
        this.nametype = nametype;
    }

    @JsonProperty("nameprefix")
    public Boolean getNameprefix() {
        return nameprefix;
    }

    @JsonProperty("nameprefix")
    public void setNameprefix(Boolean nameprefix) {
        this.nameprefix = nameprefix;
    }

    @JsonProperty("namefamilyName")
    public Boolean getNamefamilyName() {
        return namefamilyName;
    }

    @JsonProperty("namefamilyName")
    public void setNamefamilyName(Boolean namefamilyName) {
        this.namefamilyName = namefamilyName;
    }

    @JsonProperty("namegivenName")
    public Boolean getNamegivenName() {
        return namegivenName;
    }

    @JsonProperty("namegivenName")
    public void setNamegivenName(Boolean namegivenName) {
        this.namegivenName = namegivenName;
    }

    @JsonProperty("namemiddleName")
    public Boolean getNamemiddleName() {
        return namemiddleName;
    }

    @JsonProperty("namemiddleName")
    public void setNamemiddleName(Boolean namemiddleName) {
        this.namemiddleName = namemiddleName;
    }

    @JsonProperty("namesuffix")
    public Boolean getNamesuffix() {
        return namesuffix;
    }

    @JsonProperty("namesuffix")
    public void setNamesuffix(Boolean namesuffix) {
        this.namesuffix = namesuffix;
    }

    @JsonProperty("otherNamesnametype")
    public Boolean getOtherNamesnametype() {
        return otherNamesnametype;
    }

    @JsonProperty("otherNamesnametype")
    public void setOtherNamesnametype(Boolean otherNamesnametype) {
        this.otherNamesnametype = otherNamesnametype;
    }

    @JsonProperty("otherNamesnameprefix")
    public Boolean getOtherNamesnameprefix() {
        return otherNamesnameprefix;
    }

    @JsonProperty("otherNamesnameprefix")
    public void setOtherNamesnameprefix(Boolean otherNamesnameprefix) {
        this.otherNamesnameprefix = otherNamesnameprefix;
    }

    @JsonProperty("otherNamesnamefamilyName")
    public Boolean getOtherNamesnamefamilyName() {
        return otherNamesnamefamilyName;
    }

    @JsonProperty("otherNamesnamefamilyName")
    public void setOtherNamesnamefamilyName(Boolean otherNamesnamefamilyName) {
        this.otherNamesnamefamilyName = otherNamesnamefamilyName;
    }

    @JsonProperty("otherNamesnamegivenName")
    public Boolean getOtherNamesnamegivenName() {
        return otherNamesnamegivenName;
    }

    @JsonProperty("otherNamesnamegivenName")
    public void setOtherNamesnamegivenName(Boolean otherNamesnamegivenName) {
        this.otherNamesnamegivenName = otherNamesnamegivenName;
    }

    @JsonProperty("otherNamesnamemiddleName")
    public Boolean getOtherNamesnamemiddleName() {
        return otherNamesnamemiddleName;
    }

    @JsonProperty("otherNamesnamemiddleName")
    public void setOtherNamesnamemiddleName(Boolean otherNamesnamemiddleName) {
        this.otherNamesnamemiddleName = otherNamesnamemiddleName;
    }

    @JsonProperty("otherNamesnamesuffix")
    public Boolean getOtherNamesnamesuffix() {
        return otherNamesnamesuffix;
    }

    @JsonProperty("otherNamesnamesuffix")
    public void setOtherNamesnamesuffix(Boolean otherNamesnamesuffix) {
        this.otherNamesnamesuffix = otherNamesnamesuffix;
    }

    @JsonProperty("localId")
    public Boolean getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(Boolean localId) {
        this.localId = localId;
    }

    @JsonProperty("loginId")
    public Boolean getLoginId() {
        return loginId;
    }

    @JsonProperty("loginId")
    public void setLoginId(Boolean loginId) {
        this.loginId = loginId;
    }

    @JsonProperty("otherIdsotherIdtype")
    public Boolean getOtherIdsotherIdtype() {
        return otherIdsotherIdtype;
    }

    @JsonProperty("otherIdsotherIdtype")
    public void setOtherIdsotherIdtype(Boolean otherIdsotherIdtype) {
        this.otherIdsotherIdtype = otherIdsotherIdtype;
    }

    @JsonProperty("otherIdsotherIdid")
    public Boolean getOtherIdsotherIdid() {
        return otherIdsotherIdid;
    }

    @JsonProperty("otherIdsotherIdid")
    public void setOtherIdsotherIdid(Boolean otherIdsotherIdid) {
        this.otherIdsotherIdid = otherIdsotherIdid;
    }

    @JsonProperty("addressaddressType")
    public Boolean getAddressaddressType() {
        return addressaddressType;
    }

    @JsonProperty("addressaddressType")
    public void setAddressaddressType(Boolean addressaddressType) {
        this.addressaddressType = addressaddressType;
    }

    @JsonProperty("addressline1")
    public Boolean getAddressline1() {
        return addressline1;
    }

    @JsonProperty("addressline1")
    public void setAddressline1(Boolean addressline1) {
        this.addressline1 = addressline1;
    }

    @JsonProperty("addressline2")
    public Boolean getAddressline2() {
        return addressline2;
    }

    @JsonProperty("addressline2")
    public void setAddressline2(Boolean addressline2) {
        this.addressline2 = addressline2;
    }

    @JsonProperty("addresscity")
    public Boolean getAddresscity() {
        return addresscity;
    }

    @JsonProperty("addresscity")
    public void setAddresscity(Boolean addresscity) {
        this.addresscity = addresscity;
    }

    @JsonProperty("addressstateProvince")
    public Boolean getAddressstateProvince() {
        return addressstateProvince;
    }

    @JsonProperty("addressstateProvince")
    public void setAddressstateProvince(Boolean addressstateProvince) {
        this.addressstateProvince = addressstateProvince;
    }

    @JsonProperty("addresspostalCode")
    public Boolean getAddresspostalCode() {
        return addresspostalCode;
    }

    @JsonProperty("addresspostalCode")
    public void setAddresspostalCode(Boolean addresspostalCode) {
        this.addresspostalCode = addresspostalCode;
    }

    @JsonProperty("addresscountryCode")
    public Boolean getAddresscountryCode() {
        return addresscountryCode;
    }

    @JsonProperty("addresscountryCode")
    public void setAddresscountryCode(Boolean addresscountryCode) {
        this.addresscountryCode = addresscountryCode;
    }

    @JsonProperty("phoneNumberphoneNumberType")
    public Boolean getPhoneNumberphoneNumberType() {
        return phoneNumberphoneNumberType;
    }

    @JsonProperty("phoneNumberphoneNumberType")
    public void setPhoneNumberphoneNumberType(Boolean phoneNumberphoneNumberType) {
        this.phoneNumberphoneNumberType = phoneNumberphoneNumberType;
    }

    @JsonProperty("phoneNumbernumber")
    public Boolean getPhoneNumbernumber() {
        return phoneNumbernumber;
    }

    @JsonProperty("phoneNumbernumber")
    public void setPhoneNumbernumber(Boolean phoneNumbernumber) {
        this.phoneNumbernumber = phoneNumbernumber;
    }

    @JsonProperty("phoneNumberprimaryIndicator")
    public Boolean getPhoneNumberprimaryIndicator() {
        return phoneNumberprimaryIndicator;
    }

    @JsonProperty("phoneNumberprimaryIndicator")
    public void setPhoneNumberprimaryIndicator(Boolean phoneNumberprimaryIndicator) {
        this.phoneNumberprimaryIndicator = phoneNumberprimaryIndicator;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberphoneNumberType")
    public Boolean getOtherPhoneNumbersphoneNumberphoneNumberType() {
        return otherPhoneNumbersphoneNumberphoneNumberType;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberphoneNumberType")
    public void setOtherPhoneNumbersphoneNumberphoneNumberType(Boolean otherPhoneNumbersphoneNumberphoneNumberType) {
        this.otherPhoneNumbersphoneNumberphoneNumberType = otherPhoneNumbersphoneNumberphoneNumberType;
    }

    @JsonProperty("otherPhoneNumbersphoneNumbernumber")
    public Boolean getOtherPhoneNumbersphoneNumbernumber() {
        return otherPhoneNumbersphoneNumbernumber;
    }

    @JsonProperty("otherPhoneNumbersphoneNumbernumber")
    public void setOtherPhoneNumbersphoneNumbernumber(Boolean otherPhoneNumbersphoneNumbernumber) {
        this.otherPhoneNumbersphoneNumbernumber = otherPhoneNumbersphoneNumbernumber;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberprimaryIndicator")
    public Boolean getOtherPhoneNumbersphoneNumberprimaryIndicator() {
        return otherPhoneNumbersphoneNumberprimaryIndicator;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberprimaryIndicator")
    public void setOtherPhoneNumbersphoneNumberprimaryIndicator(Boolean otherPhoneNumbersphoneNumberprimaryIndicator) {
        this.otherPhoneNumbersphoneNumberprimaryIndicator = otherPhoneNumbersphoneNumberprimaryIndicator;
    }

    @JsonProperty("emailemailType")
    public Boolean getEmailemailType() {
        return emailemailType;
    }

    @JsonProperty("emailemailType")
    public void setEmailemailType(Boolean emailemailType) {
        this.emailemailType = emailemailType;
    }

    @JsonProperty("emailemailAddress")
    public Boolean getEmailemailAddress() {
        return emailemailAddress;
    }

    @JsonProperty("emailemailAddress")
    public void setEmailemailAddress(Boolean emailemailAddress) {
        this.emailemailAddress = emailemailAddress;
    }

    @JsonProperty("otherEmailsemailemailType")
    public Boolean getOtherEmailsemailemailType() {
        return otherEmailsemailemailType;
    }

    @JsonProperty("otherEmailsemailemailType")
    public void setOtherEmailsemailemailType(Boolean otherEmailsemailemailType) {
        this.otherEmailsemailemailType = otherEmailsemailemailType;
    }

    @JsonProperty("otherEmailsemailemailAddress")
    public Boolean getOtherEmailsemailemailAddress() {
        return otherEmailsemailemailAddress;
    }

    @JsonProperty("otherEmailsemailemailAddress")
    public void setOtherEmailsemailemailAddress(Boolean otherEmailsemailemailAddress) {
        this.otherEmailsemailemailAddress = otherEmailsemailemailAddress;
    }

    @JsonProperty("sex")
    public Boolean getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @JsonProperty("employerType")
    public Boolean getEmployerType() {
        return employerType;
    }

    @JsonProperty("employerType")
    public void setEmployerType(Boolean employerType) {
        this.employerType = employerType;
    }

    @JsonProperty("relationshipsrelationshipstudentRefId")
    public Boolean getRelationshipsrelationshipstudentRefId() {
        return relationshipsrelationshipstudentRefId;
    }

    @JsonProperty("relationshipsrelationshipstudentRefId")
    public void setRelationshipsrelationshipstudentRefId(Boolean relationshipsrelationshipstudentRefId) {
        this.relationshipsrelationshipstudentRefId = relationshipsrelationshipstudentRefId;
    }

    @JsonProperty("relationshipsrelationshiprelationshipCode")
    public Boolean getRelationshipsrelationshiprelationshipCode() {
        return relationshipsrelationshiprelationshipCode;
    }

    @JsonProperty("relationshipsrelationshiprelationshipCode")
    public void setRelationshipsrelationshiprelationshipCode(Boolean relationshipsrelationshiprelationshipCode) {
        this.relationshipsrelationshiprelationshipCode = relationshipsrelationshiprelationshipCode;
    }

    @JsonProperty("relationshipsrelationshiprestrictions")
    public Boolean getRelationshipsrelationshiprestrictions() {
        return relationshipsrelationshiprestrictions;
    }

    @JsonProperty("relationshipsrelationshiprestrictions")
    public void setRelationshipsrelationshiprestrictions(Boolean relationshipsrelationshiprestrictions) {
        this.relationshipsrelationshiprestrictions = relationshipsrelationshiprestrictions;
    }

    @JsonProperty("relationshipsrelationshiplivesWith")
    public Boolean getRelationshipsrelationshiplivesWith() {
        return relationshipsrelationshiplivesWith;
    }

    @JsonProperty("relationshipsrelationshiplivesWith")
    public void setRelationshipsrelationshiplivesWith(Boolean relationshipsrelationshiplivesWith) {
        this.relationshipsrelationshiplivesWith = relationshipsrelationshiplivesWith;
    }

    @JsonProperty("relationshipsrelationshipprimaryContactIndicator")
    public Boolean getRelationshipsrelationshipprimaryContactIndicator() {
        return relationshipsrelationshipprimaryContactIndicator;
    }

    @JsonProperty("relationshipsrelationshipprimaryContactIndicator")
    public void setRelationshipsrelationshipprimaryContactIndicator(Boolean relationshipsrelationshipprimaryContactIndicator) {
        this.relationshipsrelationshipprimaryContactIndicator = relationshipsrelationshipprimaryContactIndicator;
    }

    @JsonProperty("relationshipsrelationshipemergencyContactIndicator")
    public Boolean getRelationshipsrelationshipemergencyContactIndicator() {
        return relationshipsrelationshipemergencyContactIndicator;
    }

    @JsonProperty("relationshipsrelationshipemergencyContactIndicator")
    public void setRelationshipsrelationshipemergencyContactIndicator(Boolean relationshipsrelationshipemergencyContactIndicator) {
        this.relationshipsrelationshipemergencyContactIndicator = relationshipsrelationshipemergencyContactIndicator;
    }

    @JsonProperty("relationshipsrelationshipfinancialResponsibilityIndicator")
    public Boolean getRelationshipsrelationshipfinancialResponsibilityIndicator() {
        return relationshipsrelationshipfinancialResponsibilityIndicator;
    }

    @JsonProperty("relationshipsrelationshipfinancialResponsibilityIndicator")
    public void setRelationshipsrelationshipfinancialResponsibilityIndicator(Boolean relationshipsrelationshipfinancialResponsibilityIndicator) {
        this.relationshipsrelationshipfinancialResponsibilityIndicator = relationshipsrelationshipfinancialResponsibilityIndicator;
    }

    @JsonProperty("relationshipsrelationshipcustodialIndicator")
    public Boolean getRelationshipsrelationshipcustodialIndicator() {
        return relationshipsrelationshipcustodialIndicator;
    }

    @JsonProperty("relationshipsrelationshipcustodialIndicator")
    public void setRelationshipsrelationshipcustodialIndicator(Boolean relationshipsrelationshipcustodialIndicator) {
        this.relationshipsrelationshipcustodialIndicator = relationshipsrelationshipcustodialIndicator;
    }

    @JsonProperty("relationshipsrelationshipcommunicationsIndicator")
    public Boolean getRelationshipsrelationshipcommunicationsIndicator() {
        return relationshipsrelationshipcommunicationsIndicator;
    }

    @JsonProperty("relationshipsrelationshipcommunicationsIndicator")
    public void setRelationshipsrelationshipcommunicationsIndicator(Boolean relationshipsrelationshipcommunicationsIndicator) {
        this.relationshipsrelationshipcommunicationsIndicator = relationshipsrelationshipcommunicationsIndicator;
    }

    @JsonProperty("relationshipsrelationshipcontactSequence")
    public Boolean getRelationshipsrelationshipcontactSequence() {
        return relationshipsrelationshipcontactSequence;
    }

    @JsonProperty("relationshipsrelationshipcontactSequence")
    public void setRelationshipsrelationshipcontactSequence(Boolean relationshipsrelationshipcontactSequence) {
        this.relationshipsrelationshipcontactSequence = relationshipsrelationshipcontactSequence;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

}