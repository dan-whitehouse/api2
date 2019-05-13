package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@id", "code", "message", "description"})
@JsonRootName("error")
@JacksonXmlRootElement(localName = "error")
@XmlRootElement(name = "error")
public class XError implements Serializable {
	@JsonProperty("@id")
	@JacksonXmlProperty(localName = "id", isAttribute = true)
	@XmlAttribute(name = "id")
	private String id;
	@JsonProperty("code")
	private int code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("description")
	private String description;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 7849059655284828943L;

	/**
	 * No args constructor for use in serialization
	 */
	public XError() {
	}

	/**
	 * @param message
	 * @param id
	 * @param description
	 * @param code
	 */
	public XError(String id, int code, String message, String description) {
		super();
		this.id = id;
		this.code = code;
		this.message = message;
		this.description = description;
	}

	@JsonProperty("@id")
	@JacksonXmlProperty(localName = "id", isAttribute = true)
	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	@JsonProperty("@id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("code")
	public int getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(int code) {
		this.code = code;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
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