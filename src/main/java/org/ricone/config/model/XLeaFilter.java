package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "localId", "leaRefId", "stateProvinceId", "ncesId", "leaName", "addressaddressType", "addressline1", "addressline2", "addresscity", "addressstateProvince", "addresscountryCode", "addresspostalCode", "phoneNumberphoneNumberType", "phoneNumbernumber", "phoneNumberprimaryIndicator", "otherPhoneNumbersphoneNumberphoneNumberType", "otherPhoneNumbersphoneNumbernumber", "otherPhoneNumbersphoneNumberprimaryIndicator", "id"})
public class XLeaFilter implements IFilter<XLeaFilter>, Serializable {
    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("localId")
    private Boolean localId;
    @JsonProperty("leaRefId")
    private Boolean leaRefId;
    @JsonProperty("stateProvinceId")
    private Boolean stateProvinceId;
    @JsonProperty("ncesId")
    private Boolean ncesId;
    @JsonProperty("leaName")
    private Boolean leaName;
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
    private final static long serialVersionUID = 8600227280515324823L;

    /**
     * No args constructor for use in serialization
     */
    public XLeaFilter() {
        refId = true;
        localId = true;
        leaRefId = true;
        stateProvinceId = true;
        ncesId = true;
        leaName = true;
        addressaddressType = true;
        addressline1 = true;
        addressline2 = true;
        addresscity = true;
        addressstateProvince = true;
        addresscountryCode = true;
        addresspostalCode = true;
        phoneNumberphoneNumberType = true;
        phoneNumbernumber = true;
        phoneNumberprimaryIndicator = true;
        otherPhoneNumbersphoneNumberphoneNumberType = true;
        otherPhoneNumbersphoneNumbernumber = true;
        otherPhoneNumbersphoneNumberprimaryIndicator = true;
    }

    /**
     * @param leaName
     * @param leaRefId
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
     * @param ncesId
     * @param addressline1
     * @param otherPhoneNumbersphoneNumberprimaryIndicator
     * @param stateProvinceId
     * @param addresscity
     * @param phoneNumberprimaryIndicator
     * @param addresspostalCode
     */
    public XLeaFilter(Boolean refId, Boolean localId, Boolean leaRefId, Boolean stateProvinceId, Boolean ncesId, Boolean leaName, Boolean addressaddressType, Boolean addressline1, Boolean addressline2, Boolean addresscity, Boolean addressstateProvince, Boolean addresscountryCode, Boolean addresspostalCode, Boolean phoneNumberphoneNumberType, Boolean phoneNumbernumber, Boolean phoneNumberprimaryIndicator, Boolean otherPhoneNumbersphoneNumberphoneNumberType, Boolean otherPhoneNumbersphoneNumbernumber, Boolean otherPhoneNumbersphoneNumberprimaryIndicator, Integer id) {
        super();
        this.refId = refId;
        this.localId = localId;
        this.leaRefId = leaRefId;
        this.stateProvinceId = stateProvinceId;
        this.ncesId = ncesId;
        this.leaName = leaName;
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

    @JsonProperty("localId")
    public Boolean getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(Boolean localId) {
        this.localId = localId;
    }

    @JsonProperty("leaRefId")
    public Boolean getLeaRefId() {
        return leaRefId;
    }

    @JsonProperty("leaRefId")
    public void setLeaRefId(Boolean leaRefId) {
        this.leaRefId = leaRefId;
    }

    @JsonProperty("stateProvinceId")
    public Boolean getStateProvinceId() {
        return stateProvinceId;
    }

    @JsonProperty("stateProvinceId")
    public void setStateProvinceId(Boolean stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    @JsonProperty("ncesId")
    public Boolean getNcesId() {
        return ncesId;
    }

    @JsonProperty("ncesId")
    public void setNcesId(Boolean ncesId) {
        this.ncesId = ncesId;
    }

    @JsonProperty("leaName")
    public Boolean getLeaName() {
        return leaName;
    }

    @JsonProperty("leaName")
    public void setLeaName(Boolean leaName) {
        this.leaName = leaName;
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