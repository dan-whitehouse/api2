package org.ricone.api.oneroster.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"imsx_codeMajor", "imsx_severity", "imsx_messageRefIdentifier", "imsx_operationRefIdentifier", "imsx_description", "imsx_codeMinor"})
public class StatusInfoSet implements Serializable {
	@JsonProperty("imsx_codeMajor")
	private CodeMajor imsxCodeMajor;
	@JsonProperty("imsx_severity")
	private Severity imsxSeverity;
	@JsonProperty("imsx_messageRefIdentifier")
	private String imsxMessageRefIdentifier;
	@JsonProperty("imsx_operationRefIdentifier")
	private String imsxOperationRefIdentifier;
	@JsonProperty("imsx_description")
	private String imsxDescription;
	@JsonProperty("imsx_codeMinor")
	private CodeMinor imsxCodeMinor;
	private final static long serialVersionUID = 1931034646494144369L;

	/**
	 * No args constructor for use in serialization
	 */
	public StatusInfoSet() {
	}

	/**
	 * @param imsxSeverity
	 * @param imsxCodeMinor
	 * @param imsxMessageRefIdentifier
	 * @param imsxDescription
	 * @param imsxOperationRefIdentifier
	 * @param imsxCodeMajor
	 */
	public StatusInfoSet(CodeMajor imsxCodeMajor, Severity imsxSeverity, String imsxMessageRefIdentifier, String imsxOperationRefIdentifier, String imsxDescription, CodeMinor imsxCodeMinor) {
		super();
		this.imsxCodeMajor = imsxCodeMajor;
		this.imsxSeverity = imsxSeverity;
		this.imsxMessageRefIdentifier = imsxMessageRefIdentifier;
		this.imsxOperationRefIdentifier = imsxOperationRefIdentifier;
		this.imsxDescription = imsxDescription;
		this.imsxCodeMinor = imsxCodeMinor;
	}

	@JsonProperty("imsx_codeMajor")
	public CodeMajor getImsxCodeMajor() {
		return imsxCodeMajor;
	}

	@JsonProperty("imsx_codeMajor")
	public void setImsxCodeMajor(CodeMajor imsxCodeMajor) {
		this.imsxCodeMajor = imsxCodeMajor;
	}

	@JsonProperty("imsx_severity")
	public Severity getImsxSeverity() {
		return imsxSeverity;
	}

	@JsonProperty("imsx_severity")
	public void setImsxSeverity(Severity imsxSeverity) {
		this.imsxSeverity = imsxSeverity;
	}

	@JsonProperty("imsx_messageRefIdentifier")
	public String getImsxMessageRefIdentifier() {
		return imsxMessageRefIdentifier;
	}

	@JsonProperty("imsx_messageRefIdentifier")
	public void setImsxMessageRefIdentifier(String imsxMessageRefIdentifier) {
		this.imsxMessageRefIdentifier = imsxMessageRefIdentifier;
	}

	@JsonProperty("imsx_operationRefIdentifier")
	public String getImsxOperationRefIdentifier() {
		return imsxOperationRefIdentifier;
	}

	@JsonProperty("imsx_operationRefIdentifier")
	public void setImsxOperationRefIdentifier(String imsxOperationRefIdentifier) {
		this.imsxOperationRefIdentifier = imsxOperationRefIdentifier;
	}

	@JsonProperty("imsx_description")
	public String getImsxDescription() {
		return imsxDescription;
	}

	@JsonProperty("imsx_description")
	public void setImsxDescription(String imsxDescription) {
		this.imsxDescription = imsxDescription;
	}

	@JsonProperty("imsx_codeMinor")
	public CodeMinor getImsxCodeMinor() {
		return imsxCodeMinor;
	}

	@JsonProperty("imsx_codeMinor")
	public void setImsxCodeMinor(CodeMinor imsxCodeMinor) {
		this.imsxCodeMinor = imsxCodeMinor;
	}
}