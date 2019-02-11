package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","enrollment"})
public class EnrollmentResponse extends BaseSingleResponse<Enrollment> implements Serializable {
	private final static long serialVersionUID = 6097789264454176014L;

	public EnrollmentResponse() {
	}

	public EnrollmentResponse(Enrollment enrollment) {
		super(enrollment);
	}

	public EnrollmentResponse(Enrollment enrollment, List<Error> errors) {
		super(enrollment);
	}

	@JsonProperty("enrollment")
	@Override public Enrollment getData() {
		return super.getData();
	}

	@JsonProperty("enrollment")
	@Override public void setData(Enrollment enrollment) {
		super.setData(enrollment);
	}

}