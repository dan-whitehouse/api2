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
@JsonPropertyOrder({"xStaff"})
@JsonRootName(value = "xStaffs")
public class XStaffs {
	@JsonProperty("xStaff")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XStaff> xStaff;

	public XStaffs() {
		xStaff = new ArrayList<>();
	}

	public XStaffs(List<XStaff> xStaff) {
		super();
		this.xStaff = xStaff;
	}

	@JsonProperty("xStaff")
	public List<XStaff> getXStaff() {
		return xStaff;
	}

	@JsonProperty("xStaff")
	public void setXStaff(List<XStaff> xStaff) {
		this.xStaff = xStaff;
	}

	@Override
	public String toString() {
		return "XStaffs{" + "xStaff=" + xStaff + '}';
	}
}