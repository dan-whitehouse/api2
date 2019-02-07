package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GUIDType {
	academicSession("academicSession"),
	category("category"),
	clazz("class"),
	course("course"),
	demographics("demographics"),
	enrollment("enrollment"),
	gradingPeriod("gradingPeriod"),
	lineItem("lineItem"),
	org("org"),
	resource("resource"),
	result("result"),
	student("student"),
	teacher("teacher"),
	term("term"),
	user("user");

	private final String label;
	GUIDType(String label) {
		this.label = label;
	}
	@JsonValue public String getLabel() {
		return label;
	}
}
