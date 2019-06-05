package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ProvisionedZone {
	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private String id;

	private Services services;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", services = " + services + "]";
	}
}
