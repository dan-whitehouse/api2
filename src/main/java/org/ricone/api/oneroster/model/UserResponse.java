package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","user"})
public class UserResponse extends BaseSingleResponse<User> implements Serializable {
	private final static long serialVersionUID = -8931879378806569113L;

	public UserResponse() {
	}

	public UserResponse(User user) {
		super(user);
	}

	public UserResponse(User user, List<Error> errors) {
		super(user, errors);
	}

	@JsonProperty("user")
	@Override public User getData() {
		return super.getData();
	}

	@JsonProperty("user")
	@Override public void setData(User user) {
		super.setData(user);
	}
}