package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "schoolRefId", "schoolYear", "sessionssessionListsessionType", "sessionssessionListsessionCode", "sessionssessionListdescription", "sessionssessionListmarkingTerm", "sessionssessionListschedulingTerm", "sessionssessionListlinkedSessionCode", "sessionssessionListstartDate", "sessionssessionListendDate", "id"})
public class XCalendarFilter implements IFilter<XCalendarFilter>, Serializable {

    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("schoolRefId")
    private Boolean schoolRefId;
    @JsonProperty("schoolYear")
    private Boolean schoolYear;
    @JsonProperty("sessionssessionListsessionType")
    private Boolean sessionssessionListsessionType;
    @JsonProperty("sessionssessionListsessionCode")
    private Boolean sessionssessionListsessionCode;
    @JsonProperty("sessionssessionListdescription")
    private Boolean sessionssessionListdescription;
    @JsonProperty("sessionssessionListmarkingTerm")
    private Boolean sessionssessionListmarkingTerm;
    @JsonProperty("sessionssessionListschedulingTerm")
    private Boolean sessionssessionListschedulingTerm;
    @JsonProperty("sessionssessionListlinkedSessionCode")
    private Boolean sessionssessionListlinkedSessionCode;
    @JsonProperty("sessionssessionListstartDate")
    private Boolean sessionssessionListstartDate;
    @JsonProperty("sessionssessionListendDate")
    private Boolean sessionssessionListendDate;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = -4524526063568415901L;

    /**
     * No args constructor for use in serialization
     */
    public XCalendarFilter() {
        refId = true;
        schoolRefId = true;
        schoolYear = true;
        sessionssessionListsessionType = true;
        sessionssessionListsessionCode = true;
        sessionssessionListdescription = true;
        sessionssessionListmarkingTerm = true;
        sessionssessionListschedulingTerm = true;
        sessionssessionListlinkedSessionCode = true;
        sessionssessionListstartDate = true;
        sessionssessionListendDate = true;
    }

    /**
     * @param id
     * @param sessionssessionListmarkingTerm
     * @param sessionssessionListsessionType
     * @param schoolYear
     * @param sessionssessionListlinkedSessionCode
     * @param sessionssessionListsessionCode
     * @param sessionssessionListdescription
     * @param refId
     * @param sessionssessionListstartDate
     * @param sessionssessionListendDate
     * @param sessionssessionListschedulingTerm
     * @param schoolRefId
     */
    public XCalendarFilter(Boolean refId, Boolean schoolRefId, Boolean schoolYear, Boolean sessionssessionListsessionType, Boolean sessionssessionListsessionCode, Boolean sessionssessionListdescription, Boolean sessionssessionListmarkingTerm, Boolean sessionssessionListschedulingTerm, Boolean sessionssessionListlinkedSessionCode, Boolean sessionssessionListstartDate, Boolean sessionssessionListendDate, Integer id) {
        super();
        this.refId = refId;
        this.schoolRefId = schoolRefId;
        this.schoolYear = schoolYear;
        this.sessionssessionListsessionType = sessionssessionListsessionType;
        this.sessionssessionListsessionCode = sessionssessionListsessionCode;
        this.sessionssessionListdescription = sessionssessionListdescription;
        this.sessionssessionListmarkingTerm = sessionssessionListmarkingTerm;
        this.sessionssessionListschedulingTerm = sessionssessionListschedulingTerm;
        this.sessionssessionListlinkedSessionCode = sessionssessionListlinkedSessionCode;
        this.sessionssessionListstartDate = sessionssessionListstartDate;
        this.sessionssessionListendDate = sessionssessionListendDate;
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

    @JsonProperty("schoolRefId")
    public Boolean getSchoolRefId() {
        return schoolRefId;
    }

    @JsonProperty("schoolRefId")
    public void setSchoolRefId(Boolean schoolRefId) {
        this.schoolRefId = schoolRefId;
    }

    @JsonProperty("schoolYear")
    public Boolean getSchoolYear() {
        return schoolYear;
    }

    @JsonProperty("schoolYear")
    public void setSchoolYear(Boolean schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonProperty("sessionssessionListsessionType")
    public Boolean getSessionssessionListsessionType() {
        return sessionssessionListsessionType;
    }

    @JsonProperty("sessionssessionListsessionType")
    public void setSessionssessionListsessionType(Boolean sessionssessionListsessionType) {
        this.sessionssessionListsessionType = sessionssessionListsessionType;
    }

    @JsonProperty("sessionssessionListsessionCode")
    public Boolean getSessionssessionListsessionCode() {
        return sessionssessionListsessionCode;
    }

    @JsonProperty("sessionssessionListsessionCode")
    public void setSessionssessionListsessionCode(Boolean sessionssessionListsessionCode) {
        this.sessionssessionListsessionCode = sessionssessionListsessionCode;
    }

    @JsonProperty("sessionssessionListdescription")
    public Boolean getSessionssessionListdescription() {
        return sessionssessionListdescription;
    }

    @JsonProperty("sessionssessionListdescription")
    public void setSessionssessionListdescription(Boolean sessionssessionListdescription) {
        this.sessionssessionListdescription = sessionssessionListdescription;
    }

    @JsonProperty("sessionssessionListmarkingTerm")
    public Boolean getSessionssessionListmarkingTerm() {
        return sessionssessionListmarkingTerm;
    }

    @JsonProperty("sessionssessionListmarkingTerm")
    public void setSessionssessionListmarkingTerm(Boolean sessionssessionListmarkingTerm) {
        this.sessionssessionListmarkingTerm = sessionssessionListmarkingTerm;
    }

    @JsonProperty("sessionssessionListschedulingTerm")
    public Boolean getSessionssessionListschedulingTerm() {
        return sessionssessionListschedulingTerm;
    }

    @JsonProperty("sessionssessionListschedulingTerm")
    public void setSessionssessionListschedulingTerm(Boolean sessionssessionListschedulingTerm) {
        this.sessionssessionListschedulingTerm = sessionssessionListschedulingTerm;
    }

    @JsonProperty("sessionssessionListlinkedSessionCode")
    public Boolean getSessionssessionListlinkedSessionCode() {
        return sessionssessionListlinkedSessionCode;
    }

    @JsonProperty("sessionssessionListlinkedSessionCode")
    public void setSessionssessionListlinkedSessionCode(Boolean sessionssessionListlinkedSessionCode) {
        this.sessionssessionListlinkedSessionCode = sessionssessionListlinkedSessionCode;
    }

    @JsonProperty("sessionssessionListstartDate")
    public Boolean getSessionssessionListstartDate() {
        return sessionssessionListstartDate;
    }

    @JsonProperty("sessionssessionListstartDate")
    public void setSessionssessionListstartDate(Boolean sessionssessionListstartDate) {
        this.sessionssessionListstartDate = sessionssessionListstartDate;
    }

    @JsonProperty("sessionssessionListendDate")
    public Boolean getSessionssessionListendDate() {
        return sessionssessionListendDate;
    }

    @JsonProperty("sessionssessionListendDate")
    public void setSessionssessionListendDate(Boolean sessionssessionListendDate) {
        this.sessionssessionListendDate = sessionssessionListendDate;
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