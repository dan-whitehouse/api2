package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentContactIdentifierComposite;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactidentifier")
@IdClass(StudentContactIdentifierComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentContactIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -6250333820678695905L;
	
	@Column(name = "StudentContactIdentifierRefId", unique = true, nullable = false, length = 64)
	@Id
    private String studentContactIdentifierRefId;
	
	@Column(name = "StudentContactIdentifierSchoolYear", length = 6)
	@Id
    private Integer studentContactIdentifierSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentContactRefId", referencedColumnName="studentContactRefId", nullable = false),
		@JoinColumn(name="StudentContactSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
	})
	private StudentContact studentContact;
	
	@Column(name = "IdentificationSystemCode", length = 50)
	private String identificationSystemCode;
	
	@Column(name = "StudentContactId", length = 50)
	private String studentContactId;

	public StudentContactIdentifier() {
	}

	public StudentContactIdentifier(String studentContactIdentifierRefId, Integer studentContactIdentifierSchoolYear, StudentContact studentContact, String identificationSystemCode, String studentContactId) {
		super();
		this.studentContactIdentifierRefId = studentContactIdentifierRefId;
		this.studentContactIdentifierSchoolYear = studentContactIdentifierSchoolYear;
		this.studentContact = studentContact;
		this.identificationSystemCode = identificationSystemCode;
		this.studentContactId = studentContactId;
	}

	public String getStudentContactIdentifierRefId() {
		return this.studentContactIdentifierRefId;
	}
	public void setStudentContactIdentifierRefId(String studentContactIdentifierRefId) {
		this.studentContactIdentifierRefId = studentContactIdentifierRefId;
	}
	
	public Integer getStudentContactIdentifierSchoolYear() {
		return studentContactIdentifierSchoolYear;
	}
	public void setStudentContactIdentifierSchoolYear(Integer studentContactIdentifierSchoolYear) {
		this.studentContactIdentifierSchoolYear = studentContactIdentifierSchoolYear;
	}

	public StudentContact getStudentContact() {
		return this.studentContact;
	}
	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
	}

	public String getIdentificationSystemCode() {
		return this.identificationSystemCode;
	}
	public void setIdentificationSystemCode(String identificationSystemCode) {
		this.identificationSystemCode = identificationSystemCode;
	}

	public String getStudentContactId() {
		return this.studentContactId;
	}
	public void setStudentContactId(String studentContactId) {
		this.studentContactId = studentContactId;
	}
}
