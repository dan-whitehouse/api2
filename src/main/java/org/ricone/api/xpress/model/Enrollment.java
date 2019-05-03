/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"leaRefId", "schoolRefId", "studentSchoolAssociationRefId", "responsibleSchoolType", "membershipType", "entryDate", "entryType", "exitDate", "exitType", "homeRoomNumber", "homeRoomTeacher", "gradeLevel", "projectedGraduationYear", "counselor"})
public class Enrollment {
	@JsonProperty("leaRefId")
	@ApiModelProperty(position = 1, value = "The refId of the LEA")
	private String leaRefId;

	@JsonProperty("schoolRefId")
	@ApiModelProperty(position = 2, value = "The refId of the school")
	private String schoolRefId;

	@JsonProperty("studentSchoolAssociationRefId")
	@ApiModelProperty(position = 3, value = "The refId of the school")
	private String studentSchoolAssociationRefId;

	@JsonProperty("responsibleSchoolType")
	@ApiModelProperty(position = 4, value = "The type of services/instruction the school is responsible for providing to the student")
	private String responsibleSchoolType;

	@JsonProperty("membershipType")
	@ApiModelProperty(position = 5, value = "The student's type of membership in the system")
	private String membershipType;

	@JsonProperty("entryDate")
	@ApiModelProperty(position = 6, value = "The month, day, and year on which a person enters and begins to receive instructional services in a school, institution, program, or class-section during a given session")
	private String entryDate;

	@JsonProperty("entryType")
	@ApiModelProperty(position = 7, value = "Enrollment entry type codes")
	private EntryType entryType;

	@JsonProperty("exitDate")
	@ApiModelProperty(position = 8, value = "The year, month and day on which the student officially withdrew or graduated, i.e. the date on which the student's enrollment ended")
	private String exitDate;

	@JsonProperty("exitType")
	@ApiModelProperty(position = 9, value = "Enrollment exit type codes")
	private ExitType exitType;

	@JsonProperty("homeRoomNumber")
	@ApiModelProperty(position = 10, value = "Home Room number")
	private String homeRoomNumber;

	@JsonProperty("homeRoomTeacher")
	@ApiModelProperty(position = 11, value = "Students home room teacher")
	private HomeRoomTeacher homeRoomTeacher;

	@JsonProperty("gradeLevel")
	@ApiModelProperty(position = 12, value = "The current grade or academic level of the student within a school")
	private String gradeLevel;

	@JsonProperty("projectedGraduationYear")
	@ApiModelProperty(position = 13, value = "The year and month the student is projected to graduate")
	private String projectedGraduationYear;

	@JsonProperty("counselor")
	@ApiModelProperty(position = 14, value = "RefId of the counselor")
	private Counselor counselor;

	public Enrollment() {
	}

	public Enrollment(String leaRefId, String schoolRefId, String studentSchoolAssociationRefId, String responsibleSchoolType, String membershipType, String entryDate, EntryType entryType, String exitDate, ExitType exitType, String homeRoomNumber, HomeRoomTeacher homeRoomTeacher, String gradeLevel, String projectedGraduationYear, Counselor counselor) {
		super();
		this.leaRefId = leaRefId;
		this.schoolRefId = schoolRefId;
		this.studentSchoolAssociationRefId = studentSchoolAssociationRefId;
		this.responsibleSchoolType = responsibleSchoolType;
		this.membershipType = membershipType;
		this.entryDate = entryDate;
		this.entryType = entryType;
		this.exitDate = exitDate;
		this.exitType = exitType;
		this.homeRoomNumber = homeRoomNumber;
		this.homeRoomTeacher = homeRoomTeacher;
		this.gradeLevel = gradeLevel;
		this.projectedGraduationYear = projectedGraduationYear;
		this.counselor = counselor;
	}

	@JsonProperty("leaRefId")
	public String getLeaRefId() {
		return leaRefId;
	}

	@JsonProperty("leaRefId")
	public void setLeaRefId(String leaRefId) {
		this.leaRefId = leaRefId;
	}

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("studentSchoolAssociationRefId")
	public String getStudentSchoolAssociationRefId() {
		return studentSchoolAssociationRefId;
	}

	@JsonProperty("studentSchoolAssociationRefId")
	public void setStudentSchoolAssociationRefId(String studentSchoolAssociationRefId) {
		this.studentSchoolAssociationRefId = studentSchoolAssociationRefId;
	}

	@JsonProperty("responsibleSchoolType")
	public String getResponsibleSchoolType() {
		return responsibleSchoolType;
	}

	@JsonProperty("responsibleSchoolType")
	public void setResponsibleSchoolType(String responsibleSchoolType) {
		this.responsibleSchoolType = responsibleSchoolType;
	}

	@JsonProperty("membershipType")
	public String getMembershipType() {
		return membershipType;
	}

	@JsonProperty("membershipType")
	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	@JsonProperty("entryDate")
	public String getEntryDate() {
		return entryDate;
	}

	@JsonProperty("entryDate")
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@JsonProperty("entryType")
	public EntryType getEntryType() {
		return entryType;
	}

	@JsonProperty("entryType")
	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}

	@JsonProperty("exitDate")
	public String getExitDate() {
		return exitDate;
	}

	@JsonProperty("exitDate")
	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	@JsonProperty("exitType")
	public ExitType getExitType() {
		return exitType;
	}

	@JsonProperty("exitType")
	public void setExitType(ExitType exitType) {
		this.exitType = exitType;
	}

	@JsonProperty("homeRoomNumber")
	public String getHomeRoomNumber() {
		return homeRoomNumber;
	}

	@JsonProperty("homeRoomNumber")
	public void setHomeRoomNumber(String homeRoomNumber) {
		this.homeRoomNumber = homeRoomNumber;
	}

	@JsonProperty("homeRoomTeacher")
	public HomeRoomTeacher getHomeRoomTeacher() {
		return homeRoomTeacher;
	}

	@JsonProperty("homeRoomTeacher")
	public void setHomeRoomTeacher(HomeRoomTeacher homeRoomTeacher) {
		this.homeRoomTeacher = homeRoomTeacher;
	}

	@JsonProperty("gradeLevel")
	public String getGradeLevel() {
		return gradeLevel;
	}

	@JsonProperty("gradeLevel")
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	@JsonProperty("projectedGraduationYear")
	public String getProjectedGraduationYear() {
		return projectedGraduationYear;
	}

	@JsonProperty("projectedGraduationYear")
	public void setProjectedGraduationYear(String projectedGraduationYear) {
		this.projectedGraduationYear = projectedGraduationYear;
	}

	@JsonProperty("counselor")
	public Counselor getCounselor() {
		return counselor;
	}

	@JsonProperty("counselor")
	public void setCounselor(Counselor counselor) {
		this.counselor = counselor;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(leaRefId, schoolRefId, studentSchoolAssociationRefId, responsibleSchoolType, membershipType, entryDate, entryType, exitDate, exitType, homeRoomNumber, homeRoomTeacher, gradeLevel, projectedGraduationYear, counselor).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Enrollment{" + "leaRefId='" + leaRefId + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", studentSchoolAssociationRefId='" + studentSchoolAssociationRefId + '\'' + ", responsibleSchoolType='" + responsibleSchoolType + '\'' + ", membershipType='" + membershipType + '\'' + ", entryDate='" + entryDate + '\'' + ", entryType=" + entryType + ", exitDate='" + exitDate + '\'' + ", exitType=" + exitType + ", homeRoomNumber='" + homeRoomNumber + '\'' + ", homeRoomTeacher=" + homeRoomTeacher + ", gradeLevel='" + gradeLevel + '\'' + ", projectedGraduationYear='" + projectedGraduationYear + '\'' + ", counselor=" + counselor + '}';
	}
}