package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.EnrollmentComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@IdClass(EnrollmentComposite.class)
@Immutable @Entity @Table(name = "demographicview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class DemographicView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "SourcedId")
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear")
	@Id private Integer sourcedSchoolYear;

	@Column(name = "BirthDate")
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

	@Column(name = "HispanicOrLatinoEthnicity")
	private Boolean hispanicOrLatinoEthnicity;

	public DemographicView() {
	}

	public DemographicView(String sourcedId, Integer sourcedSchoolYear, LocalDate birthDate, String sex, Boolean americanIndianOrAlaskaNative, Boolean asian, Boolean blackOrAfricanAmerican, Boolean nativeHawaiianOrOtherPacificIslander, Boolean white, Boolean hispanicOrLatinoEthnicity) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.birthDate = birthDate;
		this.sex = sex;
		this.americanIndianOrAlaskaNative = americanIndianOrAlaskaNative;
		this.asian = asian;
		this.blackOrAfricanAmerican = blackOrAfricanAmerican;
		this.nativeHawaiianOrOtherPacificIslander = nativeHawaiianOrOtherPacificIslander;
		this.white = white;
		this.hispanicOrLatinoEthnicity = hispanicOrLatinoEthnicity;
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

	public Boolean getHispanicOrLatinoEthnicity() {
		return hispanicOrLatinoEthnicity;
	}

	public void setHispanicOrLatinoEthnicity(Boolean hispanicOrLatinoEthnicity) {
		this.hispanicOrLatinoEthnicity = hispanicOrLatinoEthnicity;
	}
}
