package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"href", "sourcedId", "type"})
@ApiModel(description = "This is a reference to a globally unique identifier")
public class GUIDRef implements Serializable {
	private final static long serialVersionUID = 4584680713330325553L;
	@JsonProperty("href")
	@ApiModelProperty(position = 1, value = "The URI for the type of object being referenced", example = "")
	private String href;
	@JsonProperty("sourcedId")
	@ApiModelProperty(position = 2, value = "The globally unique identifier of the object being referenced", example = "02e23746-2b23-40d4-ba98-24afe6cf187e")
	private String sourcedId;
	@JsonProperty("type")
	@ApiModelProperty(position = 3, value = "The type of object being referenced. This is enumerated", example = "student")
	private GUIDType type;

	/**
	 * No args constructor for use in serialization
	 */
	public GUIDRef() {
	}

	/**
	 * @param type
	 * @param href
	 * @param sourcedId
	 */
	public GUIDRef(String href, String sourcedId, GUIDType type) {
		super();
		this.href = href;
		this.sourcedId = sourcedId;
		this.type = type;
	}

	@JsonProperty("href")
	public String getHref() {
		return href;
	}

	@JsonProperty("href")
	public void setHref(String href) {
		this.href = href;
	}

	@JsonProperty("sourcedId")
	public String getSourcedId() {
		return sourcedId;
	}

	@JsonProperty("sourcedId")
	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	@JsonProperty("type")
	public GUIDType getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(GUIDType type) {
		this.type = type;
	}
}