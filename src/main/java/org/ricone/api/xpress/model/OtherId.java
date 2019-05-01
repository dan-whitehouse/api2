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
@JsonPropertyOrder({"type", "id"})
public class OtherId {
	@JsonProperty("type")
	@ApiModelProperty(position = 1, value = "A coding scheme that is used for identification and record-keeping purposes by schools, social services, or other agencies")
	private String type;

	@JsonProperty("id")
	@ApiModelProperty(position = 2, value = "A unique number or alphanumeric code assigned to an organization by a school, school system, a state, or other agency or entity")
	private String id;

	public OtherId() {
	}

	public OtherId(String type, String id) {
		super();
		this.type = type;
		this.id = id;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(type, id).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "OtherId{" + "type='" + type + '\'' + ", id='" + id + '\'' + '}';
	}
}