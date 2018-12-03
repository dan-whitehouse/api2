package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentContactTelephoneComposite;

import javax.persistence.*;

@Entity
@Table(name = "studentcontacttelephone")
@IdClass(StudentContactTelephoneComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentContactTelephone implements java.io.Serializable {
	private static final long serialVersionUID = -6227475260415652096L;
	
	@Column(name = "StudentContactPhoneRefId", unique = true, nullable = false, length = 64)
	@Id
    private String studentContactPhoneRefId;
	
	@Column(name = "StudentContactPhoneSchoolYear", length = 6)
	@Id
    private Integer studentContactPhoneSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentContactRefId", referencedColumnName="studentContactRefId", nullable = false),
		@JoinColumn(name="StudentContactSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
	})
	private StudentContact studentContact;
	
	@Column(name = "TelephoneNumber", length = 24)
	private String telephoneNumber;
	
	@Column(name = "PrimaryTelephoneNumberIndicator")
	private Boolean primaryTelephoneNumberIndicator;
	
	@Column(name = "TelephoneNumberTypeCode", length = 50)
	private String telephoneNumberTypeCode;

	public StudentContactTelephone() {
	}

	public StudentContactTelephone(String studentContactPhoneRefId, Integer studentContactPhoneSchoolYear, StudentContact studentContact, String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode) {
		super();
		this.studentContactPhoneRefId = studentContactPhoneRefId;
		this.studentContactPhoneSchoolYear = studentContactPhoneSchoolYear;
		this.studentContact = studentContact;
		this.telephoneNumber = telephoneNumber;
		this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
		this.telephoneNumberTypeCode = telephoneNumberTypeCode;
	}

	public String getStudentContactPhoneRefId() {
		return this.studentContactPhoneRefId;
	}
	public void setStudentContactPhoneRefId(String studentContactPhoneRefId) {
		this.studentContactPhoneRefId = studentContactPhoneRefId;
	}

	public Integer getStudentContactPhoneSchoolYear() {
		return studentContactPhoneSchoolYear;
	}
	public void setStudentContactPhoneSchoolYear(Integer studentContactPhoneSchoolYear) {
		this.studentContactPhoneSchoolYear = studentContactPhoneSchoolYear;
	}
	
	public StudentContact getStudentContact() {
		return this.studentContact;
	}
	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
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
}
