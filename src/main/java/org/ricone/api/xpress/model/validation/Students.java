package org.ricone.api.xpress.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "email", "localId" })
public class Students {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("email")
	private Email email;
	@JsonProperty("localId")
	private LocalId localId;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Students() {
	}

	/**
	 * 
	 * @param total
	 * @param email
	 * @param localId
	 */
	public Students(Integer total, Email email, LocalId localId) {
		super();
		this.total = total;
		this.email = email;
		this.localId = localId;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("email")
	public Email getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(Email email) {
		this.email = email;
	}

	@JsonProperty("localId")
	public LocalId getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(LocalId localId) {
		this.localId = localId;
	}

}