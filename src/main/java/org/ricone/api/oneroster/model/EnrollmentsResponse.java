package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"enrollments"})
public class EnrollmentsResponse extends BaseMultiResponse<Enrollment> implements Serializable {
	private final static long serialVersionUID = -3656792804387663344L;

	public EnrollmentsResponse(List<Enrollment> enrollments) {
		super(enrollments);
	}

	public EnrollmentsResponse(List<Enrollment> enrollments, List<StatusInfoSet> errors) {
		super(enrollments);
	}

	@JsonProperty("enrollments")
	@Override public List<Enrollment> getData() {
		return super.getData();
	}

	@JsonProperty("enrollments")
	@Override public void setData(List<Enrollment> enrollments) {
		super.setData(enrollments);
	}
}