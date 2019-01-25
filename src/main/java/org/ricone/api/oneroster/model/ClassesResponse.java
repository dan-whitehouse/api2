package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"classes"})
public class ClassesResponse extends BaseResponse implements Serializable {

	@JsonProperty("classes")
	private List<Class> _class = new ArrayList<Class>();
	private final static long serialVersionUID = 5934808405475046263L;

	/**
	 * No args constructor for use in serialization
	 */
	public ClassesResponse() {
	}

	/**
	 * @param _class
	 */
	public ClassesResponse(List<Class> _class) {
		super();
		this._class = _class;
	}

	@JsonProperty("classes")
	public List<Class> getClass_() {
		return _class;
	}

	@JsonProperty("classes")
	public void setClass_(List<Class> _class) {
		this._class = _class;
	}
}