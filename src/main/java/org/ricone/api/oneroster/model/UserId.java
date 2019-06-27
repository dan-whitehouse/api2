package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "identifier"})
public class UserId implements Serializable {
	private final static long serialVersionUID = -4197544761658759008L;
	@JsonProperty("type")
	@ApiModelProperty(position = 1, value = "", example = "")
	private String type;
	@JsonProperty("identifier")
	@ApiModelProperty(position = 5, value = "", example = "")
	private String identifier;

	/**
	 * No args constructor for use in serialization
	 */
	public UserId() {
	}

	/**
	 * @param type
	 * @param identifier
	 */
	public UserId(String type, String identifier) {
		super();
		this.type = type;
		this.identifier = identifier;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}

	@JsonProperty("identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}