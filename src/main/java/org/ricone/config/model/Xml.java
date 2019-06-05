package org.ricone.config.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"vendor_id", "vendor_name", "app_id", "app_name", "app_version", "xml"})
public class Xml implements Serializable {

	@JsonProperty("vendor_id")
	private String vendorId;
	@JsonProperty("vendor_name")
	private String vendorName;
	@JsonProperty("app_id")
	private String appId;
	@JsonProperty("app_name")
	private String appName;
	@JsonProperty("app_version")
	private String appVersion;
	@JsonProperty("xml")
	private String xml;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -2111072837783876310L;

	/**
	 * No args constructor for use in serialization
	 */
	public Xml() {
	}

	/**
	 * @param appName
	 * @param appId
	 * @param vendorName
	 * @param appVersion
	 * @param xml
	 * @param vendorId
	 */
	public Xml(String vendorId, String vendorName, String appId, String appName, String appVersion, String xml) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.appId = appId;
		this.appName = appName;
		this.appVersion = appVersion;
		this.xml = xml;
	}

	@JsonProperty("vendor_id")
	public String getVendorId() {
		return vendorId;
	}

	@JsonProperty("vendor_id")
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	@JsonProperty("vendor_name")
	public String getVendorName() {
		return vendorName;
	}

	@JsonProperty("vendor_name")
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@JsonProperty("app_id")
	public String getAppId() {
		return appId;
	}

	@JsonProperty("app_id")
	public void setAppId(String appId) {
		this.appId = appId;
	}

	@JsonProperty("app_name")
	public String getAppName() {
		return appName;
	}

	@JsonProperty("app_name")
	public void setAppName(String appName) {
		this.appName = appName;
	}

	@JsonProperty("app_version")
	public String getAppVersion() {
		return appVersion;
	}

	@JsonProperty("app_version")
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	@JsonProperty("xml")
	public String getXml() {
		return xml;
	}

	@JsonProperty("xml")
	public void setXml(String xml) {
		this.xml = xml;
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