package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"error"})
@XmlRootElement
public class XErrorResponse implements Serializable {

	@JsonProperty("error")
	@XmlElementWrapper(nillable = true)
	private XError error;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -1284641496243738693L;

	/**
	 * No args constructor for use in serialization
	 */
	public XErrorResponse() {
	}

	/**
	 * @param error
	 */
	public XErrorResponse(XError error) {
		super();
		this.error = error;
	}

	@JsonProperty("error")
	public XError getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(XError error) {
		this.error = error;
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