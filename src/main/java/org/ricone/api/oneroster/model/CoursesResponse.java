package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","courses"})
public class CoursesResponse extends BaseMultiResponse<Course> implements Serializable {
	private final static long serialVersionUID = 8240007651262912068L;

	public CoursesResponse() {
	}

	public CoursesResponse(List<Course> course) {
		super(course);
	}

	public CoursesResponse(List<Course> course, List<Error> errors) {
		super(course);
	}

	@JsonProperty("courses")
	@Override public List<Course> getData() {
		return super.getData();
	}

	@JsonProperty("courses")
	@Override public void setData(List<Course> course) {
		super.setData(course);
	}

}