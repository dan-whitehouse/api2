package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentTelephoneComposite;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studenttelephone")
@IdClass(StudentTelephoneComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentTelephone implements Serializable {
    private static final long serialVersionUID = 2259094736201878864L;

    @Column(name = "StudentPhoneRefId", unique = true, nullable = false, length = 64)
    @Id private String studentPhoneRefId;

    @Column(name = "StudentPhoneSchoolYear", nullable = false, length = 6)
    @Id private Integer studentPhoneSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false) })
    private Student student;

    @Column(name = "TelephoneNumber", length = 24)
    private String telephoneNumber;

    @Column(name = "PrimaryTelephoneNumberIndicator")
    private Boolean primaryTelephoneNumberIndicator;

    @Column(name = "TelephoneNumberTypeCode", length = 50)
    private String telephoneNumberTypeCode;

    public StudentTelephone() {
    }

    public StudentTelephone(String studentPhoneRefId, Integer studentPhoneSchoolYear, Student student,
                            String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode) {
	this.studentPhoneRefId = studentPhoneRefId;
	this.studentPhoneSchoolYear = studentPhoneSchoolYear;
	this.student = student;
	this.telephoneNumber = telephoneNumber;
	this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
	this.telephoneNumberTypeCode = telephoneNumberTypeCode;
    }

    public String getStudentPhoneRefId() {
	return this.studentPhoneRefId;
    }

    public void setStudentPhoneRefId(String studentPhoneRefId) {
	this.studentPhoneRefId = studentPhoneRefId;
    }

    public Integer getStudentPhoneSchoolYear() {
        return studentPhoneSchoolYear;
    }

    public void setStudentPhoneSchoolYear(Integer studentPhoneSchoolYear) {
        this.studentPhoneSchoolYear = studentPhoneSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
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

    @Override
    public String toString() {
	return "StudentTelephone [studentPhoneRefId=" + studentPhoneRefId + ", studentPhoneSchoolYear="
		+ studentPhoneSchoolYear + ", student=" + student + ", telephoneNumber=" + telephoneNumber
		+ ", primaryTelephoneNumberIndicator=" + primaryTelephoneNumberIndicator + ", telephoneNumberTypeCode="
		+ telephoneNumberTypeCode + "]";
    }

}
