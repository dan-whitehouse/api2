package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "leaRefId", "localId", "stateProvinceId", "otherIdsotherIdtype", "otherIdsotherIdid", "schoolName", "gradeLevels", "addressaddressType", "addressline1", "addressline2", "addresscity", "addressstateProvince", "addresscountryCode", "addresspostalCode", "phoneNumberphoneNumberType", "phoneNumbernumber", "phoneNumberprimaryIndicator", "otherPhoneNumbersphoneNumberphoneNumberType", "otherPhoneNumbersphoneNumbernumber", "otherPhoneNumbersphoneNumberprimaryIndicator", "id"})
public class XSchoolFilter implements IFilter<XSchoolFilter>, Serializable {

    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("leaRefId")
    private Boolean leaRefId;
    @JsonProperty("localId")
    private Boolean localId;
    @JsonProperty("stateProvinceId")
    private Boolean stateProvinceId;
    @JsonProperty("otherIdsotherIdtype")
    private Boolean otherIdsotherIdtype;
    @JsonProperty("otherIdsotherIdid")
    private Boolean otherIdsotherIdid;
    @JsonProperty("schoolName")
    private Boolean schoolName;
    @JsonProperty("gradeLevels")
    private Boolean gradeLevels;
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
    @JsonProperty("addresscountryCode")
    private Boolean addresscountryCode;
    @JsonProperty("addresspostalCode")
    private Boolean addresspostalCode;
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
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = 3618480603880597000L;

    /**
     * No args constructor for use in serialization
     */
    public XSchoolFilter() {
    }

    /**
     * @param otherIdsotherIdtype
     * @param leaRefId
     * @param schoolName
     * @param phoneNumbernumber
     * @param addressstateProvince
     * @param addressaddressType
     * @param otherPhoneNumbersphoneNumberphoneNumberType
     * @param refId
     * @param phoneNumberphoneNumberType
     * @param localId
     * @param otherPhoneNumbersphoneNumbernumber
     * @param addresscountryCode
     * @param id
     * @param addressline2
     * @param addressline1
     * @param gradeLevels
     * @param otherPhoneNumbersphoneNumberprimaryIndicator
     * @param stateProvinceId
     * @param addresscity
     * @param phoneNumberprimaryIndicator
     * @param addresspostalCode
     * @param otherIdsotherIdid
     */
    public XSchoolFilter(Boolean refId, Boolean leaRefId, Boolean localId, Boolean stateProvinceId, Boolean otherIdsotherIdtype, Boolean otherIdsotherIdid, Boolean schoolName, Boolean gradeLevels, Boolean addressaddressType, Boolean addressline1, Boolean addressline2, Boolean addresscity, Boolean addressstateProvince, Boolean addresscountryCode, Boolean addresspostalCode, Boolean phoneNumberphoneNumberType, Boolean phoneNumbernumber, Boolean phoneNumberprimaryIndicator, Boolean otherPhoneNumbersphoneNumberphoneNumberType, Boolean otherPhoneNumbersphoneNumbernumber, Boolean otherPhoneNumbersphoneNumberprimaryIndicator, Integer id) {
        super();
        this.refId = refId;
        this.leaRefId = leaRefId;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.otherIdsotherIdtype = otherIdsotherIdtype;
        this.otherIdsotherIdid = otherIdsotherIdid;
        this.schoolName = schoolName;
        this.gradeLevels = gradeLevels;
        this.addressaddressType = addressaddressType;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.addresscity = addresscity;
        this.addressstateProvince = addressstateProvince;
        this.addresscountryCode = addresscountryCode;
        this.addresspostalCode = addresspostalCode;
        this.phoneNumberphoneNumberType = phoneNumberphoneNumberType;
        this.phoneNumbernumber = phoneNumbernumber;
        this.phoneNumberprimaryIndicator = phoneNumberprimaryIndicator;
        this.otherPhoneNumbersphoneNumberphoneNumberType = otherPhoneNumbersphoneNumberphoneNumberType;
        this.otherPhoneNumbersphoneNumbernumber = otherPhoneNumbersphoneNumbernumber;
        this.otherPhoneNumbersphoneNumberprimaryIndicator = otherPhoneNumbersphoneNumberprimaryIndicator;
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

    @JsonProperty("leaRefId")
    public Boolean getLeaRefId() {
        return leaRefId;
    }

    @JsonProperty("leaRefId")
    public void setLeaRefId(Boolean leaRefId) {
        this.leaRefId = leaRefId;
    }

    @JsonProperty("localId")
    public Boolean getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(Boolean localId) {
        this.localId = localId;
    }

    @JsonProperty("stateProvinceId")
    public Boolean getStateProvinceId() {
        return stateProvinceId;
    }

    @JsonProperty("stateProvinceId")
    public void setStateProvinceId(Boolean stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
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

    @JsonProperty("schoolName")
    public Boolean getSchoolName() {
        return schoolName;
    }

    @JsonProperty("schoolName")
    public void setSchoolName(Boolean schoolName) {
        this.schoolName = schoolName;
    }

    @JsonProperty("gradeLevels")
    public Boolean getGradeLevels() {
        return gradeLevels;
    }

    @JsonProperty("gradeLevels")
    public void setGradeLevels(Boolean gradeLevels) {
        this.gradeLevels = gradeLevels;
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

    @JsonProperty("addresscountryCode")
    public Boolean getAddresscountryCode() {
        return addresscountryCode;
    }

    @JsonProperty("addresscountryCode")
    public void setAddresscountryCode(Boolean addresscountryCode) {
        this.addresscountryCode = addresscountryCode;
    }

    @JsonProperty("addresspostalCode")
    public Boolean getAddresspostalCode() {
        return addresspostalCode;
    }

    @JsonProperty("addresspostalCode")
    public void setAddresspostalCode(Boolean addresspostalCode) {
        this.addresspostalCode = addresspostalCode;
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

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

}