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
@JsonPropertyOrder({"xStudent"})
@JsonRootName(value = "xStudents")
public class XStudents {
	@JsonProperty("xStudent")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XStudent> xStudent;

	public XStudents() {
		xStudent = new ArrayList<>();
	}

	public XStudents(List<XStudent> xStudent) {
		super();
		this.xStudent = xStudent;
	}

	@JsonProperty("xStudent")
	public List<XStudent> getXStudent() {
		return xStudent;
	}

	@JsonProperty("xStudent")
	public void setXStudent(List<XStudent> xStudent) {
		this.xStudent = xStudent;
	}

	@Override
	public String toString() {
		return "XStudents{" + "xStudent=" + xStudent + '}';
	}
}