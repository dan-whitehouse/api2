/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"code", "otherCode"})
public class EntryType {
	@JsonProperty("code")
	@ApiModelProperty(position = 1, value = "Value of the system type code")
	private String code;

	@JsonProperty("otherCode")
	@ApiModelProperty(position = 2, value = "A list of entry enrollment codes")
	private List<OtherCode> otherCode;

	public EntryType() {
		otherCode = new ArrayList<>();
	}

	public EntryType(String code, List<OtherCode> otherCode) {
		super();
		this.code = code;
		this.otherCode = otherCode;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("otherCode")
	public List<OtherCode> getOtherCode() {
		return otherCode;
	}

	@JsonProperty("otherCode")
	public void setOtherCode(List<OtherCode> otherCode) {
		this.otherCode = otherCode;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(code, otherCode).allMatch(Objects::isNull) || (StringUtils.isBlank(code) && otherCode.isEmpty());
	}

	@Override
	public String toString() {
		return "EntryType{" + "code='" + code + '\'' + ", otherCode=" + otherCode + '}';
	}
}