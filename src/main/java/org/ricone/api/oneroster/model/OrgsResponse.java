package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","orgs"})
public class OrgsResponse extends BaseMultiResponse<Org> implements Serializable {
	private final static long serialVersionUID = -5609856877368582544L;

	public OrgsResponse() {
	}

	public OrgsResponse(List<Org> orgs) {
		super(orgs);
	}

	public OrgsResponse(List<Org> list, List<Error> errors) {
		super(list, errors);
	}

	@JsonProperty("orgs")
	@Override public List<Org> getData() {
		return super.getData();
	}

	@JsonProperty("orgs")
	@Override public void setData(List<Org> orgs) {
		super.setData(orgs);
	}
}