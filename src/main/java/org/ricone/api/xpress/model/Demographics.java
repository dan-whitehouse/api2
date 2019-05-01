/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"races", "hispanicLatinoEthnicity", "sex", "birthDate", "countryOfBirth", "usCitizenshipStatus"})
public class Demographics {
	@JsonProperty("races")
	@ApiModelProperty(position = 1, value = "A list of races")
	private Races races;

	@JsonProperty("hispanicLatinoEthnicity")
	@ApiModelProperty(position = 2, value = "An indication that the person traces his or her origin or descent to Mexico, Puerto Rico, Cuba, Central and South America, and other Spanish cultures, regardless of race")
	private String hispanicLatinoEthnicity;

	@JsonProperty("sex")
	@ApiModelProperty(position = 3, value = "The concept describing the biological traits that distinguish the males and females of a species")
	private String sex;

	@JsonProperty("birthDate")
	@ApiModelProperty(position = 4, value = "The year, month and day on which a person was born")
	private String birthDate;

	@JsonProperty("countryOfBirth")
	@ApiModelProperty(position = 5, value = "The Country in which a person was born")
	private String countryOfBirth;

	@JsonProperty("usCitizenshipStatus")
	@ApiModelProperty(position = 6, value = "An indicator of whether or not the person is a US citizen")
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

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(races, hispanicLatinoEthnicity, sex, birthDate, countryOfBirth, usCitizenshipStatus).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "Demographics{" + "races=" + races + ", hispanicLatinoEthnicity='" + hispanicLatinoEthnicity + '\'' + ", sex='" + sex + '\'' + ", birthDate='" + birthDate + '\'' + ", countryOfBirth='" + countryOfBirth + '\'' + ", usCitizenshipStatus='" + usCitizenshipStatus + '\'' + '}';
	}
}