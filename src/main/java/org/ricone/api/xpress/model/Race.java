/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"race"})
public class Race {

	@JsonProperty("race")
	private String race;

	public Race() {
	}

	public Race(String race) {
		super();
		this.race = race;
	}

	@JsonProperty("race")
	public String getRace() {
		return race;
	}

	@JsonProperty("race")
	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Race{" + "race='" + race + '\'' + '}';
	}
}