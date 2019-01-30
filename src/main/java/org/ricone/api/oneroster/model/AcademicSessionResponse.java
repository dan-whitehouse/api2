package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"academicSession"})
public class AcademicSessionResponse extends BaseResponse implements Serializable {

	@JsonProperty("academicSession")
	private AcademicSession academicSession;
	private final static long serialVersionUID = 254536018508141411L;

	/**
	 * No args constructor for use in serialization
	 */
	public AcademicSessionResponse() {
	}

	/**
	 * @param academicSession
	 */
	public AcademicSessionResponse(AcademicSession academicSession) {
		super();
		this.academicSession = academicSession;
	}

	@JsonProperty("academicSession")
	public AcademicSession getAcademicSession() {
		return academicSession;
	}

	@JsonProperty("academicSession")
	public void setAcademicSession(AcademicSession academicSession) {
		this.academicSession = academicSession;
	}

}