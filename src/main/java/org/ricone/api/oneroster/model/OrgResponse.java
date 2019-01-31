package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"org"})
public class OrgResponse extends BaseSingleResponse<Org> implements Serializable {
	private final static long serialVersionUID = 4198000642945588466L;

	public OrgResponse() {
	}

	public OrgResponse(Org org) {
		super(org);
	}

	public OrgResponse(Org org, List<StatusInfoSet> errors) {
		super(org, errors);
	}

	@JsonProperty("org")
	@Override public Org getData() {
		return super.getData();
	}

	@JsonProperty("org")
	@Override public void setData(Org org) {
		super.setData(org);
	}
}