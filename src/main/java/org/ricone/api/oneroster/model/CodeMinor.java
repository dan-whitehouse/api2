package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeMinor {
	forbidden("forbidden"),
	full_success("full success"),
	unknown_object("unknown object"),
	invalid_data("invalid data"),
	unauthorized("unauthorized"),
	invalid_sort_field("invalid_sort_field"),
	invalid_blank_selection_field("invalid_ blank_selection _field"),
	invalid_filter_field("invalid_filter_field"),
	invalid_selection_field("invalid_selection_field");

	private final String label;
	CodeMinor(String label) {
		this.label = label;
	}
	@JsonValue
	public String getLabel() {
		return label;
	}
}
