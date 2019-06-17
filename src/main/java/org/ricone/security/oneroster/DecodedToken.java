package org.ricone.security.oneroster;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"app_id", "ims.global.org.security.scope", "provider_id", "href", "iat", "exp", "iss"})
public class DecodedToken implements Serializable {
	private final static long serialVersionUID = -2898266389932101215L;
	@JsonProperty("app_id")
	private String appId;
	@JsonProperty("ims.global.org.security.scope")
	private String imsGlobalOrgSecurityScope;
	@JsonProperty("provider_id")
	private String providerId;
	@JsonProperty("href")
	private String href;
	@JsonProperty("iat")
	private Integer iat;
	@JsonProperty("exp")
	private Integer exp;
	@JsonProperty("iss")
	private String iss;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public DecodedToken() {
		super();
	}

	@JsonProperty("app_id")
	public String getAppId() {
		return appId;
	}
	@JsonProperty("app_id")
	public void setAppId(String appId) {
		this.appId = appId;
	}

	@JsonProperty("ims.global.org.security.scope")
	public String getImsGlobalOrgSecurityScope() {
		return imsGlobalOrgSecurityScope;
	}
	@JsonProperty("ims.global.org.security.scope")
	public void setImsGlobalOrgSecurityScope(String imsGlobalOrgSecurityScope) {
		this.imsGlobalOrgSecurityScope = imsGlobalOrgSecurityScope;
	}

	@JsonProperty("provider_id")
	public String getProviderId() {
		return providerId;
	}
	@JsonProperty("provider_id")
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@JsonProperty("href")
	public String getHref() {
		return href;
	}
	@JsonProperty("href")
	public void setHref(String href) {
		this.href = href;
	}

	@JsonProperty("iat")
	public Integer getIat() {
		return iat;
	}
	@JsonProperty("iat")
	public void setIat(Integer iat) {
		this.iat = iat;
	}

	@JsonProperty("exp")
	public Integer getExp() {
		return exp;
	}
	@JsonProperty("exp")
	public void setExp(Integer exp) {
		this.exp = exp;
	}

	@JsonProperty("iss")
	public String getIss() {
		return iss;
	}
	@JsonProperty("iss")
	public void setIss(String iss) {
		this.iss = iss;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}