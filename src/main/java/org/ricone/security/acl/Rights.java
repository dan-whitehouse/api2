package org.ricone.security.acl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class Rights {
	@JacksonXmlElementWrapper(localName = "right", useWrapping = false)
	private List<Right> right;

	public List<Right> getRight() {
		return right;
	}

	public void setRight(List<Right> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "ClassPojo [right = " + right + "]";
	}
}