package org.ricone.config.model;

import com.fasterxml.jackson.annotation.*;
import org.ricone.api.core.model.Lea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"enabled", "id", "name", "provider_id"})
public class District {
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("provider_id")
    private String providerId;

    @JsonIgnore
    private Lea lea;

    @JsonIgnore
    private List<School> schools = new ArrayList<>();

    @JsonIgnore
    private HashMap<String, String> kv = new HashMap<>();


    public District() {
    }

    public District(Boolean enabled, String id, String name, String providerId, HashMap<String, String> kv) {
        this.enabled = enabled;
        this.id = id;
        this.name = name;
        this.providerId = providerId;
        this.kv = kv;
    }

    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    @JsonProperty("enabled")
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    @JsonProperty("provider_id")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @JsonIgnore
    public Lea getLea() {
        return lea;
    }

    @JsonIgnore
    public void setLea(Lea lea) {
        this.lea = lea;
    }

    @JsonIgnore
    public List<School> getSchools() {
        return schools;
    }

    @JsonIgnore
    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    @JsonIgnore
    public HashMap<String, String> getKv() {
        return kv;
    }

    @JsonIgnore
    public void setKv(HashMap<String, String> kv) {
        this.kv = kv;
    }

    @Override
    public String toString() {
        return "District{" + "enabled=" + enabled + ", id='" + id + '\'' + ", name='" + name + '\'' + ", providerId='" + providerId + '\'' + ", kv=" + kv + '}';
    }
}