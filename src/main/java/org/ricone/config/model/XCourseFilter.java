package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "schoolRefId", "schoolCourseId", "leaCourseId", "otherIdsotherIdtype", "otherIdsotherIdid", "courseTitle", "description", "subject", "scedCourseCode", "scedCourseLevelCode", "scedCourseSubjectAreaCode", "applicableEducationLevelsapplicableEducationLevel", "id"})
public class XCourseFilter implements IFilter<XCourseFilter>, Serializable {

    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("schoolRefId")
    private Boolean schoolRefId;
    @JsonProperty("schoolCourseId")
    private Boolean schoolCourseId;
    @JsonProperty("leaCourseId")
    private Boolean leaCourseId;
    @JsonProperty("otherIdsotherIdtype")
    private Boolean otherIdsotherIdtype;
    @JsonProperty("otherIdsotherIdid")
    private Boolean otherIdsotherIdid;
    @JsonProperty("courseTitle")
    private Boolean courseTitle;
    @JsonProperty("description")
    private Boolean description;
    @JsonProperty("subject")
    private Boolean subject;
    @JsonProperty("scedCourseCode")
    private Boolean scedCourseCode;
    @JsonProperty("scedCourseLevelCode")
    private Boolean scedCourseLevelCode;
    @JsonProperty("scedCourseSubjectAreaCode")
    private Boolean scedCourseSubjectAreaCode;
    @JsonProperty("applicableEducationLevelsapplicableEducationLevel")
    private Boolean applicableEducationLevelsapplicableEducationLevel;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = 4346368945156705384L;

    /**
     * No args constructor for use in serialization
     */
    public XCourseFilter() {
        refId = true;
        schoolRefId = true;
        schoolCourseId = true;
        leaCourseId = true;
        otherIdsotherIdtype = true;
        otherIdsotherIdid = true;
        courseTitle = true;
        description = true;
        subject = true;
        scedCourseCode = true;
        scedCourseLevelCode = true;
        scedCourseSubjectAreaCode = true;
        applicableEducationLevelsapplicableEducationLevel = true;
    }

    /**
     * @param otherIdsotherIdtype
     * @param schoolCourseId
     * @param scedCourseSubjectAreaCode
     * @param subject
     * @param refId
     * @param applicableEducationLevelsapplicableEducationLevel
     * @param schoolRefId
     * @param id
     * @param leaCourseId
     * @param scedCourseCode
     * @param courseTitle
     * @param description
     * @param scedCourseLevelCode
     * @param otherIdsotherIdid
     */
    public XCourseFilter(Boolean refId, Boolean schoolRefId, Boolean schoolCourseId, Boolean leaCourseId, Boolean otherIdsotherIdtype, Boolean otherIdsotherIdid, Boolean courseTitle, Boolean description, Boolean subject, Boolean scedCourseCode, Boolean scedCourseLevelCode, Boolean scedCourseSubjectAreaCode, Boolean applicableEducationLevelsapplicableEducationLevel, Integer id) {
        super();
        this.refId = refId;
        this.schoolRefId = schoolRefId;
        this.schoolCourseId = schoolCourseId;
        this.leaCourseId = leaCourseId;
        this.otherIdsotherIdtype = otherIdsotherIdtype;
        this.otherIdsotherIdid = otherIdsotherIdid;
        this.courseTitle = courseTitle;
        this.description = description;
        this.subject = subject;
        this.scedCourseCode = scedCourseCode;
        this.scedCourseLevelCode = scedCourseLevelCode;
        this.scedCourseSubjectAreaCode = scedCourseSubjectAreaCode;
        this.applicableEducationLevelsapplicableEducationLevel = applicableEducationLevelsapplicableEducationLevel;
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

    @JsonProperty("schoolCourseId")
    public Boolean getSchoolCourseId() {
        return schoolCourseId;
    }

    @JsonProperty("schoolCourseId")
    public void setSchoolCourseId(Boolean schoolCourseId) {
        this.schoolCourseId = schoolCourseId;
    }

    @JsonProperty("leaCourseId")
    public Boolean getLeaCourseId() {
        return leaCourseId;
    }

    @JsonProperty("leaCourseId")
    public void setLeaCourseId(Boolean leaCourseId) {
        this.leaCourseId = leaCourseId;
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

    @JsonProperty("courseTitle")
    public Boolean getCourseTitle() {
        return courseTitle;
    }

    @JsonProperty("courseTitle")
    public void setCourseTitle(Boolean courseTitle) {
        this.courseTitle = courseTitle;
    }

    @JsonProperty("description")
    public Boolean getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Boolean description) {
        this.description = description;
    }

    @JsonProperty("subject")
    public Boolean getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(Boolean subject) {
        this.subject = subject;
    }

    @JsonProperty("scedCourseCode")
    public Boolean getScedCourseCode() {
        return scedCourseCode;
    }

    @JsonProperty("scedCourseCode")
    public void setScedCourseCode(Boolean scedCourseCode) {
        this.scedCourseCode = scedCourseCode;
    }

    @JsonProperty("scedCourseLevelCode")
    public Boolean getScedCourseLevelCode() {
        return scedCourseLevelCode;
    }

    @JsonProperty("scedCourseLevelCode")
    public void setScedCourseLevelCode(Boolean scedCourseLevelCode) {
        this.scedCourseLevelCode = scedCourseLevelCode;
    }

    @JsonProperty("scedCourseSubjectAreaCode")
    public Boolean getScedCourseSubjectAreaCode() {
        return scedCourseSubjectAreaCode;
    }

    @JsonProperty("scedCourseSubjectAreaCode")
    public void setScedCourseSubjectAreaCode(Boolean scedCourseSubjectAreaCode) {
        this.scedCourseSubjectAreaCode = scedCourseSubjectAreaCode;
    }

    @JsonProperty("applicableEducationLevelsapplicableEducationLevel")
    public Boolean getApplicableEducationLevelsapplicableEducationLevel() {
        return applicableEducationLevelsapplicableEducationLevel;
    }

    @JsonProperty("applicableEducationLevelsapplicableEducationLevel")
    public void setApplicableEducationLevelsapplicableEducationLevel(Boolean applicableEducationLevelsapplicableEducationLevel) {
        this.applicableEducationLevelsapplicableEducationLevel = applicableEducationLevelsapplicableEducationLevel;
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
