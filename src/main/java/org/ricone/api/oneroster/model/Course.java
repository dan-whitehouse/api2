package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "schoolYear", "courseCode", "grades", "subjects", "org", "subjectCodes", "resources"})
public class Course extends Base implements Serializable {

	@JsonProperty("title")
	private String title;
	@JsonProperty("schoolYear")
	private GUIDRef schoolYear;
	@JsonProperty("courseCode")
	private String courseCode;
	@JsonProperty("grades")
	private List<String> grades = new ArrayList<String>();
	@JsonProperty("subjects")
	private List<String> subjects = new ArrayList<String>();
	@JsonProperty("org")
	private GUIDRef org;
	@JsonProperty("subjectCodes")
	private List<String> subjectCodes = new ArrayList<String>();
	@JsonProperty("resources")
	private List<GUIDRef> resources = new ArrayList<GUIDRef>();
	private final static long serialVersionUID = 6014301965221202137L;

	/**
	 * No args constructor for use in serialization
	 */
	public Course() {
	}

	/**
	 * @param schoolYear
	 * @param resources
	 * @param title
	 * @param subjectCodes
	 * @param subjects
	 * @param org
	 * @param grades
	 * @param courseCode
	 */
	public Course(String title, GUIDRef schoolYear, String courseCode, List<String> grades, List<String> subjects, GUIDRef org, List<String> subjectCodes, List<GUIDRef> resources) {
		super();
		this.title = title;
		this.schoolYear = schoolYear;
		this.courseCode = courseCode;
		this.grades = grades;
		this.subjects = subjects;
		this.org = org;
		this.subjectCodes = subjectCodes;
		this.resources = resources;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("schoolYear")
	public GUIDRef getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(GUIDRef schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("courseCode")
	public String getCourseCode() {
		return courseCode;
	}

	@JsonProperty("courseCode")
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@JsonProperty("grades")
	public List<String> getGrades() {
		return grades;
	}

	@JsonProperty("grades")
	public void setGrades(List<String> grades) {
		this.grades = grades;
	}

	@JsonProperty("subjects")
	public List<String> getSubjects() {
		return subjects;
	}

	@JsonProperty("subjects")
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@JsonProperty("org")
	public GUIDRef getOrg() {
		return org;
	}

	@JsonProperty("org")
	public void setOrg(GUIDRef org) {
		this.org = org;
	}

	@JsonProperty("subjectCodes")
	public List<String> getSubjectCodes() {
		return subjectCodes;
	}

	@JsonProperty("subjectCodes")
	public void setSubjectCodes(List<String> subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	@JsonProperty("resources")
	public List<GUIDRef> getResources() {
		return resources;
	}

	@JsonProperty("resources")
	public void setResources(List<GUIDRef> resources) {
		this.resources = resources;
	}

}