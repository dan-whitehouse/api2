package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentContactEmailComposite;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactemail")
@IdClass(StudentContactEmailComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentContactEmail implements java.io.Serializable {
	private static final long serialVersionUID = -2097058017964142612L;
	
	@Column(name = "StudentContactEmailRefId", unique = true, nullable = false, length = 64)
	@Id
    private String studentContactEmailRefId;
	
	@Column(name = "StudentContactEmailSchoolYear", length = 6)
	@Id
    private Integer studentContactEmailSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentContactRefId", referencedColumnName="studentContactRefId", nullable = false),
		@JoinColumn(name="StudentContactSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
	})
	private StudentContact studentContact;
	
	@Column(name = "EmailAddress", length = 128)
	private String emailAddress;
	
	@Column(name = "EmailTypeCode", length = 50)
	private String emailTypeCode;
	
	@Column(name = "PrimaryEmailAddressIndicator")
	private Boolean primaryEmailAddressIndicator;

	public StudentContactEmail() {
	}

	public StudentContactEmail(String studentContactEmailRefId, Integer studentContactEmailSchoolYear, StudentContact studentContact, String emailAddress, String emailTypeCode, Boolean primaryEmailAddressIndicator) {
		super();
		this.studentContactEmailRefId = studentContactEmailRefId;
		this.studentContactEmailSchoolYear = studentContactEmailSchoolYear;
		this.studentContact = studentContact;
		this.emailAddress = emailAddress;
		this.emailTypeCode = emailTypeCode;
		this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
	}
	
	public String getStudentContactEmailRefId() {
		return this.studentContactEmailRefId;
	}
	public void setStudentContactEmailRefId(String studentContactEmailRefId) {
		this.studentContactEmailRefId = studentContactEmailRefId;
	}
	
	public Integer getStudentContactEmailSchoolYear() {
		return studentContactEmailSchoolYear;
	}
	public void setStudentContactEmailSchoolYear(Integer studentContactEmailSchoolYear) {
		this.studentContactEmailSchoolYear = studentContactEmailSchoolYear;
	}

	public StudentContact getStudentContact() {
		return this.studentContact;
	}
	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailTypeCode() {
		return this.emailTypeCode;
	}
	public void setEmailTypeCode(String emailTypeCode) {
		this.emailTypeCode = emailTypeCode;
	}
	
	public Boolean getPrimaryEmailAddressIndicator() {
		return this.primaryEmailAddressIndicator;
	}
	public void setPrimaryEmailAddressIndicator(Boolean primaryEmailAddressIndicator) {
		this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
	}
}
