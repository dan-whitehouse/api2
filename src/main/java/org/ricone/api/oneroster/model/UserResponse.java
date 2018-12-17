package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"user"})
public class UserResponse implements Serializable {

	@JsonProperty("user")
	private User user;
	private final static long serialVersionUID = -8931879378806569113L;

	/**
	 * No args constructor for use in serialization
	 */
	public UserResponse() {
	}

	/**
	 * @param user
	 */
	public UserResponse(User user) {
		super();
		this.user = user;
	}

	@JsonProperty("user")
	public User getUser() {
		return user;
	}

	@JsonProperty("user")
	public void setUser(User user) {
		this.user = user;
	}

}