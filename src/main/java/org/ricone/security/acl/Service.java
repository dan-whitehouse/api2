package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Service {
	@JacksonXmlProperty(isAttribute = true, localName = "name")
	private String name;
	@JacksonXmlProperty(isAttribute = true, localName = "contextId")
	private String contextId;
	@JacksonXmlProperty(isAttribute = true, localName = "type")
	private String type;
	@JacksonXmlProperty(localName = "rights")
	private Rights rights;

	public Rights getRights() {
		return rights;
	}

	public void setRights(Rights rights) {
		this.rights = rights;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContextId() {
		return contextId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	@Override
	public String toString() {
		return "ClassPojo [rights = " + rights + ", name = " + name + ", contextId = " + contextId + "]";
	}
}