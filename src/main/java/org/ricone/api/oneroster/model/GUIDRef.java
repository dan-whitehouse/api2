package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"href", "sourcedId", "type"})
public class GUIDRef implements Serializable {

	@JsonProperty("href")
	private String href;
	@JsonProperty("sourcedId")
	private String sourcedId;
	@JsonProperty("type")
	private GUIDType type;
	private final static long serialVersionUID = 4584680713330325553L;

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