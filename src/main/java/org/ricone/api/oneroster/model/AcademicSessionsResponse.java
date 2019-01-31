package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"academicSessions"})
public class AcademicSessionsResponse extends BaseMultiResponse<AcademicSession> implements Serializable {
	private final static long serialVersionUID = 9018025310977717656L;

	public AcademicSessionsResponse(List<AcademicSession> academicSessions) {
		super(academicSessions);
	}

	public AcademicSessionsResponse(List<AcademicSession> academicSessions, List<StatusInfoSet> errors) {
		super(academicSessions, errors);
	}

	@JsonProperty("academicSessions")
	@Override public List<AcademicSession> getData() { return super.getData(); }

	@JsonProperty("academicSessions")
	@Override public void setData(List<AcademicSession> academicSessions) { super.setData(academicSessions);}
}