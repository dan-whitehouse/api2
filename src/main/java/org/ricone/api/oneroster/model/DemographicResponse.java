package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"demographics"})
public class DemographicResponse extends BaseSingleResponse<Demographic> implements Serializable {
	private final static long serialVersionUID = 6174150439900047310L;

	public DemographicResponse(Demographic demographic) {
		super(demographic);
	}

	public DemographicResponse(Demographic demographic, List<StatusInfoSet> errors) {
		super(demographic, errors);
	}

	@JsonProperty("demographics")
	@Override public Demographic getData() {
		return super.getData();
	}

	@JsonProperty("demographics")
	@Override public void setData(Demographic demographic) {
		super.setData(demographic);
	}
}