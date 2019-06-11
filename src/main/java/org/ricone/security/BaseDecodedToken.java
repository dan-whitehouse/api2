package org.ricone.security;

public abstract class BaseDecodedToken {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
