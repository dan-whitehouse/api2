package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","classes"})
public class ClassesResponse extends BaseMultiResponse<Class> implements Serializable {
	private final static long serialVersionUID = 5934808405475046263L;

	public ClassesResponse() {
	}

	public ClassesResponse(List<Class> _class) {
		super(_class);
	}

	public ClassesResponse(List<Class> _class, List<Error> errors) {
		super(_class, errors);
	}

	@JsonProperty("classes")
	@Override public List<Class> getData() {
		return super.getData();
	}

	@JsonProperty("classes")
	@Override public void setData(List<Class> _class) {
		super.setData(_class);
	}
}