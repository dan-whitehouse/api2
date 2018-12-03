package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.SchoolTelephoneComposite;

import javax.persistence.*;

@Entity
@Table(name = "schooltelephone")
@IdClass(SchoolTelephoneComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SchoolTelephone implements java.io.Serializable {
	private static final long serialVersionUID = 2528704244410039161L;
	
	@Column(name = "SchoolPhoneRefId", unique = true, nullable = false, length = 64)
	@Id
    private String schoolPhoneRefId;
	
	@Column(name = "SchoolPhoneSchoolYear", nullable = false, length = 6)
	@Id
    private Integer schoolPhoneSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolRefId", referencedColumnName="schoolRefId", nullable = false),
		@JoinColumn(name="SchoolSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
	})
	private School school;
	
	@Column(name = "TelephoneNumber", length = 24)
	private String telephoneNumber;
	
	@Column(name = "PrimaryTelephoneNumberIndicator")
	private Boolean primaryTelephoneNumberIndicator;
	
	@Column(name = "TelephoneNumberTypeCode", length = 50)
	private String telephoneNumberTypeCode;

	public SchoolTelephone() {
	}
	
	public SchoolTelephone(String schoolPhoneRefId, Integer schoolPhoneSchoolYear, School school, String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode) {
		super();
		this.schoolPhoneRefId = schoolPhoneRefId;
		this.schoolPhoneSchoolYear = schoolPhoneSchoolYear;
		this.school = school;
		this.telephoneNumber = telephoneNumber;
		this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
		this.telephoneNumberTypeCode = telephoneNumberTypeCode;
	}

	public String getSchoolPhoneRefId() {
		return this.schoolPhoneRefId;
	}
	public void setSchoolPhoneRefId(String schoolPhoneRefId) {
		this.schoolPhoneRefId = schoolPhoneRefId;
	}

	public Integer getSchoolPhoneSchoolYear() {
		return schoolPhoneSchoolYear;
	}
	public void setSchoolPhoneSchoolYear(Integer schoolPhoneSchoolYear) {
		this.schoolPhoneSchoolYear = schoolPhoneSchoolYear;
	}
	
	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Boolean getPrimaryTelephoneNumberIndicator() {
		return this.primaryTelephoneNumberIndicator;
	}
	public void setPrimaryTelephoneNumberIndicator(Boolean primaryTelephoneNumberIndicator) {
		this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
	}

	public String getTelephoneNumberTypeCode() {
		return this.telephoneNumberTypeCode;
	}
	public void setTelephoneNumberTypeCode(String telephoneNumberTypeCode) {
		this.telephoneNumberTypeCode = telephoneNumberTypeCode;
	}

	@Override
	public String toString() {
		return "SchoolTelephone [schoolPhoneRefId=" + schoolPhoneRefId + ", schoolPhoneSchoolYear=" + schoolPhoneSchoolYear + ", school=" + school + ", telephoneNumber=" + telephoneNumber + ", primaryTelephoneNumberIndicator=" + primaryTelephoneNumberIndicator + ", telephoneNumberTypeCode=" + telephoneNumberTypeCode + "]";
	}
}
