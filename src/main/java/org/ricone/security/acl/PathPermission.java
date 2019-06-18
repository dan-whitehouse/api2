package org.ricone.security.acl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"app_id", "path", "get", "post", "put", "delete", "head"})
public class PathPermission implements Serializable {
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("path")
    private String path;
    @JsonProperty("get")
    private Boolean get;
    @JsonProperty("post")
    private Boolean post;
    @JsonProperty("put")
    private Boolean put;
    @JsonProperty("delete")
    private Boolean delete;
    private final static long serialVersionUID = 8122198094551476636L;

    PathPermission(String appId, String path, Boolean get, Boolean post, Boolean put, Boolean delete) {
        this.appId = appId;
        this.path = path;
        this.get = get;
        this.post = post;
        this.put = put;
        this.delete = delete;
    }

    @JsonProperty("app_id")
    public String getAppId() {
        return appId;
    }
    @JsonProperty("app_id")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }
    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("get")
    public Boolean getGet() {
        return get;
    }
    @JsonProperty("get")
    public void setGet(Boolean get) {
        this.get = get;
    }

    @JsonProperty("post")
    public Boolean getPost() {
        return post;
    }
    @JsonProperty("post")
    public void setPost(Boolean post) {
        this.post = post;
    }

    @JsonProperty("put")
    public Boolean getPut() {
        return put;
    }
    @JsonProperty("put")
    public void setPut(Boolean put) {
        this.put = put;
    }

    @JsonProperty("delete")
    public Boolean getDelete() {
        return delete;
    }
    @JsonProperty("delete")
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "PathPermission{" + "appId='" + appId + '\'' + ", path='" + path + '\'' + ", get=" + get + ", post=" + post + ", put=" + put + ", delete=" + delete + '}';
    }
}