package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"loginId", "tempPassword", "tempPasswordExpiryDate"})
public class AppProvisioningInfo {
	@JsonProperty("loginId")
	@ApiModelProperty(position = 1, value = "The username associated to a person")
	private String loginId;

	@JsonProperty("tempPassword")
	@ApiModelProperty(position = 2, value = "A temporary password defined by the district")
	private String tempPassword;

	@JsonProperty("tempPasswordExpiryDate")
	@ApiModelProperty(position = 3, value = "A date in which the temporary password is set to expire")
	private String tempPasswordExpiryDate;

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