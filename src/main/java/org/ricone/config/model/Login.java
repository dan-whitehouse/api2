package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"username", "password"})
public class Login implements Serializable {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    private final static long serialVersionUID = 1937542900806498499L;

    /**
     * No args constructor for use in serialization
     */
    public Login() {
    }

    /**
     * @param username
     * @param password
     */
    public Login(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

}