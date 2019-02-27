package org.ricone.api.oneroster.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.model.Error;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings"})
public abstract class BaseResponse implements Serializable {
	private List<Error> warnings;

	protected BaseResponse() {
	}

	public BaseResponse(List<Error> warnings) {
		this.warnings = warnings;
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
