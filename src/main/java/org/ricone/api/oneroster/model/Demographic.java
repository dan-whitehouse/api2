package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "birthDate", "sex", "americanIndianOrAlaskaNative", "asian", "blackOrAfricanAmerican", "nativeHawaiianOrOtherPacificIslander", "white", "demographicRaceTwoOrMoreRaces", "hispanicOrLatinoEthnicity", "countryOfBirthCode", "stateOfBirthAbbreviation", "cityOfBirth", "publicSchoolResidenceStatus"})
public class Demographic extends Base implements Serializable {
	private final static long serialVersionUID = 2904493540623173207L;
	@JsonProperty("birthDate")
	@ApiModelProperty(position = 5, value = "", example = "2005-11-28")
	private LocalDate birthDate;
	@JsonProperty("sex")
	@ApiModelProperty(position = 6, value = "The set of permitted tokens for the type of gender", example = "")
	private Gender sex;
	@JsonProperty("americanIndianOrAlaskaNative")
	@ApiModelProperty(position = 7, value = "", example = "true", allowableValues = "true,false")
	private Boolean americanIndianOrAlaskaNative;
	@JsonProperty("asian")
	@ApiModelProperty(position = 8, value = "", example = "false", allowableValues = "true,false")
	private Boolean asian;
	@JsonProperty("blackOrAfricanAmerican")
	@ApiModelProperty(position = 9, value = "", example = "true", allowableValues = "true,false")
	private Boolean blackOrAfricanAmerican;
	@JsonProperty("nativeHawaiianOrOtherPacificIslander")
	@ApiModelProperty(position = 10, value = "", example = "false", allowableValues = "true,false")
	private Boolean nativeHawaiianOrOtherPacificIslander;
	@JsonProperty("white")
	@ApiModelProperty(position = 11, value = "", example = "true", allowableValues = "true,false")
	private Boolean white;
	@JsonProperty("demographicRaceTwoOrMoreRaces")
	@ApiModelProperty(position = 12, value = "", example = "false", allowableValues = "true,false")
	private Boolean demographicRaceTwoOrMoreRaces;
	@JsonProperty("hispanicOrLatinoEthnicity")
	@ApiModelProperty(position = 13, value = "", example = "true", allowableValues = "true,false")
	private Boolean hispanicOrLatinoEthnicity;
	@JsonProperty("countryOfBirthCode")
	@ApiModelProperty(position = 14, value = "", example = "US", notes = "https://ceds.ed.gov/CEDSElementDetails.aspx?TermxTopicId=20002")
	private String countryOfBirthCode;
	@JsonProperty("stateOfBirthAbbreviation")
	@ApiModelProperty(position = 15, value = "", example = "NY", notes = "https://ceds.ed.gov/CEDSElementDetails.aspx?TermxTopicId=20837")
	private String stateOfBirthAbbreviation;
	@JsonProperty("cityOfBirth")
	@ApiModelProperty(position = 16, value = "", example = "Albany")
	private String cityOfBirth;
	@JsonProperty("publicSchoolResidenceStatus")
	@ApiModelProperty(position = 17, value = "", example = "01652", notes = "https://ceds.ed.gov/CEDSElementDetails.aspx?TermxTopicId=20863")
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