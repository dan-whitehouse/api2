package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata"})
public class Base implements Serializable {

	@JsonProperty("sourcedId")
	private String sourcedId;
	@JsonProperty("status")
	private StatusType status;
	@JsonProperty("dateLastModified")
	private String dateLastModified;
	@JsonProperty("metadata")
	private Metadata metadata;
	private final static long serialVersionUID = 602595453201771641L;

	/**
	 * No args constructor for use in serialization
	 */
	public Base() {
	}

	/**
	 * @param status
	 * @param dateLastModified
	 * @param metadata
	 * @param sourcedId
	 */
	public Base(String sourcedId, StatusType status, String dateLastModified, Metadata metadata) {
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
	public String getDateLastModified() {
		return dateLastModified;
	}

	@JsonProperty("dateLastModified")
	public void setDateLastModified(String dateLastModified) {
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