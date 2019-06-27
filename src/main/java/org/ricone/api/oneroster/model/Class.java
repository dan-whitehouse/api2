package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "classCode", "classType", "location", "grades", "subjects", "course", "school", "terms", "subjectCodes", "periods", "resources"})
public class Class extends Base implements Serializable {
	private final static long serialVersionUID = -6826277327573344241L;
	@JsonProperty("title")
	@ApiModelProperty(position = 5, value = "", example = "Basic Chemistry")
	private String title;
	@JsonProperty("classCode")
	@ApiModelProperty(position = 6, value = "", example = "Chem101-Mr Rogers")
	private String classCode;
	@JsonProperty("classType")
	@ApiModelProperty(position = 7, value = "The set of permitted tokens for the type of class")
	private ClassType classType;
	@JsonProperty("location")
	@ApiModelProperty(position = 8, value = "", example = "room 19")
	private String location;
	@JsonProperty("grades")
	@ApiModelProperty(position = 9, value = "Grade(s) for which the class is attended", example = "09 or an array of 09,10", notes = "https://ceds.ed.gov/CEDSElementDetails.aspx?TermId=7100")
	private List<String> grades = new ArrayList<>();
	@JsonProperty("subjects")
	@ApiModelProperty(position = 10, value = "Subject name(s)", example = "Chemistry")
	private List<String> subjects = new ArrayList<>();
	@JsonProperty("course")
	@ApiModelProperty(position = 11, value = "Link to course i.e. the Course 'sourcedId'")
	private GUIDRef course;
	@JsonProperty("school")
	@ApiModelProperty(position = 12, value = "Link to school i.e. the School 'sourcedId'")
	private GUIDRef school;
	@JsonProperty("terms")
	@ApiModelProperty(position = 13, value = "Links to terms or semesters (academicSession) i.e. the set of 'sourcedIds' for the terms within the associated school year", example = "")
	private List<GUIDRef> terms = new ArrayList<>();
	@JsonProperty("subjectCodes")
	@ApiModelProperty(position = 14, value = "This is a machine readable set of codes and the number should match the associated 'subjects' attribute.", example = "")
	private List<String> subjectCodes = new ArrayList<>();
	@JsonProperty("periods")
	@ApiModelProperty(position = 15, value = "The time slots in the day that the class will be given", example = "1, 3, 5")
	private List<String> periods = new ArrayList<>();
	@JsonProperty("resources")
	@ApiModelProperty(position = 16, value = "Link to resources i.e. the Resource 'sourcedId'")
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