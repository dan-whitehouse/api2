package org.ricone.config.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xml"})
public class DataXML implements Serializable {

	@JsonProperty("xml")
	private Xml xml;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 1996468042958163135L;

	/**
	 * No args constructor for use in serialization
	 */
	public DataXML() {
	}

	/**
	 * @param xml
	 */
	public DataXML(Xml xml) {
		super();
		this.xml = xml;
	}

	@JsonProperty("xml")
	public Xml getXml() {
		return xml;
	}

	@JsonProperty("xml")
	public void setXml(Xml xml) {
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