package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","class"})
public class ClassResponse extends BaseSingleResponse<Class> implements Serializable {
	private final static long serialVersionUID = 7631981467689004007L;

	public ClassResponse() {
	}

	public ClassResponse(Class _class) {
		super(_class);
	}

	public ClassResponse(Class _class, List<Error> errors) {
		super(_class, errors);
	}

	@JsonProperty("class")
	@Override public Class getData() {
		return super.getData();
	}

	@JsonProperty("class")
	@Override public void setData(Class _class) {
		super.setData(_class);
	}
}