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
@JsonPropertyOrder({"@refId", "courseRefId", "courseTitle", "sectionRefId", "subject", "schoolRefId", "schoolSectionId", "schoolYear", "meetingTimes", "students", "primaryStaff", "otherStaffs", "metadata"})
@JsonRootName(value = "xRoster")
public class XRoster {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("courseRefId")
	private String courseRefId;
	@JsonProperty("courseTitle")
	private String courseTitle;
	@JsonProperty("sectionRefId")
	private String sectionRefId;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("schoolRefId")
	private String schoolRefId;
	@JsonProperty("schoolSectionId")
	private String schoolSectionId;
	@JsonProperty("schoolYear")
	private String schoolYear;
	@JsonProperty("meetingTimes")
	private MeetingTimes meetingTimes;
	@JsonProperty("students")
	private Students students;
	@JsonProperty("primaryStaff")
	private PrimaryStaff primaryStaff;
	@JsonProperty("otherStaffs")
	private OtherStaffs otherStaffs;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XRoster() {
	}

	public XRoster(String refId, String courseRefId, String courseTitle, String sectionRefId, String subject, String schoolRefId, String schoolSectionId, String schoolYear, MeetingTimes meetingTimes, Students students, PrimaryStaff primaryStaff, OtherStaffs otherStaffs, Metadata metadata) {
		this.refId = refId;
		this.courseRefId = courseRefId;
		this.courseTitle = courseTitle;
		this.sectionRefId = sectionRefId;
		this.subject = subject;
		this.schoolRefId = schoolRefId;
		this.schoolSectionId = schoolSectionId;
		this.schoolYear = schoolYear;
		this.meetingTimes = meetingTimes;
		this.students = students;
		this.primaryStaff = primaryStaff;
		this.otherStaffs = otherStaffs;
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

	@JsonProperty("courseRefId")
	public String getCourseRefId() {
		return courseRefId;
	}

	@JsonProperty("courseRefId")
	public void setCourseRefId(String courseRefId) {
		this.courseRefId = courseRefId;
	}

	@JsonProperty("courseTitle")
	public String getCourseTitle() {
		return courseTitle;
	}

	@JsonProperty("courseTitle")
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	@JsonProperty("sectionRefId")
	public String getSectionRefId() {
		return sectionRefId;
	}

	@JsonProperty("sectionRefId")
	public void setSectionRefId(String sectionRefId) {
		this.sectionRefId = sectionRefId;
	}

	@JsonProperty("subject")
	public String getSubject() {
		return subject;
	}

	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("schoolSectionId")
	public String getSchoolSectionId() {
		return schoolSectionId;
	}

	@JsonProperty("schoolSectionId")
	public void setSchoolSectionId(String schoolSectionId) {
		this.schoolSectionId = schoolSectionId;
	}

	@JsonProperty("schoolYear")
	public String getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("meetingTimes")
	public MeetingTimes getMeetingTimes() {
		return meetingTimes;
	}

	@JsonProperty("meetingTimes")
	public void setMeetingTimes(MeetingTimes meetingTimes) {
		this.meetingTimes = meetingTimes;
	}

	@JsonProperty("students")
	public Students getStudents() {
		return students;
	}

	@JsonProperty("students")
	public void setStudents(Students students) {
		this.students = students;
	}

	@JsonProperty("primaryStaff")
	public PrimaryStaff getPrimaryStaff() {
		return primaryStaff;
	}

	@JsonProperty("primaryStaff")
	public void setPrimaryStaff(PrimaryStaff primaryStaff) {
		this.primaryStaff = primaryStaff;
	}

	@JsonProperty("otherStaffs")
	public OtherStaffs getOtherStaffs() {
		return otherStaffs;
	}

	@JsonProperty("otherStaffs")
	public void setOtherStaffs(OtherStaffs otherStaffs) {
		this.otherStaffs = otherStaffs;
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
		return Stream.of(refId, courseRefId, courseTitle, sectionRefId, subject, schoolRefId, schoolSectionId, schoolYear, meetingTimes, students, primaryStaff, otherStaffs, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XRoster{" + "refId='" + refId + '\'' + ", courseRefId='" + courseRefId + '\'' + ", courseTitle='" + courseTitle + '\'' + ", sectionRefId='" + sectionRefId + '\'' + ", subject='" + subject + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", schoolSectionId='" + schoolSectionId + '\'' + ", schoolYear='" + schoolYear + '\'' + ", meetingTimes=" + meetingTimes + ", students=" + students + ", primaryStaff=" + primaryStaff + ", otherStaffs=" + otherStaffs + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XRoster)) return false;

		XRoster xRoster = (XRoster) o;

		if(refId != null ? !refId.equals(xRoster.refId) : xRoster.refId != null) return false;
		if(courseRefId != null ? !courseRefId.equals(xRoster.courseRefId) : xRoster.courseRefId != null) return false;
		if(courseTitle != null ? !courseTitle.equals(xRoster.courseTitle) : xRoster.courseTitle != null) return false;
		if(sectionRefId != null ? !sectionRefId.equals(xRoster.sectionRefId) : xRoster.sectionRefId != null)
			return false;
		if(subject != null ? !subject.equals(xRoster.subject) : xRoster.subject != null) return false;
		if(schoolRefId != null ? !schoolRefId.equals(xRoster.schoolRefId) : xRoster.schoolRefId != null) return false;
		if(schoolSectionId != null ? !schoolSectionId.equals(xRoster.schoolSectionId) : xRoster.schoolSectionId != null)
			return false;
		if(schoolYear != null ? !schoolYear.equals(xRoster.schoolYear) : xRoster.schoolYear != null) return false;
		if(meetingTimes != null ? !meetingTimes.equals(xRoster.meetingTimes) : xRoster.meetingTimes != null)
			return false;
		if(students != null ? !students.equals(xRoster.students) : xRoster.students != null) return false;
		if(primaryStaff != null ? !primaryStaff.equals(xRoster.primaryStaff) : xRoster.primaryStaff != null)
			return false;
		if(otherStaffs != null ? !otherStaffs.equals(xRoster.otherStaffs) : xRoster.otherStaffs != null) return false;
		return metadata != null ? metadata.equals(xRoster.metadata) : xRoster.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (courseRefId != null ? courseRefId.hashCode() : 0);
		result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
		result = 31 * result + (sectionRefId != null ? sectionRefId.hashCode() : 0);
		result = 31 * result + (subject != null ? subject.hashCode() : 0);
		result = 31 * result + (schoolRefId != null ? schoolRefId.hashCode() : 0);
		result = 31 * result + (schoolSectionId != null ? schoolSectionId.hashCode() : 0);
		result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
		result = 31 * result + (meetingTimes != null ? meetingTimes.hashCode() : 0);
		result = 31 * result + (students != null ? students.hashCode() : 0);
		result = 31 * result + (primaryStaff != null ? primaryStaff.hashCode() : 0);
		result = 31 * result + (otherStaffs != null ? otherStaffs.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}