package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.StaffComposite;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "staff")
@IdClass(StaffComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class Staff implements Serializable {
	private static final long serialVersionUID = 1919054468978898526L;
	
	@Column(name = "StaffRefId", unique = true, nullable = false, length = 64)
	@Id private String staffRefId;
	
	@Column(name = "StaffSchoolYear", nullable = false, length = 6)
	@Id private Integer staffSchoolYear;
	
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Birthdate", length = 10)
	private Date birthdate;
	
	@Column(name = "SexCode", length = 50)
	private String sexCode;
	
	@Column(name = "HispanicLatinoEthnicity")
	private Boolean hispanicLatinoEthnicity;
	
	@Column(name = "USCitizenshipStatusCode", length = 50)
	private String usCitizenshipStatusCode;
	
	@Column(name = "SLK")
	private Long slk;
	
	@Column(name = "Type", length = 32)
	private String type;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StaffCourseSection> staffCourseSections = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staff", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StaffIdentifier> staffIdentifiers = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StudentEnrollment> studentEnrollments = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StaffEmail> staffEmails = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<StaffAssignment> staffAssignments = new HashSet<>(0);

	public Staff() {
	}

	public Staff(String staffRefId, Integer staffSchoolYear, String firstName, String middleName, String lastName, String generationCode, String prefix, Date birthdate, String sexCode, Boolean hispanicLatinoEthnicity, String usCitizenshipStatusCode, Long slk, String type, Set<StaffCourseSection> staffCourseSection, Set<StaffIdentifier> staffIdentifiers, Set<StudentEnrollment> studentEnrollments, Set<StaffEmail> staffEmails, Set<StaffAssignment> staffAssignments) {
		super();
		this.staffRefId = staffRefId;
		this.staffSchoolYear = staffSchoolYear;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.generationCode = generationCode;
		this.prefix = prefix;
		this.birthdate = birthdate;
		this.sexCode = sexCode;
		this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
		this.usCitizenshipStatusCode = usCitizenshipStatusCode;
		this.slk = slk;
		this.type = type;
		this.staffCourseSections = staffCourseSections;
		this.staffIdentifiers = staffIdentifiers;
		this.studentEnrollments = studentEnrollments;
		this.staffEmails = staffEmails;
		this.staffAssignments = staffAssignments;
	}

	public String getStaffRefId() {
		return this.staffRefId;
	}
	public void setStaffRefId(String staffRefId) {
		this.staffRefId = staffRefId;
	}

	public Integer getStaffSchoolYear() {
		return staffSchoolYear;
	}
	public void setStaffSchoolYear(Integer staffSchoolYear) {
		this.staffSchoolYear = staffSchoolYear;
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

	public Date getBirthdate() {
		return this.birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getSexCode() {
		return this.sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	
	public Boolean getHispanicLatinoEthnicity() {
		return this.hispanicLatinoEthnicity;
	}
	public void setHispanicLatinoEthnicity(Boolean hispanicLatinoEthnicity) {
		this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
	}

	public String getUsCitizenshipStatusCode() {
		return this.usCitizenshipStatusCode;
	}
	public void setUsCitizenshipStatusCode(String uscitizenshipStatusCode) {
		this.usCitizenshipStatusCode = uscitizenshipStatusCode;
	}

	public Long getSlk() {
		return this.slk;
	}
	public void setSlk(Long slk) {
		this.slk = slk;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Set<StaffCourseSection> getStaffCourseSections() {
		return this.staffCourseSections;
	}
	public void setStaffCourseSections(Set<StaffCourseSection> staffcoursesections) {
		this.staffCourseSections = staffcoursesections;
	}

	public Set<StaffIdentifier> getStaffIdentifiers() {
		return this.staffIdentifiers;
	}
	public void setStaffIdentifiers(Set<StaffIdentifier> staffidentifiers) {
		this.staffIdentifiers = staffidentifiers;
	}

	public Set<StudentEnrollment> getStudentEnrollments() {
		return this.studentEnrollments;
	}
	public void setStudentEnrollments(Set<StudentEnrollment> studentenrollments) {
		this.studentEnrollments = studentenrollments;
	}

	public Set<StaffEmail> getStaffEmails() {
		return this.staffEmails;
	}
	public void setStaffEmails(Set<StaffEmail> staffemails) {
		this.staffEmails = staffemails;
	}

	public Set<StaffAssignment> getStaffAssignments() {
		return this.staffAssignments;
	}
	public void setStaffAssignments(Set<StaffAssignment> staffassignments) {
		this.staffAssignments = staffassignments;
	}

	@Override
	public String toString() {
		return "Staff [staffRefId=" + staffRefId + ", staffSchoolYear=" + staffSchoolYear + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", generationCode=" + generationCode + ", prefix=" + prefix + ", birthdate=" + birthdate + ", sexCode=" + sexCode + ", hispanicLatinoEthnicity=" + hispanicLatinoEthnicity + ", usCitizenshipStatusCode=" + usCitizenshipStatusCode + ", slk=" + slk + ", type=" + type + ", staffCourseSection=" + staffCourseSections + ", staffIdentifiers=" + staffIdentifiers + ", studentEnrollments=" + studentEnrollments + ", staffEmails=" + staffEmails + ", staffAssignments=" + staffAssignments + "]";
	}
}
