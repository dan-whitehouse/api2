package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.StudentContactComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "studentcontact")
@IdClass(StudentContactComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class StudentContact implements Serializable {
	private static final long serialVersionUID = -1949488111557559072L;
	
	@Column(name = "StudentContactRefId", unique = true, nullable = false, length = 64)
	@Id private String studentContactRefId;
	
	@Column(name = "StudentContactSchoolYear", nullable = false, length = 6)
	@Id private Integer studentContactSchoolYear;
	
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
	
	@Column(name = "SexCode", length = 50)
	private String sexCode;
	
	@Column(name = "LanguageCode", length = 50)
	private String languageCode;
	
	@Column(name = "LanguageUseTypeCode", length = 50)
	private String languageUseTypeCode;
	
	@Column(name = "Type", length = 32)
	private String type;
	
	@Column(name = "EmployerType", length = 32)
	private String employerType;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentContactRelationship> studentContactRelationships = new HashSet<StudentContactRelationship>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentContactEmail> studentContactEmails = new HashSet<StudentContactEmail>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentContactAddress> studentContactAddresses = new HashSet<StudentContactAddress>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentContactTelephone> studentContactTelephones = new HashSet<StudentContactTelephone>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentContactIdentifier> studentContactIdentifiers = new HashSet<StudentContactIdentifier>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentContactOtherName> studentContactOtherNames = new HashSet<StudentContactOtherName>(0);

	public StudentContact() {
	}

	public StudentContact(String studentContactRefId, Integer studentContactSchoolYear, String firstName, String middleName, String lastName, String generationCode, String prefix, String sexCode, String languageCode, String languageUseTypeCode, String type, String employerType, Set<StudentContactRelationship> studentContactRelationships, Set<StudentContactEmail> studentContactEmails, Set<StudentContactAddress> studentContactAddresses, Set<StudentContactTelephone> studentContactTelephones, Set<StudentContactIdentifier> studentContactIdentifiers, Set<StudentContactOtherName> studentContactOtherNames) {
		super();
		this.studentContactRefId = studentContactRefId;
		this.studentContactSchoolYear = studentContactSchoolYear;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.generationCode = generationCode;
		this.prefix = prefix;
		this.sexCode = sexCode;
		this.languageCode = languageCode;
		this.languageUseTypeCode = languageUseTypeCode;
		this.type = type;
		this.employerType = employerType;
		this.studentContactRelationships = studentContactRelationships;
		this.studentContactEmails = studentContactEmails;
		this.studentContactAddresses = studentContactAddresses;
		this.studentContactTelephones = studentContactTelephones;
		this.studentContactIdentifiers = studentContactIdentifiers;
		this.studentContactOtherNames = studentContactOtherNames;
	}

	public String getStudentContactRefId() {
		return this.studentContactRefId;
	}
	public void setStudentContactRefId(String studentContactRefId) {
		this.studentContactRefId = studentContactRefId;
	}
	
	public Integer getStudentContactSchoolYear() {
		return studentContactSchoolYear;
	}
	public void setStudentContactSchoolYear(Integer studentContactSchoolYear) {
		this.studentContactSchoolYear = studentContactSchoolYear;
	}

	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGenerationCode() {
		return this.generationCode;
	}
	public void setGenerationCode(String generationCode) {
		this.generationCode = generationCode;
	}

	public String getPrefix() {
		return this.prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSexCode() {
		return this.sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageUseTypeCode() {
		return this.languageUseTypeCode;
	}
	public void setLanguageUseTypeCode(String languageUseTypeCode) {
		this.languageUseTypeCode = languageUseTypeCode;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getEmployerType() {
		return employerType;
	}
	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}

	public Set<StudentContactRelationship> getStudentContactRelationships() {
		return this.studentContactRelationships;
	}
	public void setStudentContactRelationships(Set<StudentContactRelationship> studentcontactrelationships) {
		this.studentContactRelationships = studentcontactrelationships;
	}

	public Set<StudentContactEmail> getStudentContactEmails() {
		return this.studentContactEmails;
	}
	public void setStudentContactEmails(Set<StudentContactEmail> studentcontactemails) {
		this.studentContactEmails = studentcontactemails;
	}

	public Set<StudentContactAddress> getStudentContactAddresses() {
		return this.studentContactAddresses;
	}
	public void setStudentContactAddresses(Set<StudentContactAddress> studentcontactaddresses) {
		this.studentContactAddresses = studentcontactaddresses;
	}

	public Set<StudentContactTelephone> getStudentContactTelephones() {
		return this.studentContactTelephones;
	}
	public void setStudentContactTelephones(Set<StudentContactTelephone> studentcontacttelephones) {
		this.studentContactTelephones = studentcontacttelephones;
	}

	public Set<StudentContactIdentifier> getStudentContactIdentifiers() {
		return this.studentContactIdentifiers;
	}
	public void setStudentContactIdentifiers(Set<StudentContactIdentifier> studentcontactidentifiers) {
		this.studentContactIdentifiers = studentcontactidentifiers;
	}

	public Set<StudentContactOtherName> getStudentContactOtherNames() {
		return studentContactOtherNames;
	}
	public void setStudentContactOtherNames(Set<StudentContactOtherName> studentContactOtherNames) {
		this.studentContactOtherNames = studentContactOtherNames;
	}

	@Override
	public String toString() {
		return "StudentContact{" + "studentContactRefId='" + studentContactRefId + '\'' + ", studentContactSchoolYear=" + studentContactSchoolYear + '}';
	}
}
