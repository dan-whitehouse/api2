package org.ricone.api.oneroster.model;

public enum GUIDType {
	academicSession("academicSession"),
	category("categories"),
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
	private GUIDType(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
}
