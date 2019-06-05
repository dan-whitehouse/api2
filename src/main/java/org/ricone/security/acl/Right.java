package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlValue;

public class Right {
	@JacksonXmlProperty(isAttribute = true, localName = "type")
	private String type;
	@XmlValue
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ClassPojo [type = " + type + ", value = " + value + "]";
	}
}