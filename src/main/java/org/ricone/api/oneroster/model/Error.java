package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"codeMajor", "severity", "codeMinor", "description", "messageRefIdentifier", "operationRefIdentifier"})
public class Error implements Serializable {
	private final static long serialVersionUID = 1931034646494144369L;
	@JsonProperty("codeMajor")
	@ApiModelProperty(position = 1, value = "", example = "")
	private CodeMajor codeMajor;
	@JsonProperty("severity")
	@ApiModelProperty(position = 2, value = "", example = "")
	private Severity severity;
	@JsonProperty("messageRefIdentifier")
	@ApiModelProperty(position = 3, value = "", example = "")
	private String messageRefIdentifier;
	@JsonProperty("operationRefIdentifier")
	@ApiModelProperty(position = 4, value = "", example = "")
	private String operationRefIdentifier;
	@JsonProperty("description")
	@ApiModelProperty(position = 5, value = "", example = "")
	private String description;
	@JsonProperty("codeMinor")
	@ApiModelProperty(position = 6, value = "", example = "")
	private CodeMinor codeMinor;

	/**
	 * No args constructor for use in serialization
	 */
	public Error() {
	}

	public Error(Severity severity, CodeMajor codeMajor, CodeMinor codeMinor, String description) {
		super();
		this.severity = severity;
		this.codeMajor = codeMajor;
		this.codeMinor = codeMinor;
		this.description = description;
	}

	@JsonProperty("codeMajor")
	public CodeMajor getCodeMajor() {
		return codeMajor;
	}

	@JsonProperty("codeMajor")
	public void setCodeMajor(CodeMajor codeMajor) {
		this.codeMajor = codeMajor;
	}

	@JsonProperty("severity")
	public Severity getSeverity() {
		return severity;
	}

	@JsonProperty("severity")
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	@JsonProperty("messageRefIdentifier")
	public String getMessageRefIdentifier() {
		return messageRefIdentifier;
	}

	@JsonProperty("messageRefIdentifier")
	public void setMessageRefIdentifier(String messageRefIdentifier) {
		this.messageRefIdentifier = messageRefIdentifier;
	}

	@JsonProperty("operationRefIdentifier")
	public String getOperationRefIdentifier() {
		return operationRefIdentifier;
	}

	@JsonProperty("operationRefIdentifier")
	public void setOperationRefIdentifier(String operationRefIdentifier) {
		this.operationRefIdentifier = operationRefIdentifier;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("codeMinor")
	public CodeMinor getCodeMinor() {
		return codeMinor;
	}

	@JsonProperty("codeMinor")
	public void setCodeMinor(CodeMinor codeMinor) {
		this.codeMinor = codeMinor;
	}
}