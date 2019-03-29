package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "birthDate", "sex", "americanIndianOrAlaskaNative", "asian", "blackOrAfricanAmerican", "nativeHawaiianOrOtherPacificIslander", "white", "demographicRaceTwoOrMoreRaces", "hispanicOrLatinoEthnicity", "countryOfBirthCode", "stateOfBirthAbbreviation", "cityOfBirth", "publicSchoolResidenceStatus"})
public class Demographic extends Base implements Serializable {
	private final static long serialVersionUID = 2904493540623173207L;
	@JsonProperty("birthDate")
	private LocalDate birthDate;
	@JsonProperty("sex")
	private Gender sex;
	@JsonProperty("americanIndianOrAlaskaNative")
	private Boolean americanIndianOrAlaskaNative;
	@JsonProperty("asian")
	private Boolean asian;
	@JsonProperty("blackOrAfricanAmerican")
	private Boolean blackOrAfricanAmerican;
	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	private Boolean nativeHawaiianOrOtherPacificIslander;
	@JsonProperty("white")
	private Boolean white;
	@JsonProperty("demographicRaceTwoOrMoreRaces")
	private Boolean demographicRaceTwoOrMoreRaces;
	@JsonProperty("hispanicOrLatinoEthnicity")
	private Boolean hispanicOrLatinoEthnicity;
	@JsonProperty("countryOfBirthCode")
	private String countryOfBirthCode;
	@JsonProperty("stateOfBirthAbbreviation")
	private String stateOfBirthAbbreviation;
	@JsonProperty("cityOfBirth")
	private String cityOfBirth;
	@JsonProperty("publicSchoolResidenceStatus")
	private String publicSchoolResidenceStatus;

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
	public Demographic(LocalDate birthDate, Gender sex, Boolean americanIndianOrAlaskaNative, Boolean asian, Boolean blackOrAfricanAmerican, Boolean nativeHawaiianOrOtherPacificIslander, Boolean white, Boolean demographicRaceTwoOrMoreRaces, Boolean hispanicOrLatinoEthnicity, String countryOfBirthCode, String stateOfBirthAbbreviation, String cityOfBirth, String publicSchoolResidenceStatus) {
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
	public LocalDate getBirthDate() {
		return birthDate;
	}

	@JsonProperty("birthDate")
	public void setBirthDate(LocalDate birthDate) {
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
	public Boolean getAmericanIndianOrAlaskaNative() {
		return americanIndianOrAlaskaNative;
	}

	@JsonProperty("americanIndianOrAlaskaNative")
	public void setAmericanIndianOrAlaskaNative(Boolean americanIndianOrAlaskaNative) {
		this.americanIndianOrAlaskaNative = americanIndianOrAlaskaNative;
	}

	@JsonProperty("asian")
	public Boolean getAsian() {
		return asian;
	}

	@JsonProperty("asian")
	public void setAsian(Boolean asian) {
		this.asian = asian;
	}

	@JsonProperty("blackOrAfricanAmerican")
	public Boolean getBlackOrAfricanAmerican() {
		return blackOrAfricanAmerican;
	}

	@JsonProperty("blackOrAfricanAmerican")
	public void setBlackOrAfricanAmerican(Boolean blackOrAfricanAmerican) {
		this.blackOrAfricanAmerican = blackOrAfricanAmerican;
	}

	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	public Boolean getNativeHawaiianOrOtherPacificIslander() {
		return nativeHawaiianOrOtherPacificIslander;
	}

	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	public void setNativeHawaiianOrOtherPacificIslander(Boolean nativeHawaiianOrOtherPacificIslander) {
		this.nativeHawaiianOrOtherPacificIslander = nativeHawaiianOrOtherPacificIslander;
	}

	@JsonProperty("white")
	public Boolean getWhite() {
		return white;
	}

	@JsonProperty("white")
	public void setWhite(Boolean white) {
		this.white = white;
	}

	@JsonProperty("demographicRaceTwoOrMoreRaces")
	public Boolean getDemographicRaceTwoOrMoreRaces() {
		return demographicRaceTwoOrMoreRaces;
	}

	@JsonProperty("demographicRaceTwoOrMoreRaces")
	public void setDemographicRaceTwoOrMoreRaces(Boolean demographicRaceTwoOrMoreRaces) {
		this.demographicRaceTwoOrMoreRaces = demographicRaceTwoOrMoreRaces;
	}

	@JsonProperty("hispanicOrLatinoEthnicity")
	public Boolean getHispanicOrLatinoEthnicity() {
		return hispanicOrLatinoEthnicity;
	}

	@JsonProperty("hispanicOrLatinoEthnicity")
	public void setHispanicOrLatinoEthnicity(Boolean hispanicOrLatinoEthnicity) {
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