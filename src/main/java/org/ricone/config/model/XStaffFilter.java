package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "nametype", "nameprefix", "namefamilyName", "namegivenName", "namemiddleName", "namesuffix", "localId", "stateProvinceId", "loginId", "otherIdsotherIdtype", "otherIdsotherIdid", "sex", "emailemailType", "emailemailAddress", "primaryAssignmentleaRefId", "primaryAssignmentschoolRefId", "primaryAssignmentjobFunction", "otherAssignmentsstaffPersonAssignmentleaRefId", "otherAssignmentsstaffPersonAssignmentschoolRefId", "otherAssignmentsstaffPersonAssignmentjobFunction", "id"})
public class XStaffFilter implements IFilter<XStaffFilter>, Serializable {

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
    @JsonProperty("localId")
    private Boolean localId;
    @JsonProperty("stateProvinceId")
    private Boolean stateProvinceId;
    @JsonProperty("loginId")
    private Boolean loginId;
    @JsonProperty("otherIdsotherIdtype")
    private Boolean otherIdsotherIdtype;
    @JsonProperty("otherIdsotherIdid")
    private Boolean otherIdsotherIdid;
    @JsonProperty("sex")
    private Boolean sex;
    @JsonProperty("emailemailType")
    private Boolean emailemailType;
    @JsonProperty("emailemailAddress")
    private Boolean emailemailAddress;
    @JsonProperty("primaryAssignmentleaRefId")
    private Boolean primaryAssignmentleaRefId;
    @JsonProperty("primaryAssignmentschoolRefId")
    private Boolean primaryAssignmentschoolRefId;
    @JsonProperty("primaryAssignmentjobFunction")
    private Boolean primaryAssignmentjobFunction;
    @JsonProperty("otherAssignmentsstaffPersonAssignmentleaRefId")
    private Boolean otherAssignmentsstaffPersonAssignmentleaRefId;
    @JsonProperty("otherAssignmentsstaffPersonAssignmentschoolRefId")
    private Boolean otherAssignmentsstaffPersonAssignmentschoolRefId;
    @JsonProperty("otherAssignmentsstaffPersonAssignmentjobFunction")
    private Boolean otherAssignmentsstaffPersonAssignmentjobFunction;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = -7275438080718087005L;

    /**
     * No args constructor for use in serialization
     */
    public XStaffFilter() {
        refId = true;
        nametype = true;
        nameprefix = true;
        namefamilyName = true;
        namegivenName = true;
        namemiddleName = true;
        namesuffix = true;
        localId = true;
        stateProvinceId = true;
        loginId = true;
        otherIdsotherIdtype = true;
        otherIdsotherIdid = true;
        sex = true;
        emailemailType = true;
        emailemailAddress = true;
        primaryAssignmentleaRefId = true;
        primaryAssignmentschoolRefId = true;
        primaryAssignmentjobFunction = true;
        otherAssignmentsstaffPersonAssignmentleaRefId = true;
        otherAssignmentsstaffPersonAssignmentschoolRefId = true;
        otherAssignmentsstaffPersonAssignmentjobFunction = true;
    }

    /**
     * @param otherIdsotherIdtype
     * @param primaryAssignmentleaRefId
     * @param namefamilyName
     * @param loginId
     * @param sex
     * @param nametype
     * @param namegivenName
     * @param namemiddleName
     * @param namesuffix
     * @param refId
     * @param localId
     * @param emailemailType
     * @param emailemailAddress
     * @param otherAssignmentsstaffPersonAssignmentschoolRefId
     * @param id
     * @param otherAssignmentsstaffPersonAssignmentjobFunction
     * @param otherAssignmentsstaffPersonAssignmentleaRefId
     * @param primaryAssignmentschoolRefId
     * @param primaryAssignmentjobFunction
     * @param stateProvinceId
     * @param nameprefix
     * @param otherIdsotherIdid
     */
    public XStaffFilter(Boolean refId, Boolean nametype, Boolean nameprefix, Boolean namefamilyName, Boolean namegivenName, Boolean namemiddleName, Boolean namesuffix, Boolean localId, Boolean stateProvinceId, Boolean loginId, Boolean otherIdsotherIdtype, Boolean otherIdsotherIdid, Boolean sex, Boolean emailemailType, Boolean emailemailAddress, Boolean primaryAssignmentleaRefId, Boolean primaryAssignmentschoolRefId, Boolean primaryAssignmentjobFunction, Boolean otherAssignmentsstaffPersonAssignmentleaRefId, Boolean otherAssignmentsstaffPersonAssignmentschoolRefId, Boolean otherAssignmentsstaffPersonAssignmentjobFunction, Integer id) {
        super();
        this.refId = refId;
        this.nametype = nametype;
        this.nameprefix = nameprefix;
        this.namefamilyName = namefamilyName;
        this.namegivenName = namegivenName;
        this.namemiddleName = namemiddleName;
        this.namesuffix = namesuffix;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.loginId = loginId;
        this.otherIdsotherIdtype = otherIdsotherIdtype;
        this.otherIdsotherIdid = otherIdsotherIdid;
        this.sex = sex;
        this.emailemailType = emailemailType;
        this.emailemailAddress = emailemailAddress;
        this.primaryAssignmentleaRefId = primaryAssignmentleaRefId;
        this.primaryAssignmentschoolRefId = primaryAssignmentschoolRefId;
        this.primaryAssignmentjobFunction = primaryAssignmentjobFunction;
        this.otherAssignmentsstaffPersonAssignmentleaRefId = otherAssignmentsstaffPersonAssignmentleaRefId;
        this.otherAssignmentsstaffPersonAssignmentschoolRefId = otherAssignmentsstaffPersonAssignmentschoolRefId;
        this.otherAssignmentsstaffPersonAssignmentjobFunction = otherAssignmentsstaffPersonAssignmentjobFunction;
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

    @JsonProperty("sex")
    public Boolean getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(Boolean sex) {
        this.sex = sex;
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

    @JsonProperty("primaryAssignmentleaRefId")
    public Boolean getPrimaryAssignmentleaRefId() {
        return primaryAssignmentleaRefId;
    }

    @JsonProperty("primaryAssignmentleaRefId")
    public void setPrimaryAssignmentleaRefId(Boolean primaryAssignmentleaRefId) {
        this.primaryAssignmentleaRefId = primaryAssignmentleaRefId;
    }

    @JsonProperty("primaryAssignmentschoolRefId")
    public Boolean getPrimaryAssignmentschoolRefId() {
        return primaryAssignmentschoolRefId;
    }

    @JsonProperty("primaryAssignmentschoolRefId")
    public void setPrimaryAssignmentschoolRefId(Boolean primaryAssignmentschoolRefId) {
        this.primaryAssignmentschoolRefId = primaryAssignmentschoolRefId;
    }

    @JsonProperty("primaryAssignmentjobFunction")
    public Boolean getPrimaryAssignmentjobFunction() {
        return primaryAssignmentjobFunction;
    }

    @JsonProperty("primaryAssignmentjobFunction")
    public void setPrimaryAssignmentjobFunction(Boolean primaryAssignmentjobFunction) {
        this.primaryAssignmentjobFunction = primaryAssignmentjobFunction;
    }

    @JsonProperty("otherAssignmentsstaffPersonAssignmentleaRefId")
    public Boolean getOtherAssignmentsstaffPersonAssignmentleaRefId() {
        return otherAssignmentsstaffPersonAssignmentleaRefId;
    }

    @JsonProperty("otherAssignmentsstaffPersonAssignmentleaRefId")
    public void setOtherAssignmentsstaffPersonAssignmentleaRefId(Boolean otherAssignmentsstaffPersonAssignmentleaRefId) {
        this.otherAssignmentsstaffPersonAssignmentleaRefId = otherAssignmentsstaffPersonAssignmentleaRefId;
    }

    @JsonProperty("otherAssignmentsstaffPersonAssignmentschoolRefId")
    public Boolean getOtherAssignmentsstaffPersonAssignmentschoolRefId() {
        return otherAssignmentsstaffPersonAssignmentschoolRefId;
    }

    @JsonProperty("otherAssignmentsstaffPersonAssignmentschoolRefId")
    public void setOtherAssignmentsstaffPersonAssignmentschoolRefId(Boolean otherAssignmentsstaffPersonAssignmentschoolRefId) {
        this.otherAssignmentsstaffPersonAssignmentschoolRefId = otherAssignmentsstaffPersonAssignmentschoolRefId;
    }

    @JsonProperty("otherAssignmentsstaffPersonAssignmentjobFunction")
    public Boolean getOtherAssignmentsstaffPersonAssignmentjobFunction() {
        return otherAssignmentsstaffPersonAssignmentjobFunction;
    }

    @JsonProperty("otherAssignmentsstaffPersonAssignmentjobFunction")
    public void setOtherAssignmentsstaffPersonAssignmentjobFunction(Boolean otherAssignmentsstaffPersonAssignmentjobFunction) {
        this.otherAssignmentsstaffPersonAssignmentjobFunction = otherAssignmentsstaffPersonAssignmentjobFunction;
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