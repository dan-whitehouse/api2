package org.ricone.api.oneroster.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.model.StatusInfoSet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"statusInfoSet"})
public class BaseResponse implements Serializable {
	@JsonProperty("statusInfoSet")
	private List<StatusInfoSet> statusInfoSets = new ArrayList<>();
	private final static long serialVersionUID = -7209856313466787294L;

	public BaseResponse() {
	}

	public BaseResponse(List<StatusInfoSet> statusInfoSets) {
		this.statusInfoSets = statusInfoSets;
	}

	@JsonProperty("statusInfoSet")
	public List<StatusInfoSet> getStatusInfoSets() {
		return statusInfoSets;
	}

	@JsonProperty("statusInfoSet")
	public void setStatusInfoSets(List<StatusInfoSet> statusInfoSets) {
		this.statusInfoSets = statusInfoSets;
	}
}
