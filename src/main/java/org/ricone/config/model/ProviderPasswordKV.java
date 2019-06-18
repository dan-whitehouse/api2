package org.ricone.config.model;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 0.9.9
 * @since 2016-06-07
 */

public class ProviderPasswordKV {
	private String id;
	private String provider_id;
	private String name;
	private String username;
	private String password;

	public ProviderPasswordKV() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}