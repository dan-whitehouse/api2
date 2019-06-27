package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description="This specification is silent on what implementers may consider to be appropriate extensions. Different objects may or may not include key/value pairs.\nExample:\n\"metadata\": {\n" + "\"ricone.schoolYear\": \"2019\",\n" + "\"ricone.districtId\": \"999999\",\n" + "\"address1\": \"123 Something Road\",\n" + "\"city\": \"Albany\",\n" + "\"state\": \"NY\",\n" + "\"postCode\": \"12205\",\n" + "\"country\": \"US\"\n" + "}")
public class Metadata implements Serializable {
	private final static long serialVersionUID = -210985162537038327L;
	@JsonIgnore
	private LinkedHashMap<String, Object> additionalProperties = new LinkedHashMap<>();

	public Metadata() {
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