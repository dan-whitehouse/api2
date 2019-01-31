package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"course"})
public class CourseResponse extends BaseSingleResponse<Course> implements Serializable {
	private final static long serialVersionUID = -6447433608404298239L;

	public CourseResponse(Course course) {
		super(course);
	}

	public CourseResponse(Course course, List<StatusInfoSet> errors) {
		super(course, errors);
	}

	@JsonProperty("course")
	@Override public Course getData() {
		return super.getData();
	}

	@JsonProperty("course")
	@Override public void setData(Course course) {
		super.setData(course);
	}

}