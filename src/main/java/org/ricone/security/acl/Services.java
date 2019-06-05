package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class Services {
	@JacksonXmlElementWrapper(localName = "service", useWrapping = false)
	private List<Service> service;

	public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "ClassPojo [service = " + service + "]";
	}
}