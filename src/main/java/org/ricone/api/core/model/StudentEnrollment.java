package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.StudentEnrollmentComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "studentenrollment")
@IdClass(StudentEnrollmentComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class StudentEnrollment implements java.io.Serializable {
    private static final long serialVersionUID = -7894349036867529032L;

    @Column(name = "StudentEnrollmentRefId", unique = true, nullable = false, length = 64)
    @Id private String studentEnrollmentRefId;

    @Column(name = "StudentEnrollmentSchoolYear", nullable = false, length = 6)
    @Id private Integer studentEnrollmentSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false)
	})
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "CounselorRefId", referencedColumnName = "staffRefId", nullable = false),
	    @JoinColumn(name = "CounselorSchoolYear", referencedColumnName = "staffSchoolYear", nullable = false)
    })
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "HomeRoomTeacherRefId", referencedColumnName = "staffRefId", nullable = false),
	    @JoinColumn(name = "HomeRoomTeacherSchoolYear", referencedColumnName = "staffSchoolYear", nullable = false)
	})
    private Staff teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "SchoolRefId", referencedColumnName = "schoolRefId", nullable = false),
	    @JoinColumn(name = "SchoolSchoolYear", referencedColumnName = "schoolSchoolYear", nullable = false)
	})
    private School school;

    @Column(name = "EntryGradeLevelCode", length = 50)
    private String entryGradeLevelCode;

    @Column(name = "EnrollmentStatusCode", length = 50)
    private String enrollmentStatusCode;

    @Column(name = "EntryTypeCode", length = 50)
    private String entryTypeCode;

    @Column(name = "ExitGradeLevelCode", length = 50)
    private String exitGradeLevelCode;

    @Column(name = "ExitOrWithdrawalStatusCode", length = 50)
    private String exitOrWithdrawalStatusCode;

    @Column(name = "ExitOrWithdrawalTypeCode", length = 50)
    private String exitOrWithdrawalTypeCode;

    @Column(name = "DisplacedStudentStatus")
    private Boolean displacedStudentStatus;

    @Column(name = "EndOfTermStatusCode", length = 50)
    private String endOfTermStatusCode;

    @Column(name = "PromotionReasonCode", length = 50)
    private String promotionReasonCode;

    @Column(name = "FoodServiceEligibilityCode", length = 50)
    private String foodServiceEligibilityCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "FirstEntryDateIntoUSSchool", length = 10)
    private Date firstEntryDateIntoUsschool;

    @Column(name = "HomeRoomIdentifier", length = 30)
    private String homeroomIdentifier;

    @Column(name = "ResponsibleSchoolTypeCode", length = 50)
    private String responsibleSchoolTypeCode;

    @Column(name = "MembershipTypeCode", length = 50)
    private String membershipTypeCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "EnrollmentEntryDate", length = 10)
    private Date enrollmentEntryDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EnrollmentExitDate", length = 10)
    private Date enrollmentExitDate;

    @Column(name = "CurrentGradeLevel", length = 50)
    private String currentGradeLevel;

    @Column(name = "StudentSchoolAssociationRefId", length = 64)
    private String studentSchoolAssociationRefId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentEnrollment")
    @Fetch(FetchMode.SELECT) @BatchSize(size = 100)
    private Set<EntryExitCode> entryExitCodes = new HashSet<>(0);

    public StudentEnrollment() {
    }

    public StudentEnrollment(String studentEnrollmentRefId, Integer studentEnrollmentSchoolYear, Student student,
                             Staff staff, Staff teacher, School school, String entryGradeLevelCode, String enrollmentStatusCode,
                             String entryTypeCode, String exitGradeLevelCode, String exitOrWithdrawalStatusCode,
                             String exitOrWithdrawalTypeCode, Boolean displacedStudentStatus, String endOfTermStatusCode,
                             String promotionReasonCode, String foodServiceEligibilityCode, Date firstEntryDateIntoUsschool,
                             String homeroomIdentifier, String responsibleSchoolTypeCode, String membershipTypeCode,
                             Date enrollmentEntryDate, Date enrollmentExitDate, String currentGradeLevel,
                             String studentSchoolAssociationRefId, Set<EntryExitCode> entryExitCodes) {
		super();
		this.studentEnrollmentRefId = studentEnrollmentRefId;
		this.studentEnrollmentSchoolYear = studentEnrollmentSchoolYear;
		this.student = student;
		this.staff = staff;
		this.teacher = teacher;
		this.school = school;
		this.entryGradeLevelCode = entryGradeLevelCode;
		this.enrollmentStatusCode = enrollmentStatusCode;
		this.entryTypeCode = entryTypeCode;
		this.exitGradeLevelCode = exitGradeLevelCode;
		this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
		this.exitOrWithdrawalTypeCode = exitOrWithdrawalTypeCode;
		this.displacedStudentStatus = displacedStudentStatus;
		this.endOfTermStatusCode = endOfTermStatusCode;
		this.promotionReasonCode = promotionReasonCode;
		this.foodServiceEligibilityCode = foodServiceEligibilityCode;
		this.firstEntryDateIntoUsschool = firstEntryDateIntoUsschool;
		this.homeroomIdentifier = homeroomIdentifier;
		this.responsibleSchoolTypeCode = responsibleSchoolTypeCode;
		this.membershipTypeCode = membershipTypeCode;
		this.enrollmentEntryDate = enrollmentEntryDate;
		this.enrollmentExitDate = enrollmentExitDate;
		this.currentGradeLevel = currentGradeLevel;
		this.studentSchoolAssociationRefId = studentSchoolAssociationRefId;
		this.entryExitCodes = entryExitCodes;
    }

    public String getStudentEnrollmentRefId() {
    	return this.studentEnrollmentRefId;
    }

    public void setStudentEnrollmentRefId(String studentEnrollmentRefId) {
    	this.studentEnrollmentRefId = studentEnrollmentRefId;
    }

    public Integer getStudentEnrollmentSchoolYear() {
    	return studentEnrollmentSchoolYear;
    }

    public void setStudentEnrollmentSchoolYear(Integer studentEnrollmentSchoolYear) {
    	this.studentEnrollmentSchoolYear = studentEnrollmentSchoolYear;
    }

    public Student getStudent() {
    	return this.student;
    }

    public void setStudent(Student student) {
    	this.student = student;
    }

    public Staff getStaff() {
    	return this.staff;
    }

    public void setStaff(Staff staff) {
    	this.staff = staff;
    }

    public Staff getTeacher() {
    	return teacher;
    }

    public void setTeacher(Staff teacher) {
    	this.teacher = teacher;
    }

    public School getSchool() {
    	return this.school;
    }

    public void setSchool(School school) {
    	this.school = school;
    }

    public String getEntryGradeLevelCode() {
    	return this.entryGradeLevelCode;
    }

    public void setEntryGradeLevelCode(String entryGradeLevelCode) {
    	this.entryGradeLevelCode = entryGradeLevelCode;
    }

    public String getEnrollmentStatusCode() {
    	return this.enrollmentStatusCode;
    }

    public void setEnrollmentStatusCode(String enrollmentStatusCode) {
    	this.enrollmentStatusCode = enrollmentStatusCode;
    }

    public String getEntryTypeCode() {
    	return this.entryTypeCode;
    }

    public void setEntryTypeCode(String entryTypeCode) {
    	this.entryTypeCode = entryTypeCode;
    }

    public String getExitGradeLevelCode() {
    	return this.exitGradeLevelCode;
    }

    public void setExitGradeLevelCode(String exitGradeLevelCode) {
    	this.exitGradeLevelCode = exitGradeLevelCode;
    }

    public String getExitOrWithdrawalStatusCode() {
    	return this.exitOrWithdrawalStatusCode;
    }

    public void setExitOrWithdrawalStatusCode(String exitOrWithdrawalStatusCode) {
    	this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
    }

    public String getExitOrWithdrawalTypeCode() {
    	return this.exitOrWithdrawalTypeCode;
    }

    public void setExitOrWithdrawalTypeCode(String exitOrWithdrawalTypeCode) {
    	this.exitOrWithdrawalTypeCode = exitOrWithdrawalTypeCode;
    }

    public Boolean getDisplacedStudentStatus() {
    	return this.displacedStudentStatus;
    }

    public void setDisplacedStudentStatus(Boolean displacedStudentStatus) {
    	this.displacedStudentStatus = displacedStudentStatus;
    }

    public String getEndOfTermStatusCode() {
    	return this.endOfTermStatusCode;
    }

    public void setEndOfTermStatusCode(String endOfTermStatusCode) {
    	this.endOfTermStatusCode = endOfTermStatusCode;
    }

    public String getPromotionReasonCode() {
    	return this.promotionReasonCode;
    }

    public void setPromotionReasonCode(String promotionReasonCode) {
    	this.promotionReasonCode = promotionReasonCode;
    }

    public String getFoodServiceEligibilityCode() {
    	return this.foodServiceEligibilityCode;
    }

    public void setFoodServiceEligibilityCode(String foodServiceEligibilityCode) {
    	this.foodServiceEligibilityCode = foodServiceEligibilityCode;
    }

    public Date getFirstEntryDateIntoUsschool() {
    	return this.firstEntryDateIntoUsschool;
    }

    public void setFirstEntryDateIntoUsschool(Date firstEntryDateIntoUsschool) {
    	this.firstEntryDateIntoUsschool = firstEntryDateIntoUsschool;
    }

    public String getHomeroomIdentifier() {
    	return this.homeroomIdentifier;
    }

    public void setHomeroomIdentifier(String homeRoomIdentifier) {
    	this.homeroomIdentifier = homeRoomIdentifier;
    }

    public String getResponsibleSchoolTypeCode() {
    	return this.responsibleSchoolTypeCode;
    }

    public void setResponsibleSchoolTypeCode(String responsibleSchoolTypeCode) {
    	this.responsibleSchoolTypeCode = responsibleSchoolTypeCode;
    }

    public String getMembershipTypeCode() {
    	return this.membershipTypeCode;
    }

    public void setMembershipTypeCode(String membershipTypeCode) {
    	this.membershipTypeCode = membershipTypeCode;
    }

    public Date getEnrollmentEntryDate() {
    	return this.enrollmentEntryDate;
    }

    public void setEnrollmentEntryDate(Date enrollmentEntryDate) {
    	this.enrollmentEntryDate = enrollmentEntryDate;
    }

    public Date getEnrollmentExitDate() {
    	return this.enrollmentExitDate;
    }

    public void setEnrollmentExitDate(Date enrollmentExitDate) {
    	this.enrollmentExitDate = enrollmentExitDate;
    }

    public String getCurrentGradeLevel() {
    	return this.currentGradeLevel;
    }

    public void setCurrentGradeLevel(String currentGradeLevel) {
    	this.currentGradeLevel = currentGradeLevel;
    }

    public String getStudentSchoolAssociationRefId() {
    	return studentSchoolAssociationRefId;
    }

    public void setStudentSchoolAssociationRefId(String studentSchoolAssociationRefId) {
    	this.studentSchoolAssociationRefId = studentSchoolAssociationRefId;
    }

    public Set<EntryExitCode> getEntryExitCodes() {
    	return entryExitCodes;
    }

    public void setEntryExitCodes(Set<EntryExitCode> entryExitCodes) {
    	this.entryExitCodes = entryExitCodes;
    }

    @Override
    public String toString() {
	return "StudentEnrollment [studentEnrollmentRefId=" + studentEnrollmentRefId + ", studentEnrollmentSchoolYear="
		+ studentEnrollmentSchoolYear + ", student=" + student + ", staff=" + staff + ", teacher=" + teacher
		+ ", school=" + school + ", entryGradeLevelCode=" + entryGradeLevelCode + ", enrollmentStatusCode="
		+ enrollmentStatusCode + ", entryTypeCode=" + entryTypeCode + ", exitGradeLevelCode="
		+ exitGradeLevelCode + ", exitOrWithdrawalStatusCode=" + exitOrWithdrawalStatusCode
		+ ", exitOrWithdrawalTypeCode=" + exitOrWithdrawalTypeCode + ", displacedStudentStatus="
		+ displacedStudentStatus + ", endOfTermStatusCode=" + endOfTermStatusCode + ", promotionReasonCode="
		+ promotionReasonCode + ", foodServiceEligibilityCode=" + foodServiceEligibilityCode
		+ ", firstEntryDateIntoUsschool=" + firstEntryDateIntoUsschool + ", homeroomIdentifier="
		+ homeroomIdentifier + ", responsibleSchoolTypeCode=" + responsibleSchoolTypeCode
		+ ", membershipTypeCode=" + membershipTypeCode + ", enrollmentEntryDate=" + enrollmentEntryDate
		+ ", enrollmentExitDate=" + enrollmentExitDate + ", currentGradeLevel=" + currentGradeLevel
		+ ", studentSchoolAssociationRefId=" + studentSchoolAssociationRefId + ", entryExitCodes="
		+ entryExitCodes + "]";
    }
    
}
