package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.security.acl.PathPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"longDescription", "type", "name", "permTemplate", "status", "profile_id", "tags", "licenseName", "includeExitedStudents", "providerSecret", "siteUrl", "vendor_id", "iconUrl", "sis_id", "public", "severityLevelDataAPI", "shortDescription", "title", "id", "password"})
public class App implements Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static long serialVersionUID = 391070065402879606L;
    @JsonProperty("longDescription")
    private String longDescription;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("permTemplate")
    private String permTemplate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("profile_id")
    private String profileId;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("licenseName")
    private String licenseName;
    @JsonProperty("includeExitedStudents")
    private String includeExitedStudents;
    @JsonProperty("providerSecret")
    private String providerSecret;
    @JsonProperty("siteUrl")
    private String siteUrl;
    @JsonProperty("vendor_id")
    private String vendorId;
    @JsonProperty("iconUrl")
    private String iconUrl;
    @JsonProperty("sis_id")
    private String sisId;
    @JsonProperty("public")
    private Boolean _public;
    @JsonProperty("severityLevelDataAPI")
    private String severityLevelDataAPI;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private String id;
    @JsonProperty("password")
    private String password;

    @JsonIgnore
    private List<District> districts = new ArrayList<>();
    @JsonIgnore
    private List<Lea> leas = new ArrayList<>();
    @JsonIgnore
    private List<PathPermission> permissions = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public App() {
    }

    /**
     * @param tags
     * @param _public
     * @param status
     * @param severityLevelDataAPI
     * @param iconUrl
     * @param sisId
     * @param includeExitedStudents
     * @param licenseName
     * @param permTemplate
     * @param type
     * @param vendorId
     * @param password
     * @param id
     * @param siteUrl
     * @param title
     * @param shortDescription
     * @param profileId
     * @param name
     * @param longDescription
     * @param providerSecret
     */
    public App(String longDescription, String type, String name, String permTemplate, String status, String profileId, String tags, String licenseName, String includeExitedStudents, String providerSecret, String siteUrl, String vendorId, String iconUrl, String sisId, Boolean _public, String severityLevelDataAPI, String shortDescription, String title, String id, String password) {
        super();
        this.longDescription = longDescription;
        this.type = type;
        this.name = name;
        this.permTemplate = permTemplate;
        this.status = status;
        this.profileId = profileId;
        this.tags = tags;
        this.licenseName = licenseName;
        this.includeExitedStudents = includeExitedStudents;
        this.providerSecret = providerSecret;
        this.siteUrl = siteUrl;
        this.vendorId = vendorId;
        this.iconUrl = iconUrl;
        this.sisId = sisId;
        this._public = _public;
        this.severityLevelDataAPI = severityLevelDataAPI;
        this.shortDescription = shortDescription;
        this.title = title;
        this.id = id;
        this.password = password;
    }

    @JsonProperty("longDescription")
    public String getLongDescription() {
        return longDescription;
    }

    @JsonProperty("longDescription")
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("permTemplate")
    public String getPermTemplate() {
        return permTemplate;
    }

    @JsonProperty("permTemplate")
    public void setPermTemplate(String permTemplate) {
        this.permTemplate = permTemplate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("profile_id")
    public String getProfileId() {
        return profileId;
    }

    @JsonProperty("profile_id")
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @JsonProperty("tags")
    public String getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(String tags) {
        this.tags = tags;
    }

    @JsonProperty("licenseName")
    public String getLicenseName() {
        return licenseName;
    }

    @JsonProperty("licenseName")
    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    @JsonProperty("includeExitedStudents")
    public String getIncludeExitedStudents() {
        return includeExitedStudents;
    }

    @JsonProperty("includeExitedStudents")
    public void setIncludeExitedStudents(String includeExitedStudents) {
        this.includeExitedStudents = includeExitedStudents;
    }

    @JsonIgnore
    public boolean includeExitedStudents() {
        return BooleanUtils.toBoolean(includeExitedStudents);
    }

    @JsonProperty("providerSecret")
    public String getProviderSecret() {
        return providerSecret;
    }

    @JsonProperty("providerSecret")
    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }

    @JsonProperty("siteUrl")
    public String getSiteUrl() {
        return siteUrl;
    }

    @JsonProperty("siteUrl")
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @JsonProperty("vendor_id")
    public String getVendorId() {
        return vendorId;
    }

    @JsonProperty("vendor_id")
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @JsonProperty("iconUrl")
    public String getIconUrl() {
        return iconUrl;
    }

    @JsonProperty("iconUrl")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @JsonProperty("sis_id")
    public String getSisId() {
        return sisId;
    }

    @JsonProperty("sis_id")
    public void setSisId(String sisId) {
        this.sisId = sisId;
    }

    @JsonProperty("public")
    public Boolean getPublic() {
        return _public;
    }

    @JsonProperty("public")
    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    @JsonProperty("severityLevelDataAPI")
    public String getSeverityLevelDataAPI() {
        return severityLevelDataAPI;
    }

    @JsonProperty("severityLevelDataAPI")
    public void setSeverityLevelDataAPI(String severityLevelDataAPI) {
        this.severityLevelDataAPI = severityLevelDataAPI;
    }

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    /* Custom Methods */
    public boolean hasProviderSecret() {
        return StringUtils.isNotBlank(this.providerSecret);
    }
}

