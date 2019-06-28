package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata"})
public abstract class Base implements Serializable {
	private final static long serialVersionUID = 602595453201771641L;
	@JsonProperty("sourcedId")
	@ApiModelProperty(position = 1, value = "", example = "02e23746-2b23-40d4-ba98-24afe6cf187e")
	private String sourcedId;
	@JsonProperty("status")
	@ApiModelProperty(position = 2, value = "The set of permitted tokens for the type of status", example = "active")
	private StatusType status;
	@JsonProperty("dateLastModified")
	@ApiModelProperty(position = 3, value = "", example = "2019-06-27T18:25:43.511Z")
	private ZonedDateTime dateLastModified;
	@JsonProperty("metadata")
	@ApiModelProperty(position = 4, value = "")
	private Metadata metadata;

	/**
	 * No args constructor for use in serialization
	 */
	public Base() {
	}

	/**
	 * @param status - active | tobedeleted
	 * @param dateLastModified - All objects must be annotated with the dateTime upon which they were last modified. DateTimes MUST be expressed in W3C profile of ISO 8601 and MUST contain the UTC timezone;
	 * @param metadata - Objects can be extended using the Metadata class. This specification is silent on what implementers may consider to be appropriate extensions.
	 * @param sourcedId - This is a GUID System ID for an object. This is the GUID that systems will refer to when making API calls, or when needing to identify an object
	 */
	public Base(String sourcedId, StatusType status, ZonedDateTime dateLastModified, Metadata metadata) {
		super();
		this.sourcedId = sourcedId;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.metadata = metadata;
	}

	@JsonProperty("sourcedId")
	@ApiModelProperty(position = -4)
	public String getSourcedId() {
		return sourcedId;
	}

	@JsonProperty("sourcedId")
	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	@JsonProperty("status")
	@ApiModelProperty(position = -3)
	public StatusType getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(StatusType status) {
		this.status = status;
	}

	@JsonProperty("dateLastModified")
	@ApiModelProperty(position = -2)
	public ZonedDateTime getDateLastModified() {
		return dateLastModified;
	}

	@JsonProperty("dateLastModified")
	public void setDateLastModified(ZonedDateTime dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	@JsonProperty("metadata")
	@ApiModelProperty(position = -1)
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}