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
@JsonPropertyOrder({"xSchool"})
@JsonRootName(value = "xSchools")
public class XSchools {
	@JsonProperty("xSchool")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XSchool> xSchool;

	public XSchools() {
		xSchool = new ArrayList<>();
	}

	public XSchools(List<XSchool> xSchool) {
		super();
		this.xSchool = xSchool;
	}

	@JsonProperty("xSchool")
	public List<XSchool> getXSchool() {
		return xSchool;
	}

	@JsonProperty("xSchool")
	public void setXSchool(List<XSchool> xSchool) {
		this.xSchool = xSchool;
	}

	@Override
	public String toString() {
		return "XSchools{" + "xSchool=" + xSchool + '}';
	}
}