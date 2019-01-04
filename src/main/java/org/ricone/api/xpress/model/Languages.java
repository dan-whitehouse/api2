/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"language"})
public class Languages {

	@JsonProperty("language")
	private List<Language> language;

	public Languages() {
		language = new ArrayList<>();
	}

	public Languages(List<Language> language) {
		super();
		this.language = language;
	}

	@JsonProperty("language")
	public List<Language> getLanguage() {
		return language;
	}

	@JsonProperty("language")
	public void setLanguage(List<Language> language) {
		this.language = language;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return language.isEmpty();
	}

	@Override
	public String toString() {
		return "Languages{" + "language=" + language + '}';
	}
}