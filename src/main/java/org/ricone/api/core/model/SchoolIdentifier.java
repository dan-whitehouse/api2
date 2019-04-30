package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.SchoolIdentifierComposite;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "schoolidentifier")
@IdClass(SchoolIdentifierComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SchoolIdentifier implements Serializable {
	private static final long serialVersionUID = 8276829540278448016L;
	
	@Column(name = "SchoolIdentifierRefId", unique = true, nullable = false, length = 64)
	@Id private String schoolIdentifierRefId;
	
	@Column(name = "SchoolIdentifierSchoolYear", length = 6)
	@Id private Integer schoolIdentifierSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolRefId", referencedColumnName="schoolRefId", nullable = false),
		@JoinColumn(name="SchoolSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
	})
	private School school;
	
	@Column(name = "IdentificationSystemCode", length = 50)
	private String identificationSystemCode;
	
	@Column(name = "SchoolId", nullable = false, length = 50)
	private String schoolId;

	public SchoolIdentifier() {
	}

	public SchoolIdentifier(String schoolIdentifierRefId, School school, String schoolId) {
		this.schoolIdentifierRefId = schoolIdentifierRefId;
		this.school = school;
		this.schoolId = schoolId;
	}

	public SchoolIdentifier(String schoolIdentifierRefId, School school, String identificationSystemCode, String schoolId) {
		this.schoolIdentifierRefId = schoolIdentifierRefId;
		this.school = school;
		this.identificationSystemCode = identificationSystemCode;
		this.schoolId = schoolId;
	}

	public String getSchoolIdentifierRefId() {
		return this.schoolIdentifierRefId;
	}
	public void setSchoolIdentifierRefId(String schoolIdentifierRefId) {
		this.schoolIdentifierRefId = schoolIdentifierRefId;
	}
	
	public Integer getSchoolIdentifierSchoolYear() {
		return schoolIdentifierSchoolYear;
	}
	public void setSchoolIdentifierSchoolYear(Integer schoolIdentifierSchoolYear) {
		this.schoolIdentifierSchoolYear = schoolIdentifierSchoolYear;
	}

	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	public String getIdentificationSystemCode() {
		return this.identificationSystemCode;
	}
	public void setIdentificationSystemCode(String identificationSystemCode) {
		this.identificationSystemCode = identificationSystemCode;
	}

	public String getSchoolId() {
		return this.schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "SchoolIdentifier [schoolIdentifierRefId=" + schoolIdentifierRefId + ", schoolIdentifierSchoolYear=" + schoolIdentifierSchoolYear + ", school=" + school + ", identificationSystemCode=" + identificationSystemCode + ", schoolId=" + schoolId + "]";
	}
}
