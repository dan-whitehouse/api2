package org.ricone.api.xpress.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "refId", "name", "students", "staff", "schools" })
public class District {

	@JsonProperty("refId")
	private String refId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("students")
	private Students students;
	@JsonProperty("staff")
	private Staff staff;
	@JsonProperty("schools")
	private Schools schools;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public District() {
	}

	/**
	 * 
	 * @param staff
	 * @param students
	 * @param schools
	 * @param name
	 * @param refId
	 */
	public District(String refId, String name, Students students, Staff staff, Schools schools) {
		super();
		this.refId = refId;
		this.name = name;
		this.students = students;
		this.staff = staff;
		this.schools = schools;
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

	@JsonProperty("students")
	public Students getStudents() {
		return students;
	}

	@JsonProperty("students")
	public void setStudents(Students students) {
		this.students = students;
	}

	@JsonProperty("staff")
	public Staff getStaff() {
		return staff;
	}

	@JsonProperty("staff")
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@JsonProperty("schools")
	public Schools getSchools() {
		return schools;
	}

	@JsonProperty("schools")
	public void setSchools(Schools schools) {
		this.schools = schools;
	}

}