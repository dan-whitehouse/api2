package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata"})
public abstract class Base implements Serializable {
	private final static long serialVersionUID = 602595453201771641L;
	@JsonProperty("sourcedId")
	private String sourcedId;
	@JsonProperty("status")
	private StatusType status;
	@JsonProperty("dateLastModified")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="America/New_York")
	private ZonedDateTime dateLastModified;
	@JsonProperty("metadata")
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
	public String getSourcedId() {
		return sourcedId;
	}

	@JsonProperty("sourcedId")
	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	@JsonProperty("status")
	public StatusType getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(StatusType status) {
		this.status = status;
	}

	@JsonProperty("dateLastModified")
	public ZonedDateTime getDateLastModified() {
		return dateLastModified;
	}

	@JsonProperty("dateLastModified")
	public void setDateLastModified(ZonedDateTime dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}