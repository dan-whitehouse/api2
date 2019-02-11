package org.ricone.api.oneroster.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.model.Error;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings"})
public abstract class BaseSingleResponse<T> implements Serializable {
	private T data;
	private List<Error> warnings;

	protected BaseSingleResponse() {
	}

	public BaseSingleResponse(T data) {
		this.data = data;
	}

	public BaseSingleResponse(T data, List<Error> warnings) {
		this.data = data;
		this.warnings = warnings;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonProperty("warnings")
	public List<Error> getWarnings() {
		return warnings;
	}

	@JsonProperty("warnings")
	public void setWarnings(List<Error> errors) {
		this.warnings = errors;
	}
}
