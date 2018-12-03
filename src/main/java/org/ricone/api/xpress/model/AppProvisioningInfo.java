package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"loginId", "tempPassword", "tempPasswordExpiryDate"})
public class AppProvisioningInfo implements Serializable {

	@JsonProperty("loginId")
	private String loginId;
	@JsonProperty("tempPassword")
	private String tempPassword;
	@JsonProperty("tempPasswordExpiryDate")
	private String tempPasswordExpiryDate;
	private final static long serialVersionUID = 6144160832199171697L;

	public AppProvisioningInfo() {
	}

	/**
	 * @param loginId
	 * @param tempPassword
	 * @param tempPasswordExpiryDate
	 */
	public AppProvisioningInfo(String loginId, String tempPassword, String tempPasswordExpiryDate) {
		super();
		this.loginId = loginId;
		this.tempPassword = tempPassword;
		this.tempPasswordExpiryDate = tempPasswordExpiryDate;
	}

	@JsonProperty("loginId")
	public String getLoginId() {
		return loginId;
	}

	@JsonProperty("loginId")
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@JsonProperty("tempPassword")
	public String getTempPassword() {
		return tempPassword;
	}

	@JsonProperty("tempPassword")
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	@JsonProperty("tempPasswordExpiryDate")
	public String getTempPasswordExpiryDate() {
		return tempPasswordExpiryDate;
	}

	@JsonProperty("tempPasswordExpiryDate")
	public void setTempPasswordExpiryDate(String tempPasswordExpiryDate) {
		this.tempPasswordExpiryDate = tempPasswordExpiryDate;
	}
}