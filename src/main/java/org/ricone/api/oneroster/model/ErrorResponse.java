package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"errors"})
public class ErrorResponse implements Serializable {
	private final static long serialVersionUID = -2668305213966385627L;

	@JsonProperty("errors")
	private List<Error> errors = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 */
	public ErrorResponse() {
	}

	/**
	 * @param errors
	 */
	public ErrorResponse(List<Error> errors) {
		super();
		this.errors = errors;
	}

	@JsonProperty("errors")
	public List<Error> getErrors() {
		return errors;
	}

	@JsonProperty("errors")
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}