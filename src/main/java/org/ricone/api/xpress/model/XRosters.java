/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xRoster"})
@JsonRootName(value = "xRosters")
public class XRosters {
	@JsonProperty("xRoster")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XRoster> xRoster;

	public XRosters() {
		xRoster = new ArrayList<>();
	}

	public XRosters(List<XRoster> xRoster) {
		super();
		this.xRoster = xRoster;
	}

	@JsonProperty("xRoster")
	public List<XRoster> getXRoster() {
		return xRoster;
	}

	@JsonProperty("xRoster")
	public void setXRoster(List<XRoster> xRoster) {
		this.xRoster = xRoster;
	}

	@Override
	public String toString() {
		return "XRosters{" + "xRoster=" + xRoster + '}';
	}
}