package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.StudentComposite;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "student")
@IdClass(StudentComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements java.io.Serializable {
    private static final long serialVersionUID = -8205131726629327272L;

    @Id
    @Column(name = "StudentRefId", unique = true, nullable = false, length = 64)
    private String studentRefId;

    @Id
    @Column(name = "StudentSchoolYear", nullable = false, length = 6)
    private Integer studentSchoolYear;

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

    @Column(name = "Counselor", length = 50)
    private String counselor;

    @Column(name = "CohortGraduationYear", length = 4)
    private String cohortGraduationYear;

    @Column(name = "GradeLevelCode", length = 50)
    private String gradeLevelCode;

    @Column(name = "SLK")
    private Long slk;

    @Column(name = "Type", length = 32)
    private String type;

    @Column(name = "CountryOfBirth", length = 50)
    private String countryOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(name = "ProjectedGraduationDate", length = 10)
    private Date projectedGraduationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentRace> studentRaces = new HashSet<StudentRace>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentLanguage> studentLanguages = new HashSet<StudentLanguage>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentOtherName> studentOtherNames = new HashSet<StudentOtherName>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentContactRelationship> studentContactRelationships = new HashSet<StudentContactRelationship>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentAcademicRecord> studentAcademicRecords = new HashSet<StudentAcademicRecord>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentCourseSection> studentCourseSections = new HashSet<StudentCourseSection>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentIdentifier> studentIdentifiers = new HashSet<StudentIdentifier>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentHealth> studentHealths = new HashSet<StudentHealth>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentEnrollment> studentEnrollments = new HashSet<StudentEnrollment>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentTelephone> studentTelephones = new HashSet<StudentTelephone>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentEmail> studentEmails = new HashSet<StudentEmail>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    private Set<StudentAddress> studentAddresses = new HashSet<StudentAddress>(0);

    public Student() {
    }

    public Student(String studentRefId, Integer studentSchoolYear, String firstName, String middleName, String lastName,
                   String generationCode, String prefix, Date birthdate, String sexCode, Boolean hispanicLatinoEthnicity,
                   String usCitizenshipStatusCode, String counselor, String cohortGraduationYear, String gradeLevelCode,
                   Long slk, String type, String countryOfBirth, Date projectedGraduationDate, Set<StudentRace> studentRaces,
                   Set<StudentLanguage> studentLanguages, Set<StudentOtherName> studentOtherNames,
                   Set<StudentContactRelationship> studentContactRelationships,
                   Set<StudentAcademicRecord> studentAcademicRecords, Set<StudentCourseSection> studentCourseSections,
                   Set<StudentIdentifier> studentIdentifiers, Set<StudentHealth> studentHealths,
                   Set<StudentEnrollment> studentEnrollments, Set<StudentTelephone> studentTelephones,
                   Set<StudentEmail> studentEmails, Set<StudentAddress> studentAddresses) {
	this.studentRefId = studentRefId;
	this.studentSchoolYear = studentSchoolYear;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.generationCode = generationCode;
	this.prefix = prefix;
	this.birthdate = birthdate;
	this.sexCode = sexCode;
	this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
	this.usCitizenshipStatusCode = usCitizenshipStatusCode;
	this.counselor = counselor;
	this.cohortGraduationYear = cohortGraduationYear;
	this.gradeLevelCode = gradeLevelCode;
	this.slk = slk;
	this.type = type;
	this.countryOfBirth = countryOfBirth;
	this.projectedGraduationDate = projectedGraduationDate;
	this.studentRaces = studentRaces;
	this.studentLanguages = studentLanguages;
	this.studentOtherNames = studentOtherNames;
	this.studentContactRelationships = studentContactRelationships;
	this.studentAcademicRecords = studentAcademicRecords;
	this.studentCourseSections = studentCourseSections;
	this.studentIdentifiers = studentIdentifiers;
	this.studentHealths = studentHealths;
	this.studentEnrollments = studentEnrollments;
	this.studentTelephones = studentTelephones;
	this.studentEmails = studentEmails;
	this.studentAddresses = studentAddresses;
    }

    public String getStudentRefId() {
	return this.studentRefId;
    }

    public void setStudentRefId(String studentRefId) {
	this.studentRefId = studentRefId;
    }

    public Integer getStudentSchoolYear() {
	return studentSchoolYear;
    }

    public void setStudentSchoolYear(Integer studentSchoolYear) {
	this.studentSchoolYear = studentSchoolYear;
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

    public String getCounselor() {
	return this.counselor;
    }

    public void setCounselor(String counselor) {
	this.counselor = counselor;
    }

    public String getCohortGraduationYear() {
	return this.cohortGraduationYear;
    }

    public void setCohortGraduationYear(String cohortGraduationYear) {
	this.cohortGraduationYear = cohortGraduationYear;
    }

    public String getGradeLevelCode() {
	return this.gradeLevelCode;
    }

    public void setGradeLevelCode(String gradeLevelCode) {
	this.gradeLevelCode = gradeLevelCode;
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

    public String getCountryOfBirth() {
	return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
	this.countryOfBirth = countryOfBirth;
    }

    public Date getProjectedGraduationDate() {
	return this.projectedGraduationDate;
    }

    public void setProjectedGraduationDate(Date projectedGraduationDate) {
	this.projectedGraduationDate = projectedGraduationDate;
    }

    public Set<StudentRace> getStudentRaces() {
	return this.studentRaces;
    }

    public void setStudentRaces(Set<StudentRace> studentraces) {
	this.studentRaces = studentraces;
    }

    public Set<StudentLanguage> getStudentLanguages() {
	return this.studentLanguages;
    }

    public void setStudentLanguages(Set<StudentLanguage> studentlanguages) {
	this.studentLanguages = studentlanguages;
    }

    public Set<StudentOtherName> getStudentOtherNames() {
	return this.studentOtherNames;
    }

    public void setStudentOtherNames(Set<StudentOtherName> studentothernames) {
	this.studentOtherNames = studentothernames;
    }

    public Set<StudentContactRelationship> getStudentContactRelationships() {
	return this.studentContactRelationships;
    }

    public void setStudentContactRelationships(Set<StudentContactRelationship> studentcontactrelationships) {
	this.studentContactRelationships = studentcontactrelationships;
    }

    public Set<StudentAcademicRecord> getStudentAcademicRecords() {
	return this.studentAcademicRecords;
    }

    public void setStudentAcademicRecords(Set<StudentAcademicRecord> studentacademicrecords) {
	this.studentAcademicRecords = studentacademicrecords;
    }

    public Set<StudentCourseSection> getStudentCourseSections() {
	return this.studentCourseSections;
    }

    public void setStudentCourseSections(Set<StudentCourseSection> studentcoursesections) {
	this.studentCourseSections = studentcoursesections;
    }

    public Set<StudentIdentifier> getStudentIdentifiers() {
	return this.studentIdentifiers;
    }

    public void setStudentIdentifiers(Set<StudentIdentifier> studentidentifiers) {
	this.studentIdentifiers = studentidentifiers;
    }

    public Set<StudentHealth> getStudentHealths() {
	return this.studentHealths;
    }

    public void setStudentHealths(Set<StudentHealth> studenthealths) {
	this.studentHealths = studenthealths;
    }

    public Set<StudentEnrollment> getStudentEnrollments() {
	return this.studentEnrollments;
    }

    public void setStudentEnrollments(Set<StudentEnrollment> studentenrollments) {
	this.studentEnrollments = studentenrollments;
    }

    public Set<StudentTelephone> getStudentTelephones() {
	return this.studentTelephones;
    }

    public void setStudentTelephones(Set<StudentTelephone> studenttelephones) {
	this.studentTelephones = studenttelephones;
    }

    public Set<StudentEmail> getStudentEmails() {
	return this.studentEmails;
    }

    public void setStudentEmails(Set<StudentEmail> studentemails) {
	this.studentEmails = studentemails;
    }

    public Set<StudentAddress> getStudentAddresses() {
	return this.studentAddresses;
    }

    public void setStudentAddresses(Set<StudentAddress> studentaddresses) {
	this.studentAddresses = studentaddresses;
    }

    @Override
    public String toString() {
	return "Student [studentRefId=" + studentRefId + ", studentSchoolYear=" + studentSchoolYear + ", firstName="
		+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", generationCode="
		+ generationCode + ", prefix=" + prefix + ", birthdate=" + birthdate + ", sexCode=" + sexCode
		+ ", hispanicLatinoEthnicity=" + hispanicLatinoEthnicity + ", usCitizenshipStatusCode="
		+ usCitizenshipStatusCode + ", counselor=" + counselor + ", cohortGraduationYear="
		+ cohortGraduationYear + ", gradeLevelCode=" + gradeLevelCode + ", slk=" + slk + ", type=" + type
		+ ", countryOfBirth=" + countryOfBirth + ", projectedGraduationDate=" + projectedGraduationDate
		+ ", studentRaces=" + studentRaces + ", studentLanguages=" + studentLanguages + ", studentOtherNames="
		+ studentOtherNames + ", studentContactRelationships=" + studentContactRelationships
		+ ", studentAcademicRecords=" + studentAcademicRecords + ", studentCourseSections="
		+ studentCourseSections + ", studentIdentifiers=" + studentIdentifiers + ", studentHealths="
		+ studentHealths + ", studentEnrollments=" + studentEnrollments + ", studentTelephones="
		+ studentTelephones + ", studentEmails=" + studentEmails + ", studentAddresses=" + studentAddresses
		+ "]";
    }

}
