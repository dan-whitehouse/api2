package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentContactRelationshipComposite;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactrelationship")
@IdClass(StudentContactRelationshipComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentContactRelationship implements java.io.Serializable {
	private static final long serialVersionUID = 2782318822343339249L;

	@Column(name = "StudentContactRelationshipRefId", unique = true, nullable = false, length = 64)
	@Id
    private String studentContactRelationshipRefId;
	
	@Column(name = "StudentContactRelationshipSchoolYear", length = 6)
	@Id
    private Integer studentContactRelationshipSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentContactRefId", referencedColumnName="studentContactRefId", nullable = false),
		@JoinColumn(name="StudentContactSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
	})
	private StudentContact studentContact;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentRefId", referencedColumnName="studentRefId", nullable = false),
		@JoinColumn(name="StudentSchoolYear", referencedColumnName="studentSchoolYear", nullable = false)
	})
	private Student student;
	
	@Column(name = "RelationshipCode", length = 50)
	private String relationshipCode;
	
	@Column(name = "CustodialRelationshipIndicator")
	private Boolean custodialRelationshipIndicator;
	
	@Column(name = "EmergencyContactIndicator")
	private Boolean emergencyContactIndicator;
	
	@Column(name = "ContactPriorityNumber")
	private Integer contactPriorityNumber;
	
	@Column(name = "ContactRestrictions", length = 2000)
	private String contactRestrictions;
	
	@Column(name = "LivesWithIndicator")
	private Boolean livesWithIndicator;
	
	@Column(name = "PrimaryContactIndicator")
	private Boolean primaryContactIndicator;
	
	@Column(name = "FinancialResponsibilityIndicator")
	private Boolean financialResponsibilityIndicator;
	
	@Column(name = "CommunicationsIndicator")
	private Boolean communicationsIndicator;

	public StudentContactRelationship() {
	}

	public StudentContactRelationship(String studentContactRelationshipRefId, Integer studentContactRelationshipSchoolYear, StudentContact studentContact, Student student, String relationshipCode, Boolean custodialRelationshipIndicator, Boolean emergencyContactIndicator, Integer contactPriorityNumber, String contactRestrictions, Boolean livesWithIndicator, Boolean primaryContactIndicator, Boolean financialResponsibilityIndicator, Boolean communicationsIndicator) {
		super();
		this.studentContactRelationshipRefId = studentContactRelationshipRefId;
		this.studentContactRelationshipSchoolYear = studentContactRelationshipSchoolYear;
		this.studentContact = studentContact;
		this.student = student;
		this.relationshipCode = relationshipCode;
		this.custodialRelationshipIndicator = custodialRelationshipIndicator;
		this.emergencyContactIndicator = emergencyContactIndicator;
		this.contactPriorityNumber = contactPriorityNumber;
		this.contactRestrictions = contactRestrictions;
		this.livesWithIndicator = livesWithIndicator;
		this.primaryContactIndicator = primaryContactIndicator;
		this.financialResponsibilityIndicator = financialResponsibilityIndicator;
		this.communicationsIndicator = communicationsIndicator;
	}
	
	public String getStudentContactRelationshipRefId() {
		return this.studentContactRelationshipRefId;
	}
	public void setStudentContactRelationshipRefId(String studentContactRelationshipRefId) {
		this.studentContactRelationshipRefId = studentContactRelationshipRefId;
	}

	public Integer getStudentContactRelationshipSchoolYear() {
		return studentContactRelationshipSchoolYear;
	}
	public void setStudentContactRelationshipSchoolYear(Integer studentContactRelationshipSchoolYear) {
		this.studentContactRelationshipSchoolYear = studentContactRelationshipSchoolYear;
	}
	
	public StudentContact getStudentContact() {
		return this.studentContact;
	}
	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
	}

	public Student getStudent() {
		return this.student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public String getRelationshipCode() {
		return this.relationshipCode;
	}
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}

	public Boolean getCustodialRelationshipIndicator() {
		return this.custodialRelationshipIndicator;
	}
	public void setCustodialRelationshipIndicator(Boolean custodialRelationshipIndicator) {
		this.custodialRelationshipIndicator = custodialRelationshipIndicator;
	}

	public Boolean getEmergencyContactIndicator() {
		return this.emergencyContactIndicator;
	}
	public void setEmergencyContactIndicator(Boolean emergencyContactIndicator) {
		this.emergencyContactIndicator = emergencyContactIndicator;
	}

	public Integer getContactPriorityNumber() {
		return this.contactPriorityNumber;
	}
	public void setContactPriorityNumber(Integer contactPriorityNumber) {
		this.contactPriorityNumber = contactPriorityNumber;
	}

	public String getContactRestrictions() {
		return this.contactRestrictions;
	}
	public void setContactRestrictions(String contactRestrictions) {
		this.contactRestrictions = contactRestrictions;
	}

	public Boolean getLivesWithIndicator() {
		return this.livesWithIndicator;
	}
	public void setLivesWithIndicator(Boolean livesWithIndicator) {
		this.livesWithIndicator = livesWithIndicator;
	}
	
	public Boolean getPrimaryContactIndicator() {
		return this.primaryContactIndicator;
	}
	public void setPrimaryContactIndicator(Boolean primaryContactIndicator) {
		this.primaryContactIndicator = primaryContactIndicator;
	}

	public Boolean getFinancialResponsibilityIndicator() {
		return financialResponsibilityIndicator;
	}
	public void setFinancialResponsibilityIndicator(Boolean financialResponsibilityIndicator) {
		this.financialResponsibilityIndicator = financialResponsibilityIndicator;
	}
	
	public Boolean getCommunicationsIndicator() {
		return communicationsIndicator;
	}
	public void setCommunicationsIndicator(Boolean communicationsIndicator) {
		this.communicationsIndicator = communicationsIndicator;
	}
}
