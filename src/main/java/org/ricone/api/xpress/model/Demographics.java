/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"races", "hispanicLatinoEthnicity", "sex", "birthDate", "countryOfBirth", "usCitizenshipStatus"})
public class Demographics {

	@JsonProperty("races")
	private Races races;
	@JsonProperty("hispanicLatinoEthnicity")
	private String hispanicLatinoEthnicity;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("birthDate")
	private String birthDate;
	@JsonProperty("countryOfBirth")
	private String countryOfBirth;
	@JsonProperty("usCitizenshipStatus")
	private String usCitizenshipStatus;

	public Demographics() {
	}

	public Demographics(Races races, String hispanicLatinoEthnicity, String sex, String birthDate, String countryOfBirth, String usCitizenshipStatus) {
		super();
		this.races = races;
		this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
		this.sex = sex;
		this.birthDate = birthDate;
		this.countryOfBirth = countryOfBirth;
		this.usCitizenshipStatus = usCitizenshipStatus;
	}

	@JsonProperty("races")
	public Races getRaces() {
		return races;
	}

	@JsonProperty("races")
	public void setRaces(Races races) {
		this.races = races;
	}

	@JsonProperty("hispanicLatinoEthnicity")
	public String getHispanicLatinoEthnicity() {
		return hispanicLatinoEthnicity;
	}

	@JsonProperty("hispanicLatinoEthnicity")
	public void setHispanicLatinoEthnicity(String hispanicLatinoEthnicity) {
		this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
	}

	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	@JsonProperty("birthDate")
	public String getBirthDate() {
		return birthDate;
	}

	@JsonProperty("birthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@JsonProperty("countryOfBirth")
	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	@JsonProperty("countryOfBirth")
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	@JsonProperty("usCitizenshipStatus")
	public String getUsCitizenshipStatus() {
		return usCitizenshipStatus;
	}

	@JsonProperty("usCitizenshipStatus")
	public void setUsCitizenshipStatus(String usCitizenshipStatus) {
		this.usCitizenshipStatus = usCitizenshipStatus;
	}

	@Override
	public String toString() {
		return "Demographics{" + "races=" + races + ", hispanicLatinoEthnicity='" + hispanicLatinoEthnicity + '\'' + ", sex='" + sex + '\'' + ", birthDate='" + birthDate + '\'' + ", countryOfBirth='" + countryOfBirth + '\'' + ", usCitizenshipStatus='" + usCitizenshipStatus + '\'' + '}';
	}
}