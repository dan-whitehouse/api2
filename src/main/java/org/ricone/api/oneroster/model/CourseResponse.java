package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"course"})
public class CourseResponse implements Serializable {

	@JsonProperty("course")
	private Course course;
	private final static long serialVersionUID = -6447433608404298239L;

	/**
	 * No args constructor for use in serialization
	 */
	public CourseResponse() {
	}

	/**
	 * @param course
	 */
	public CourseResponse(Course course) {
		super();
		this.course = course;
	}

	@JsonProperty("course")
	public Course getCourse() {
		return course;
	}

	@JsonProperty("course")
	public void setCourse(Course course) {
		this.course = course;
	}

}