package org.ricone.api.xpress.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"refId",
		"name",
		"studentEnrollments",
		"staffAssignments",
		"courses",
		"courseSections"
})
public class School implements Serializable
{

	@JsonProperty("refId")
	private String refId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("studentEnrollments")
	private StudentEnrollments studentEnrollments;
	@JsonProperty("staffAssignments")
	private StaffAssignments staffAssignments;
	@JsonProperty("courses")
	private Integer courses;
	@JsonProperty("courseSections")
	private Integer courseSections;
	private final static long serialVersionUID = -6048482986685952628L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public School() {
	}

	/**
	 *
	 * @param courses
	 * @param studentEnrollments
	 * @param name
	 * @param refId
	 * @param courseSections
	 * @param staffAssignments
	 */
	public School(String refId, String name, StudentEnrollments studentEnrollments, StaffAssignments staffAssignments, Integer courses, Integer courseSections) {
		super();
		this.refId = refId;
		this.name = name;
		this.studentEnrollments = studentEnrollments;
		this.staffAssignments = staffAssignments;
		this.courses = courses;
		this.courseSections = courseSections;
	}

	@JsonProperty("refId")
	public String getRefId() {
		return refId;
	}

	@JsonProperty("refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("studentEnrollments")
	public StudentEnrollments getStudentEnrollments() {
		return studentEnrollments;
	}

	@JsonProperty("studentEnrollments")
	public void setStudentEnrollments(StudentEnrollments studentEnrollments) {
		this.studentEnrollments = studentEnrollments;
	}

	@JsonProperty("staffAssignments")
	public StaffAssignments getStaffAssignments() {
		return staffAssignments;
	}

	@JsonProperty("staffAssignments")
	public void setStaffAssignments(StaffAssignments staffAssignments) {
		this.staffAssignments = staffAssignments;
	}

	@JsonProperty("courses")
	public Integer getCourses() {
		return courses;
	}

	@JsonProperty("courses")
	public void setCourses(Integer courses) {
		this.courses = courses;
	}

	@JsonProperty("courseSections")
	public Integer getCourseSections() {
		return courseSections;
	}

	@JsonProperty("courseSections")
	public void setCourseSections(Integer courseSections) {
		this.courseSections = courseSections;
	}

}