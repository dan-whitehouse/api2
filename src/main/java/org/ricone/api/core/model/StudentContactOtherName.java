package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentContactOtherNameComposite;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentcontactothername")
@IdClass(StudentContactOtherNameComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentContactOtherName implements Serializable {
	private static final long serialVersionUID = -3879579464081349767L;
	
	@Column(name = "StudentContactOtherNameRefId", unique = true, nullable = false, length = 64)
	@Id private String studentContactOtherNameRefId;
	
	@Column(name = "StudentContactOtherNameSchoolYear", length = 6)
	@Id private Integer studentContactOtherNameSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentContactRefId", referencedColumnName="studentContactRefId", nullable = false),
		@JoinColumn(name="StudentContactSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
	})
	private StudentContact studentContact;
	
	@Column(name = "FirstName", length = 35)
	private String firstName;
	
	@Column(name = "MiddleName", length = 35)
	private String middleName;
	
	@Column(name = "LastName", length = 35)
	private String lastName;
	
	@Column(name = "GenerationCode", length = 10)
	private String generationCode;
	
	@Column(name = "Prefix", length = 30)
	private String prefix;
	
	@Column(name = "Type", length = 32)
	private String type;

	public StudentContactOtherName() {
	}

	public StudentContactOtherName(String studentContactOtherNameRefId, Integer studentContactOtherNameSchoolYear, StudentContact studentContact, String firstName, String middleName, String lastName, String generationCode, String prefix, String type) {
		super();
		this.studentContactOtherNameRefId = studentContactOtherNameRefId;
		this.studentContactOtherNameSchoolYear = studentContactOtherNameSchoolYear;
		this.studentContact = studentContact;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.generationCode = generationCode;
		this.prefix = prefix;
		this.type = type;
	}
	
	public String getStudentContactOtherNameRefId() {
		return studentContactOtherNameRefId;
	}
	public void setStudentContactOtherNameRefId(String studentContactOtherNameRefId) {
		this.studentContactOtherNameRefId = studentContactOtherNameRefId;
	}
	
	public Integer getStudentContactOtherNameSchoolYear() {
		return studentContactOtherNameSchoolYear;
	}
	public void setStudentContactOtherNameSchoolYear(Integer studentContactOtherNameSchoolYear) {
		this.studentContactOtherNameSchoolYear = studentContactOtherNameSchoolYear;
	}

	public StudentContact getStudentContact() {
		return studentContact;
	}
	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGenerationCode() {
		return generationCode;
	}
	public void setGenerationCode(String generationCode) {
		this.generationCode = generationCode;
	}

	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
