package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"demographics"})
public class DemographicsResponse implements Serializable {

	@JsonProperty("demographics")
	private List<Demographic> demographics = new ArrayList<Demographic>();
	private final static long serialVersionUID = 6089973485073338986L;

	/**
	 * No args constructor for use in serialization
	 */
	public DemographicsResponse() {
	}

	/**
	 * @param demographics
	 */
	public DemographicsResponse(List<Demographic> demographics) {
		super();
		this.demographics = demographics;
	}

	@JsonProperty("demographics")
	public List<Demographic> getDemographics() {
		return demographics;
	}

	@JsonProperty("demographics")
	public void setDemographics(List<Demographic> demographics) {
		this.demographics = demographics;
	}

}