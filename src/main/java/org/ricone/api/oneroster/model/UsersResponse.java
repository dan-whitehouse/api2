package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"users"})
public class UsersResponse extends BaseResponse implements Serializable {

	@JsonProperty("users")
	private List<User> users = new ArrayList<User>();
	private final static long serialVersionUID = -2820152702413553355L;

	/**
	 * No args constructor for use in serialization
	 */
	public UsersResponse() {
	}

	/**
	 * @param users
	 */
	public UsersResponse(List<User> users) {
		super();
		this.users = users;
	}

	@JsonProperty("users")
	public List<User> getUsers() {
		return users;
	}

	@JsonProperty("users")
	public void setUsers(List<User> users) {
		this.users = users;
	}

}