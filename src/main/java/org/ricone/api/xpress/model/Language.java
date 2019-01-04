/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "code"})
public class Language {

	@JsonProperty("type")
	private String type;
	@JsonProperty("code")
	private String code;

	public Language() {
	}

	public Language(String type, String code) {
		super();
		this.type = type;
		this.code = code;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(type, code).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Language{" + "type='" + type + '\'' + ", code='" + code + '\'' + '}';
	}
}