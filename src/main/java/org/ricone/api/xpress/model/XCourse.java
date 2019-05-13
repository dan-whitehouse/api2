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

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "schoolRefId", "schoolCourseId", "leaCourseId", "otherIds", "courseTitle", "description", "subject", "applicableEducationLevels", "scedCourseCode", "scedCourseLevelCode", "scedCourseSubjectAreaCode", "metadata"})
@JsonRootName(value = "xCourse")
public class XCourse extends XWrapper {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	@XmlAttribute(name = "refId")
	@ApiModelProperty(position = 1, value = "The refId of the course")
	private String refId;

	@JsonProperty("schoolRefId")
	@ApiModelProperty(position = 2, value = "RefId of the school in which the course is delivered")
	private String schoolRefId;

	@JsonProperty("schoolCourseId")
	@ApiModelProperty(position = 3, value = "The actual code, defined at the school level, that identifies the organization of subject matter and related learning experiences provided for the instruction of students")
	private String schoolCourseId;

	@JsonProperty("leaCourseId")
	@ApiModelProperty(position = 4, value = "The actual code, defined at the LEA/district level, that identifies the organization of subject matter and related learning experiences provided for the instruction of students as defined at the district level (i.e. district course code)")
	private String leaCourseId;

	@JsonProperty("otherIds")
	@ApiModelProperty(position = 5, value = "A system that is used to identify the organization of subject matter and related learning experiences provided for the instruction of students")
	private OtherIds otherIds;

	@JsonProperty("courseTitle")
	@ApiModelProperty(position = 6, value = "The descriptive name given to a course of study offered in a school or other institution or organization. In departmentalized classes at the elementary, secondary, and postsecondary levels (and for staff development activities), this refers to the name by which a course is identified (e.g., American History, English III). For elementary and other non-departmentalized classes, it refers to any portion of the instruction for which a grade or report is assigned (e.g., reading, composition, spelling, and language arts)")
	private String courseTitle;

	@JsonProperty("description")
	@ApiModelProperty(position = 7, value = "A description of the course content and/or goals. Reference may be made to state or national content standards")
	private String description;

	@JsonProperty("subject")
	@ApiModelProperty(position = 8, value = "The subject of the course")
	private String subject;

	@JsonProperty("applicableEducationLevels")
	@ApiModelProperty(position = 9, value = "The education level, grade level or primary instructional level at which a course is intended")
	private ApplicableEducationLevels applicableEducationLevels;

	@JsonProperty("scedCourseCode")
	@ApiModelProperty(position = 10, value = "SCED code for the course")
	private String scedCourseCode;

	@JsonProperty("scedCourseLevelCode")
	@ApiModelProperty(position = 11, value = "SCED course level code")
	private String scedCourseLevelCode;

	@JsonProperty("scedCourseSubjectAreaCode")
	@ApiModelProperty(position = 12, value = "SCED course subject area code")
	private String scedCourseSubjectAreaCode;

	@JsonProperty("metadata")
	@ApiModelProperty(position = 13, value = "")
	private Metadata metadata;

	public XCourse() {
	}

	public XCourse(String refId, String schoolRefId, String schoolCourseId, String leaCourseId, OtherIds otherIds, String courseTitle, String description, String subject, ApplicableEducationLevels applicableEducationLevels, String scedCourseCode, String scedCourseLevelCode, String scedCourseSubjectAreaCode, Metadata metadata) {
		this.refId = refId;
		this.schoolRefId = schoolRefId;
		this.schoolCourseId = schoolCourseId;
		this.leaCourseId = leaCourseId;
		this.otherIds = otherIds;
		this.courseTitle = courseTitle;
		this.description = description;
		this.subject = subject;
		this.applicableEducationLevels = applicableEducationLevels;
		this.scedCourseCode = scedCourseCode;
		this.scedCourseLevelCode = scedCourseLevelCode;
		this.scedCourseSubjectAreaCode = scedCourseSubjectAreaCode;
		this.metadata = metadata;
	}

	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	@XmlAttribute(name = "refId")
	public String getRefId() {
		return refId;
	}

	@JsonProperty("@refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("schoolCourseId")
	public String getSchoolCourseId() {
		return schoolCourseId;
	}

	@JsonProperty("schoolCourseId")
	public void setSchoolCourseId(String schoolCourseId) {
		this.schoolCourseId = schoolCourseId;
	}

	@JsonProperty("leaCourseId")
	public String getLeaCourseId() {
		return leaCourseId;
	}

	@JsonProperty("leaCourseId")
	public void setLeaCourseId(String leaCourseId) {
		this.leaCourseId = leaCourseId;
	}

	@JsonProperty("otherIds")
	public OtherIds getOtherIds() {
		return otherIds;
	}

	@JsonProperty("otherIds")
	public void setOtherIds(OtherIds otherIds) {
		this.otherIds = otherIds;
	}

	@JsonProperty("courseTitle")
	public String getCourseTitle() {
		return courseTitle;
	}

	@JsonProperty("courseTitle")
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}

	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@JsonProperty("applicableEducationLevels")
	public ApplicableEducationLevels getApplicableEducationLevels() {
		return applicableEducationLevels;
	}

	@JsonProperty("applicableEducationLevels")
	public void setApplicableEducationLevels(ApplicableEducationLevels applicableEducationLevels) {
		this.applicableEducationLevels = applicableEducationLevels;
	}

	@JsonProperty("scedCourseCode")
	public String getScedCourseCode() {
		return scedCourseCode;
	}

	@JsonProperty("scedCourseCode")
	public void setScedCourseCode(String scedCourseCode) {
		this.scedCourseCode = scedCourseCode;
	}

	@JsonProperty("scedCourseLevelCode")
	public String getScedCourseLevelCode() {
		return scedCourseLevelCode;
	}

	@JsonProperty("scedCourseLevelCode")
	public void setScedCourseLevelCode(String scedCourseLevelCode) {
		this.scedCourseLevelCode = scedCourseLevelCode;
	}

	@JsonProperty("scedCourseSubjectAreaCode")
	public String getScedCourseSubjectAreaCode() {
		return scedCourseSubjectAreaCode;
	}

	@JsonProperty("scedCourseSubjectAreaCode")
	public void setScedCourseSubjectAreaCode(String scedCourseSubjectAreaCode) {
		this.scedCourseSubjectAreaCode = scedCourseSubjectAreaCode;
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
		return Stream.of(refId, schoolRefId, schoolCourseId, leaCourseId, otherIds, courseTitle, description, subject, applicableEducationLevels, scedCourseCode, scedCourseLevelCode, scedCourseSubjectAreaCode, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XCourse{" + "refId='" + refId + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", schoolCourseId='" + schoolCourseId + '\'' + ", leaCourseId='" + leaCourseId + '\'' + ", otherIds=" + otherIds + ", courseTitle='" + courseTitle + '\'' + ", description='" + description + '\'' + ", subject='" + subject + '\'' + ", applicableEducationLevels=" + applicableEducationLevels + ", scedCourseCode='" + scedCourseCode + '\'' + ", scedCourseLevelCode='" + scedCourseLevelCode + '\'' + ", scedCourseSubjectAreaCode='" + scedCourseSubjectAreaCode + '\'' + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XCourse)) return false;

		XCourse xCourse = (XCourse) o;

		if(refId != null ? !refId.equals(xCourse.refId) : xCourse.refId != null) return false;
		if(schoolRefId != null ? !schoolRefId.equals(xCourse.schoolRefId) : xCourse.schoolRefId != null) return false;
		if(schoolCourseId != null ? !schoolCourseId.equals(xCourse.schoolCourseId) : xCourse.schoolCourseId != null)
			return false;
		if(leaCourseId != null ? !leaCourseId.equals(xCourse.leaCourseId) : xCourse.leaCourseId != null) return false;
		if(otherIds != null ? !otherIds.equals(xCourse.otherIds) : xCourse.otherIds != null) return false;
		if(courseTitle != null ? !courseTitle.equals(xCourse.courseTitle) : xCourse.courseTitle != null) return false;
		if(description != null ? !description.equals(xCourse.description) : xCourse.description != null) return false;
		if(subject != null ? !subject.equals(xCourse.subject) : xCourse.subject != null) return false;
		if(applicableEducationLevels != null ? !applicableEducationLevels.equals(xCourse.applicableEducationLevels) : xCourse.applicableEducationLevels != null)
			return false;
		if(scedCourseCode != null ? !scedCourseCode.equals(xCourse.scedCourseCode) : xCourse.scedCourseCode != null)
			return false;
		if(scedCourseLevelCode != null ? !scedCourseLevelCode.equals(xCourse.scedCourseLevelCode) : xCourse.scedCourseLevelCode != null)
			return false;
		if(scedCourseSubjectAreaCode != null ? !scedCourseSubjectAreaCode.equals(xCourse.scedCourseSubjectAreaCode) : xCourse.scedCourseSubjectAreaCode != null)
			return false;
		return metadata != null ? metadata.equals(xCourse.metadata) : xCourse.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (schoolRefId != null ? schoolRefId.hashCode() : 0);
		result = 31 * result + (schoolCourseId != null ? schoolCourseId.hashCode() : 0);
		result = 31 * result + (leaCourseId != null ? leaCourseId.hashCode() : 0);
		result = 31 * result + (otherIds != null ? otherIds.hashCode() : 0);
		result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (subject != null ? subject.hashCode() : 0);
		result = 31 * result + (applicableEducationLevels != null ? applicableEducationLevels.hashCode() : 0);
		result = 31 * result + (scedCourseCode != null ? scedCourseCode.hashCode() : 0);
		result = 31 * result + (scedCourseLevelCode != null ? scedCourseLevelCode.hashCode() : 0);
		result = 31 * result + (scedCourseSubjectAreaCode != null ? scedCourseSubjectAreaCode.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}