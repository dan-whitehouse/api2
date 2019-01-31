package org.ricone.api.oneroster.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ricone.api.oneroster.model.StatusInfoSet;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseSingleResponse<T> implements Serializable {
	private T data;
	private List<StatusInfoSet> errors;

	protected BaseSingleResponse() {
	}

	public BaseSingleResponse(T data) {
		this.data = data;
	}

	public BaseSingleResponse(T data, List<StatusInfoSet> errors) {
		this.data = data;
		this.errors = errors;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonProperty("statusInfoSet")
	public List<StatusInfoSet> getStatusInfoSets() {
		return errors;
	}

	@JsonProperty("statusInfoSet")
	public void setStatusInfoSets(List<StatusInfoSet> errors) {
		this.errors = errors;
	}
}
