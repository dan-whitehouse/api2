package org.ricone.api.oneroster.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.error.StatusInfoSet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"statusInfoSet"})
public class ErrorResponse implements Serializable {

	@JsonProperty("statusInfoSet")
	private List<StatusInfoSet> statusInfoSet = new ArrayList<StatusInfoSet>();
	private final static long serialVersionUID = -2668305213966385627L;

	/**
	 * No args constructor for use in serialization
	 */
	public ErrorResponse() {
	}

	/**
	 * @param statusInfoSet
	 */
	public ErrorResponse(List<StatusInfoSet> statusInfoSet) {
		super();
		this.statusInfoSet = statusInfoSet;
	}

	@JsonProperty("statusInfoSet")
	public List<StatusInfoSet> getStatusInfoSet() {
		return statusInfoSet;
	}

	@JsonProperty("statusInfoSet")
	public void setStatusInfoSet(List<StatusInfoSet> statusInfoSet) {
		this.statusInfoSet = statusInfoSet;
	}

}