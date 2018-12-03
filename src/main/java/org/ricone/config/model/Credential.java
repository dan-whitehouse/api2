package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "ttl", "created", "userId", "roles"})
public class Credential implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ttl")
    private Integer ttl;
    @JsonProperty("created")
    private String created;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    private final static long serialVersionUID = 8851781547213154272L;

    /**
     * No args constructor for use in serialization
     */
    public Credential() {
    }

    /**
     * @param id
     * @param created
     * @param roles
     * @param userId
     * @param ttl
     */
    public Credential(String id, Integer ttl, String created, Integer userId, List<String> roles) {
        super();
        this.id = id;
        this.ttl = ttl;
        this.created = created;
        this.userId = userId;
        this.roles = roles;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("ttl")
    public Integer getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}