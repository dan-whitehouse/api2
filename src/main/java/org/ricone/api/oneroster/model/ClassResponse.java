package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class"})
public class ClassResponse extends BaseResponse implements Serializable {

	@JsonProperty("class")
	private Class _class;
	private final static long serialVersionUID = 7631981467689004007L;

	/**
	 * No args constructor for use in serialization
	 */
	public ClassResponse() {
	}

	/**
	 * @param _class
	 */
	public ClassResponse(Class _class) {
		super();
		this._class = _class;
	}

	@JsonProperty("class")
	public Class getClass_() {
		return _class;
	}

	@JsonProperty("class")
	public void setClass_(Class _class) {
		this._class = _class;
	}
}