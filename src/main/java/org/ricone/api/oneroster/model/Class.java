package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "classCode", "classType", "location", "grades", "subjects", "course", "school", "terms", "subjectCodes", "periods", "resources"})
public class Class extends Base implements Serializable {
	private final static long serialVersionUID = -6826277327573344241L;
	@JsonProperty("title")
	private String title;
	@JsonProperty("classCode")
	private String classCode;
	@JsonProperty("classType")
	private ClassType classType;
	@JsonProperty("location")
	private String location;
	@JsonProperty("grades")
	private List<String> grades = new ArrayList<>();
	@JsonProperty("subjects")
	private List<String> subjects = new ArrayList<>();
	@JsonProperty("course")
	private GUIDRef course;
	@JsonProperty("school")
	private GUIDRef school;
	@JsonProperty("terms")
	private List<GUIDRef> terms = new ArrayList<>();
	@JsonProperty("subjectCodes")
	private List<String> subjectCodes = new ArrayList<>();
	@JsonProperty("periods")
	private List<String> periods = new ArrayList<>();
	@JsonProperty("resources")
	private List<GUIDRef> resources = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 */
	public Class() {
	}

	/**
	 * @param course
	 * @param resources
	 * @param periods
	 * @param title
	 * @param school
	 * @param subjectCodes
	 * @param subjects
	 * @param location
	 * @param terms
	 * @param classCode
	 * @param classType
	 * @param grades
	 */
	public Class(String title, String classCode, ClassType classType, String location, List<String> grades, List<String> subjects, GUIDRef course, GUIDRef school, List<GUIDRef> terms, List<String> subjectCodes, List<String> periods, List<GUIDRef> resources) {
		super();
		this.title = title;
		this.classCode = classCode;
		this.classType = classType;
		this.location = location;
		this.grades = grades;
		this.subjects = subjects;
		this.course = course;
		this.school = school;
		this.terms = terms;
		this.subjectCodes = subjectCodes;
		this.periods = periods;
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

	@JsonProperty("classCode")
	public String getClassCode() {
		return classCode;
	}

	@JsonProperty("classCode")
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@JsonProperty("classType")
	public ClassType getClassType() {
		return classType;
	}

	@JsonProperty("classType")
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
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

	@JsonProperty("course")
	public GUIDRef getCourse() {
		return course;
	}

	@JsonProperty("course")
	public void setCourse(GUIDRef course) {
		this.course = course;
	}

	@JsonProperty("school")
	public GUIDRef getSchool() {
		return school;
	}

	@JsonProperty("school")
	public void setSchool(GUIDRef school) {
		this.school = school;
	}

	@JsonProperty("terms")
	public List<GUIDRef> getTerms() {
		return terms;
	}

	@JsonProperty("terms")
	public void setTerms(List<GUIDRef> terms) {
		this.terms = terms;
	}

	@JsonProperty("subjectCodes")
	public List<String> getSubjectCodes() {
		return subjectCodes;
	}

	@JsonProperty("subjectCodes")
	public void setSubjectCodes(List<String> subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	@JsonProperty("periods")
	public List<String> getPeriods() {
		return periods;
	}

	@JsonProperty("periods")
	public void setPeriods(List<String> periods) {
		this.periods = periods;
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