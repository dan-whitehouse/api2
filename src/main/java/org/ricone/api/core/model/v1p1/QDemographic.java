package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.v1p1.composite.SourcedComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "onerosterv1p1_demographic")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QDemographic implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourcedId", unique = true, nullable = false, length = 64)
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear", nullable = false, length = 6)
	@Id private Integer sourcedSchoolYear;

	@Column(name = "DistrictId", length = 30)
	private String districtId;

	@Column(name = "BirthDate", length = 30)
	private LocalDate birthDate;

	@Column(name = "Sex")
	private String sex;

	@Column(name = "AmericanIndianOrAlaskaNative")
	private Boolean americanIndianOrAlaskaNative;

	@Column(name = "Asian")
	private Boolean asian;

	@Column(name = "BlackOrAfricanAmerican")
	private Boolean blackOrAfricanAmerican;

	@Column(name = "NativeHawaiianOrOtherPacificIslander")
	private Boolean nativeHawaiianOrOtherPacificIslander;

	@Column(name = "White")
	private Boolean white;

	@Column(name = "DemographicRaceTwoOrMoreRaces")
	private Boolean demographicRaceTwoOrMoreRaces;

	@Column(name = "HispanicOrLatinoEthnicity")
	private Boolean hispanicOrLatinoEthnicity;

	@Column(name = "CountryOfBirthCode")
	private String countryOfBirthCode;

	@Column(name = "StateOfBirthAbbreviation")
	private String stateOfBirthAbbreviation;

	@Column(name = "cityOfBirth")
	private String CityOfBirth;

	@Column(name = "PublicSchoolResidenceStatus")
	private String publicSchoolResidenceStatus;

	public QDemographic() {
	}

	public QDemographic(String sourcedId, Integer sourcedSchoolYear, String districtId, LocalDate birthDate, String sex, Boolean americanIndianOrAlaskaNative, Boolean asian, Boolean blackOrAfricanAmerican, Boolean nativeHawaiianOrOtherPacificIslander, Boolean white, Boolean demographicRaceTwoOrMoreRaces, Boolean hispanicOrLatinoEthnicity, String countryOfBirthCode, String stateOfBirthAbbreviation, String cityOfBirth, String publicSchoolResidenceStatus) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.districtId = districtId;
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
		CityOfBirth = cityOfBirth;
		this.publicSchoolResidenceStatus = publicSchoolResidenceStatus;
	}

	public String getSourcedId() {
		return sourcedId;
	}

	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	public Integer getSourcedSchoolYear() {
		return sourcedSchoolYear;
	}

	public void setSourcedSchoolYear(Integer sourcedSchoolYear) {
		this.sourcedSchoolYear = sourcedSchoolYear;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Boolean getAmericanIndianOrAlaskaNative() {
		return americanIndianOrAlaskaNative;
	}

	public void setAmericanIndianOrAlaskaNative(Boolean americanIndianOrAlaskaNative) {
		this.americanIndianOrAlaskaNative = americanIndianOrAlaskaNative;
	}

	public Boolean getAsian() {
		return asian;
	}

	public void setAsian(Boolean asian) {
		this.asian = asian;
	}

	public Boolean getBlackOrAfricanAmerican() {
		return blackOrAfricanAmerican;
	}

	public void setBlackOrAfricanAmerican(Boolean blackOrAfricanAmerican) {
		this.blackOrAfricanAmerican = blackOrAfricanAmerican;
	}

	public Boolean getNativeHawaiianOrOtherPacificIslander() {
		return nativeHawaiianOrOtherPacificIslander;
	}

	public void setNativeHawaiianOrOtherPacificIslander(Boolean nativeHawaiianOrOtherPacificIslander) {
		this.nativeHawaiianOrOtherPacificIslander = nativeHawaiianOrOtherPacificIslander;
	}

	public Boolean getWhite() {
		return white;
	}

	public void setWhite(Boolean white) {
		this.white = white;
	}

	public Boolean getDemographicRaceTwoOrMoreRaces() {
		return demographicRaceTwoOrMoreRaces;
	}

	public void setDemographicRaceTwoOrMoreRaces(Boolean demographicRaceTwoOrMoreRaces) {
		this.demographicRaceTwoOrMoreRaces = demographicRaceTwoOrMoreRaces;
	}

	public Boolean getHispanicOrLatinoEthnicity() {
		return hispanicOrLatinoEthnicity;
	}

	public void setHispanicOrLatinoEthnicity(Boolean hispanicOrLatinoEthnicity) {
		this.hispanicOrLatinoEthnicity = hispanicOrLatinoEthnicity;
	}

	public String getCountryOfBirthCode() {
		return countryOfBirthCode;
	}

	public void setCountryOfBirthCode(String countryOfBirthCode) {
		this.countryOfBirthCode = countryOfBirthCode;
	}

	public String getStateOfBirthAbbreviation() {
		return stateOfBirthAbbreviation;
	}

	public void setStateOfBirthAbbreviation(String stateOfBirthAbbreviation) {
		this.stateOfBirthAbbreviation = stateOfBirthAbbreviation;
	}

	public String getCityOfBirth() {
		return CityOfBirth;
	}

	public void setCityOfBirth(String cityOfBirth) {
		CityOfBirth = cityOfBirth;
	}

	public String getPublicSchoolResidenceStatus() {
		return publicSchoolResidenceStatus;
	}

	public void setPublicSchoolResidenceStatus(String publicSchoolResidenceStatus) {
		this.publicSchoolResidenceStatus = publicSchoolResidenceStatus;
	}
}
