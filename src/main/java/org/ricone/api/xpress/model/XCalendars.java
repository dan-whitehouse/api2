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
@JsonPropertyOrder({"xCalendar"})
@JsonRootName(value = "xCalendars")
public class XCalendars {
	@JsonProperty("xCalendar")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XCalendar> xCalendar;

	public XCalendars() {
		xCalendar = new ArrayList<>();
	}

	public XCalendars(List<XCalendar> xCalendar) {
		super();
		this.xCalendar = xCalendar;
	}

	@JsonProperty("xCalendar")
	public List<XCalendar> getXCalendar() {
		return xCalendar;
	}

	@JsonProperty("xCalendar")
	public void setXCalendar(List<XCalendar> xCalendar) {
		this.xCalendar = xCalendar;
	}

	@Override
	public String toString() {
		return "XCalendars{" + "xCalendar=" + xCalendar + '}';
	}
}