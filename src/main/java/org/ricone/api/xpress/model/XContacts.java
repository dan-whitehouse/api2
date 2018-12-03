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
@JsonPropertyOrder({"xContact"})
@JsonRootName(value = "xContacts")
public class XContacts {
	@JsonProperty("xContact")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XContact> xContact;

	public XContacts() {
		xContact = new ArrayList<>();
	}

	public XContacts(List<XContact> xContact) {
		super();
		this.xContact = xContact;
	}

	@JsonProperty("xContact")
	public List<XContact> getXContact() {
		return xContact;
	}

	@JsonProperty("xContact")
	public void setXContact(List<XContact> xContact) {
		this.xContact = xContact;
	}

	@Override
	public String toString() {
		return "XContacts{" + "xContact=" + xContact + '}';
	}
}