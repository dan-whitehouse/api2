package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"org"})
public class OrgResponse extends BaseResponse implements Serializable {

	@JsonProperty("org")
	private Org org;
	private final static long serialVersionUID = 4198000642945588466L;

	/**
	 * No args constructor for use in serialization
	 */
	public OrgResponse() {
	}

	/**
	 * @param org
	 */
	public OrgResponse(Org org) {
		super();
		this.org = org;
	}

	@JsonProperty("org")
	public Org getOrg() {
		return org;
	}

	@JsonProperty("org")
	public void setOrg(Org org) {
		this.org = org;
	}
}