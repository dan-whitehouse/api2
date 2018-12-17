package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "birthDate", "sex", "americanIndianOrAlaskaNative", "asian", "blackOrAfricanAmerican", "nativeHawaiianOrOtherPacificIslander", "white", "demographicRaceTwoOrMoreRaces", "hispanicOrLatinoEthnicity", "countryOfBirthCode", "stateOfBirthAbbreviation", "cityOfBirth", "publicSchoolResidenceStatus"})
public class Demographic extends Base implements Serializable {

	@JsonProperty("birthDate")
	private String birthDate;
	@JsonProperty("sex")
	private Gender sex;
	@JsonProperty("americanIndianOrAlaskaNative")
	private String americanIndianOrAlaskaNative;
	@JsonProperty("asian")
	private String asian;
	@JsonProperty("blackOrAfricanAmerican")
	private String blackOrAfricanAmerican;
	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	private String nativeHawaiianOrOtherPacificIslander;
	@JsonProperty("white")
	private String white;
	@JsonProperty("demographicRaceTwoOrMoreRaces")
	private String demographicRaceTwoOrMoreRaces;
	@JsonProperty("hispanicOrLatinoEthnicity")
	private String hispanicOrLatinoEthnicity;
	@JsonProperty("countryOfBirthCode")
	private String countryOfBirthCode;
	@JsonProperty("stateOfBirthAbbreviation")
	private String stateOfBirthAbbreviation;
	@JsonProperty("cityOfBirth")
	private String cityOfBirth;
	@JsonProperty("publicSchoolResidenceStatus")
	private String publicSchoolResidenceStatus;
	private final static long serialVersionUID = 2904493540623173207L;

	/**
	 * No args constructor for use in serialization
	 */
	public Demographic() {
	}

	/**
	 * @param publicSchoolResidenceStatus
	 * @param sex
	 * @param nativeHawaiianOrOtherPacificIslander
	 * @param blackOrAfricanAmerican
	 * @param white
	 * @param cityOfBirth
	 * @param americanIndianOrAlaskaNative
	 * @param asian
	 * @param hispanicOrLatinoEthnicity
	 * @param demographicRaceTwoOrMoreRaces
	 * @param stateOfBirthAbbreviation
	 * @param countryOfBirthCode
	 * @param birthDate
	 */
	public Demographic(String birthDate, Gender sex, String americanIndianOrAlaskaNative, String asian, String blackOrAfricanAmerican, String nativeHawaiianOrOtherPacificIslander, String white, String demographicRaceTwoOrMoreRaces, String hispanicOrLatinoEthnicity, String countryOfBirthCode, String stateOfBirthAbbreviation, String cityOfBirth, String publicSchoolResidenceStatus) {
		super();
		this.birthDate = birthDate;
		this.sex = sex;
		this.americanIndianOrAlaskaNative = americanIndianOrAlaskaNative;
		this.asian = asian;
		this.blackOrAfricanAmerican = blackOrAfricanAmerican;
		this.nativeHawaiianOrOtherPacificIslander = nativeHawaiianOrOtherPacificIslander;
		this.white = white;
		this.demographicRaceTwoOrMoreRaces = demographicRaceTwoOrMoreRaces;
		this.hispanicOrLatinoEthnicity = hispanicOrLatinoEthnicity;
		this.countryOfBirthCode = countryOfBirthCode;
		this.stateOfBirthAbbreviation = stateOfBirthAbbreviation;
		this.cityOfBirth = cityOfBirth;
		this.publicSchoolResidenceStatus = publicSchoolResidenceStatus;
	}

	@JsonProperty("birthDate")
	public String getBirthDate() {
		return birthDate;
	}

	@JsonProperty("birthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@JsonProperty("sex")
	public Gender getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(Gender sex) {
		this.sex = sex;
	}

	@JsonProperty("americanIndianOrAlaskaNative")
	public String getAmericanIndianOrAlaskaNative() {
		return americanIndianOrAlaskaNative;
	}

	@JsonProperty("americanIndianOrAlaskaNative")
	public void setAmericanIndianOrAlaskaNative(String americanIndianOrAlaskaNative) {
		this.americanIndianOrAlaskaNative = americanIndianOrAlaskaNative;
	}

	@JsonProperty("asian")
	public String getAsian() {
		return asian;
	}

	@JsonProperty("asian")
	public void setAsian(String asian) {
		this.asian = asian;
	}

	@JsonProperty("blackOrAfricanAmerican")
	public String getBlackOrAfricanAmerican() {
		return blackOrAfricanAmerican;
	}

	@JsonProperty("blackOrAfricanAmerican")
	public void setBlackOrAfricanAmerican(String blackOrAfricanAmerican) {
		this.blackOrAfricanAmerican = blackOrAfricanAmerican;
	}

	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	public String getNativeHawaiianOrOtherPacificIslander() {
		return nativeHawaiianOrOtherPacificIslander;
	}

	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	public void setNativeHawaiianOrOtherPacificIslander(String nativeHawaiianOrOtherPacificIslander) {
		this.nativeHawaiianOrOtherPacificIslander = nativeHawaiianOrOtherPacificIslander;
	}

	@JsonProperty("white")
	public String getWhite() {
		return white;
	}

	@JsonProperty("white")
	public void setWhite(String white) {
		this.white = white;
	}

	@JsonProperty("demographicRaceTwoOrMoreRaces")
	public String getDemographicRaceTwoOrMoreRaces() {
		return demographicRaceTwoOrMoreRaces;
	}

	@JsonProperty("demographicRaceTwoOrMoreRaces")
	public void setDemographicRaceTwoOrMoreRaces(String demographicRaceTwoOrMoreRaces) {
		this.demographicRaceTwoOrMoreRaces = demographicRaceTwoOrMoreRaces;
	}

	@JsonProperty("hispanicOrLatinoEthnicity")
	public String getHispanicOrLatinoEthnicity() {
		return hispanicOrLatinoEthnicity;
	}

	@JsonProperty("hispanicOrLatinoEthnicity")
	public void setHispanicOrLatinoEthnicity(String hispanicOrLatinoEthnicity) {
		this.hispanicOrLatinoEthnicity = hispanicOrLatinoEthnicity;
	}

	@JsonProperty("countryOfBirthCode")
	public String getCountryOfBirthCode() {
		return countryOfBirthCode;
	}

	@JsonProperty("countryOfBirthCode")
	public void setCountryOfBirthCode(String countryOfBirthCode) {
		this.countryOfBirthCode = countryOfBirthCode;
	}

	@JsonProperty("stateOfBirthAbbreviation")
	public String getStateOfBirthAbbreviation() {
		return stateOfBirthAbbreviation;
	}

	@JsonProperty("stateOfBirthAbbreviation")
	public void setStateOfBirthAbbreviation(String stateOfBirthAbbreviation) {
		this.stateOfBirthAbbreviation = stateOfBirthAbbreviation;
	}

	@JsonProperty("cityOfBirth")
	public String getCityOfBirth() {
		return cityOfBirth;
	}

	@JsonProperty("cityOfBirth")
	public void setCityOfBirth(String cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}

	@JsonProperty("publicSchoolResidenceStatus")
	public String getPublicSchoolResidenceStatus() {
		return publicSchoolResidenceStatus;
	}

	@JsonProperty("publicSchoolResidenceStatus")
	public void setPublicSchoolResidenceStatus(String publicSchoolResidenceStatus) {
		this.publicSchoolResidenceStatus = publicSchoolResidenceStatus;
	}
}