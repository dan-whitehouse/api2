package org.ricone.api.xpress.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "validation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "districts" })
public class Validation {

	@JsonProperty("districts")
	private Districts districts;

	public Validation() {
	}

	public Validation(Districts districts) {
		super();
		this.districts = districts;
	}

	@JsonProperty("districts")
	public Districts getDistricts() {
		return districts;
	}

	@JsonProperty("districts")
	public void setDistricts(Districts districts) {
		this.districts = districts;
	}
}