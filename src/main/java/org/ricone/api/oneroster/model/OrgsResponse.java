package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"orgs"})
public class OrgsResponse extends BaseResponse implements Serializable {
	@JsonProperty("orgs")
	private List<Org> orgs = new ArrayList<>();
	private final static long serialVersionUID = -5609856877368582544L;

	/**
	 * No args constructor for use in serialization
	 */
	public OrgsResponse() {
	}

	/**
	 * @param orgs
	 */
	public OrgsResponse(List<Org> orgs) {
		super();
		this.orgs = orgs;
	}

	@JsonProperty("orgs")
	public List<Org> getOrgs() {
		return orgs;
	}

	@JsonProperty("orgs")
	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}
}