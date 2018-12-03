/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xCourse"})
@JsonRootName(value = "xCourses")
public class XCourses {
	@JsonProperty("xCourse")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XCourse> xCourse;

	public XCourses() {
		xCourse = new ArrayList<>();
	}

	public XCourses(List<XCourse> xCourse) {
		super();
		this.xCourse = xCourse;
	}

	@JsonProperty("xCourse")
	public List<XCourse> getXCourse() {
		return xCourse;
	}

	@JsonProperty("xCourse")
	public void setXCourse(List<XCourse> xCourse) {
		this.xCourse = xCourse;
	}

	@Override
	public String toString() {
		return "XCourses{" + "xCourse=" + xCourse + '}';
	}
}