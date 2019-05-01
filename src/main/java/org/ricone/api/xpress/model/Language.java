/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "code"})
public class Language {
	@JsonProperty("type")
	@ApiModelProperty(position = 1, value = "An indication of the function and context in which a person uses a language to communicate. The possible values are: Correspondence, Dominant, Home, Native, OtherLanguageProficiency, Other")
	private String type;

	@JsonProperty("code")
	@ApiModelProperty(position = 2, value = "The code for the specific language or dialect that a person uses to communicate. Language Code option set comes from the ISO 639-2 standard")
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