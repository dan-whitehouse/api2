package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseSingleResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings","academicSession"})
public class AcademicSessionResponse extends BaseSingleResponse<AcademicSession> implements Serializable {
	private final static long serialVersionUID = 254536018508141411L;

	public AcademicSessionResponse() {
	}

	public AcademicSessionResponse(AcademicSession academicSession) {
		super(academicSession);
	}

	public AcademicSessionResponse(AcademicSession academicSession, List<Error> errors) {
		super(academicSession, errors);
	}

	@JsonProperty("academicSession")
	@Override public AcademicSession getData() {
		return super.getData();
	}

	@JsonProperty("academicSession")
	@Override public void setData(AcademicSession academicSession) {
		super.setData(academicSession);
	}

}