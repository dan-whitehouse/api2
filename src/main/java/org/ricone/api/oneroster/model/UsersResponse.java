package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"users"})
public class UsersResponse extends BaseMultiResponse<User> implements Serializable {
	private final static long serialVersionUID = -2820152702413553355L;

	public UsersResponse() {
	}

	public UsersResponse(List<User> users) {
		super(users);
	}

	public UsersResponse(List<User> users, List<StatusInfoSet> errors) {
		super(users, errors);
	}

	@JsonProperty("users")
	@Override public List<User> getData() {
		return super.getData();
	}

	@JsonProperty("users")
	@Override public void setData(List<User> users) {
		super.setData(users);
	}
}