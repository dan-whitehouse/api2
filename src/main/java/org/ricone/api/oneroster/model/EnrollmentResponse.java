package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"enrollment"})
public class EnrollmentResponse extends BaseResponse implements Serializable {

	@JsonProperty("enrollment")
	private Enrollment enrollment;
	private final static long serialVersionUID = 6097789264454176014L;

	/**
	 * No args constructor for use in serialization
	 */
	public EnrollmentResponse() {
	}

	/**
	 * @param enrollment
	 */
	public EnrollmentResponse(Enrollment enrollment) {
		super();
		this.enrollment = enrollment;
	}

	@JsonProperty("enrollment")
	public Enrollment getEnrollment() {
		return enrollment;
	}

	@JsonProperty("enrollment")
	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

}