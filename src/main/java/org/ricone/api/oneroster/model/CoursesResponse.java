package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"courses"})
public class CoursesResponse extends BaseResponse implements Serializable {

	@JsonProperty("courses")
	private List<Course> course = new ArrayList<Course>();
	private final static long serialVersionUID = 8240007651262912068L;

	/**
	 * No args constructor for use in serialization
	 */
	public CoursesResponse() {
	}

	/**
	 * @param course
	 */
	public CoursesResponse(List<Course> course) {
		super();
		this.course = course;
	}

	@JsonProperty("courses")
	public List<Course> getCourse() {
		return course;
	}

	@JsonProperty("courses")
	public void setCourse(List<Course> course) {
		this.course = course;
	}

}