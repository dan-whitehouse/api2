package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"academicSessions"})
public class AcademicSessionsResponse extends BaseResponse implements Serializable {

	@JsonProperty("academicSessions")
	private List<AcademicSession> academicSessions = new ArrayList<AcademicSession>();
	private final static long serialVersionUID = 9018025310977717656L;

	/**
	 * No args constructor for use in serialization
	 */
	public AcademicSessionsResponse() {
	}

	/**
	 * @param academicSessions
	 */
	public AcademicSessionsResponse(List<AcademicSession> academicSessions) {
		super();
		this.academicSessions = academicSessions;
	}

	@JsonProperty("academicSessions")
	public List<AcademicSession> getAcademicSessions() {
		return academicSessions;
	}

	@JsonProperty("academicSessions")
	public void setAcademicSessions(List<AcademicSession> academicSessions) {
		this.academicSessions = academicSessions;
	}
}