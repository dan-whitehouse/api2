package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"demographics"})
public class DemographicResponse extends BaseResponse implements Serializable {

	@JsonProperty("demographics")
	private Demographic demographics;
	private final static long serialVersionUID = 6174150439900047310L;

	/**
	 * No args constructor for use in serialization
	 */
	public DemographicResponse() {
	}

	/**
	 * @param demographics
	 */
	public DemographicResponse(Demographic demographics) {
		super();
		this.demographics = demographics;
	}

	@JsonProperty("demographics")
	public Demographic getDemographics() {
		return demographics;
	}

	@JsonProperty("demographics")
	public void setDemographics(Demographic demographics) {
		this.demographics = demographics;
	}

}