package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentHealthComposite;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studenthealth")
@IdClass(StudentHealthComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentHealth implements java.io.Serializable {
    private static final long serialVersionUID = 6287570933551222011L;

    @Id
    @Column(name = "StudentHealthRefId", unique = true, nullable = false, length = 64)
    private String studentHealthRefId;

    @Id
    @Column(name = "StudentHealthSchoolYear", nullable = false, length = 6)
    private Integer studentHealthSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false) })
    private Student student;

    @Temporal(TemporalType.DATE)
    @Column(name = "VisionScreeningDate", length = 10)
    private Date visionScreeningDate;

    @Column(name = "VisionScreeningStatusCode", length = 50)
    private String visionScreeningStatusCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "HearingScreeningDate", length = 10)
    private Date hearingScreeningDate;

    @Column(name = "HearingScreeningStatusCode", length = 50)
    private String hearingScreeningStatusCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "DentalScreeningDate", length = 10)
    private Date dentalScreeningDate;

    @Column(name = "DentalScreeningStatusCode", length = 50)
    private String dentalScreeningStatusCode;

    @Column(name = "HealthInsuranceCoverageCode", length = 50)
    private String healthInsuranceCoverageCode;

    @Column(name = "DentalInsuranceCoverageTypeCode", length = 50)
    private String dentalInsuranceCoverageTypeCode;

    @Column(name = "MedicalAlertIndicatorCode", length = 50)
    private String medicalAlertIndicatorCode;

    @Column(name = "HealthScreeningEquipmentUsed", length = 300)
    private String healthScreeningEquipmentUsed;

    @Column(name = "HealthScreeningFollowUpRecommendation")
    private String healthScreeningFollowUpRecommendation;

    public StudentHealth() {
    }

    public StudentHealth(String studentHealthRefId, Integer studentHealthSchoolYear, Student student,
                         Date visionScreeningDate, String visionScreeningStatusCode, Date hearingScreeningDate,
                         String hearingScreeningStatusCode, Date dentalScreeningDate, String dentalScreeningStatusCode,
                         String healthInsuranceCoverageCode, String dentalInsuranceCoverageTypeCode,
                         String medicalAlertIndicatorCode, String healthScreeningEquipmentUsed,
                         String healthScreeningFollowUpRecommendation) {
	this.studentHealthRefId = studentHealthRefId;
	this.studentHealthSchoolYear = studentHealthSchoolYear;
	this.student = student;
	this.visionScreeningDate = visionScreeningDate;
	this.visionScreeningStatusCode = visionScreeningStatusCode;
	this.hearingScreeningDate = hearingScreeningDate;
	this.hearingScreeningStatusCode = hearingScreeningStatusCode;
	this.dentalScreeningDate = dentalScreeningDate;
	this.dentalScreeningStatusCode = dentalScreeningStatusCode;
	this.healthInsuranceCoverageCode = healthInsuranceCoverageCode;
	this.dentalInsuranceCoverageTypeCode = dentalInsuranceCoverageTypeCode;
	this.medicalAlertIndicatorCode = medicalAlertIndicatorCode;
	this.healthScreeningEquipmentUsed = healthScreeningEquipmentUsed;
	this.healthScreeningFollowUpRecommendation = healthScreeningFollowUpRecommendation;
    }

    public String getStudentHealthRefId() {
	return this.studentHealthRefId;
    }

    public void setStudentHealthRefId(String studentHealthRefId) {
	this.studentHealthRefId = studentHealthRefId;
    }

    public Integer getStudentHealthSchoolYear() {
	return studentHealthSchoolYear;
    }

    public void setStudentHealthSchoolYear(Integer studentHealthSchoolYear) {
	this.studentHealthSchoolYear = studentHealthSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public Date getVisionScreeningDate() {
	return this.visionScreeningDate;
    }

    public void setVisionScreeningDate(Date visionScreeningDate) {
	this.visionScreeningDate = visionScreeningDate;
    }

    public String getVisionScreeningStatusCode() {
	return this.visionScreeningStatusCode;
    }

    public void setVisionScreeningStatusCode(String visionScreeningStatusCode) {
	this.visionScreeningStatusCode = visionScreeningStatusCode;
    }

    public Date getHearingScreeningDate() {
	return this.hearingScreeningDate;
    }

    public void setHearingScreeningDate(Date hearingScreeningDate) {
	this.hearingScreeningDate = hearingScreeningDate;
    }

    public String getHearingScreeningStatusCode() {
	return this.hearingScreeningStatusCode;
    }

    public void setHearingScreeningStatusCode(String hearingScreeningStatusCode) {
	this.hearingScreeningStatusCode = hearingScreeningStatusCode;
    }

    public Date getDentalScreeningDate() {
	return this.dentalScreeningDate;
    }

    public void setDentalScreeningDate(Date dentalScreeningDate) {
	this.dentalScreeningDate = dentalScreeningDate;
    }

    public String getDentalScreeningStatusCode() {
	return this.dentalScreeningStatusCode;
    }

    public void setDentalScreeningStatusCode(String dentalScreeningStatusCode) {
	this.dentalScreeningStatusCode = dentalScreeningStatusCode;
    }

    public String getHealthInsuranceCoverageCode() {
	return this.healthInsuranceCoverageCode;
    }

    public void setHealthInsuranceCoverageCode(String healthInsuranceCoverageCode) {
	this.healthInsuranceCoverageCode = healthInsuranceCoverageCode;
    }

    public String getDentalInsuranceCoverageTypeCode() {
	return this.dentalInsuranceCoverageTypeCode;
    }

    public void setDentalInsuranceCoverageTypeCode(String dentalInsuranceCoverageTypeCode) {
	this.dentalInsuranceCoverageTypeCode = dentalInsuranceCoverageTypeCode;
    }

    public String getMedicalAlertIndicatorCode() {
	return this.medicalAlertIndicatorCode;
    }

    public void setMedicalAlertIndicatorCode(String medicalAlertIndicatorCode) {
	this.medicalAlertIndicatorCode = medicalAlertIndicatorCode;
    }

    public String getHealthScreeningEquipmentUsed() {
	return this.healthScreeningEquipmentUsed;
    }

    public void setHealthScreeningEquipmentUsed(String healthScreeningEquipmentUsed) {
	this.healthScreeningEquipmentUsed = healthScreeningEquipmentUsed;
    }

    public String getHealthScreeningFollowUpRecommendation() {
	return this.healthScreeningFollowUpRecommendation;
    }

    public void setHealthScreeningFollowUpRecommendation(String healthScreeningFollowUpRecommendation) {
	this.healthScreeningFollowUpRecommendation = healthScreeningFollowUpRecommendation;
    }

    @Override
    public String toString() {
	return "StudentHealth [studentHealthRefId=" + studentHealthRefId + ", studentHealthSchoolYear="
		+ studentHealthSchoolYear + ", student=" + student + ", visionScreeningDate=" + visionScreeningDate
		+ ", visionScreeningStatusCode=" + visionScreeningStatusCode + ", hearingScreeningDate="
		+ hearingScreeningDate + ", hearingScreeningStatusCode=" + hearingScreeningStatusCode
		+ ", dentalScreeningDate=" + dentalScreeningDate + ", dentalScreeningStatusCode="
		+ dentalScreeningStatusCode + ", healthInsuranceCoverageCode=" + healthInsuranceCoverageCode
		+ ", dentalInsuranceCoverageTypeCode=" + dentalInsuranceCoverageTypeCode
		+ ", medicalAlertIndicatorCode=" + medicalAlertIndicatorCode + ", healthScreeningEquipmentUsed="
		+ healthScreeningEquipmentUsed + ", healthScreeningFollowUpRecommendation="
		+ healthScreeningFollowUpRecommendation + "]";
    }

}
