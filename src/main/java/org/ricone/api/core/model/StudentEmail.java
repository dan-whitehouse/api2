package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentEmailComposite;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentemail")
@IdClass(StudentEmailComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentEmail implements Serializable {
    private static final long serialVersionUID = 3121641356609913131L;

    @Column(name = "StudentEmailRefId", unique = true, nullable = false, length = 64)
    @Id private String studentEmailRefId;

    @Column(name = "StudentEmailSchoolYear", nullable = false, length = 6)
    @Id private Integer studentEmailSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false)
	})
    private Student student;

    @Column(name = "EmailAddress", length = 128)
    private String emailAddress;

    @Column(name = "EmailTypeCode", length = 50)
    private String emailTypeCode;

    @Column(name = "PrimaryEmailAddressIndicator")
    private Boolean primaryEmailAddressIndicator;

    public StudentEmail() {
    }

    public StudentEmail(String studentEmailRefId, Integer studentEmailSchoolYear, Student student, String emailAddress, String emailTypeCode, Boolean primaryEmailAddressIndicator) {
		this.studentEmailRefId = studentEmailRefId;
		this.studentEmailSchoolYear = studentEmailSchoolYear;
		this.student = student;
		this.emailAddress = emailAddress;
		this.emailTypeCode = emailTypeCode;
		this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
    }

    public String getStudentEmailRefId() {
    	return this.studentEmailRefId;
    }

    public void setStudentEmailRefId(String studentEmailRefId) {
    	this.studentEmailRefId = studentEmailRefId;
    }

    public Integer getStudentEmailSchoolYear() {
    	return studentEmailSchoolYear;
    }

    public void setStudentEmailSchoolYear(Integer studentEmailSchoolYear) {
    	this.studentEmailSchoolYear = studentEmailSchoolYear;
    }

    public Student getStudent() {
    	return this.student;
    }

    public void setStudent(Student student) {
    	this.student = student;
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

    @Override
    public String toString() {
	return "StudentEmail [studentEmailRefId=" + studentEmailRefId + ", studentEmailSchoolYear="
		+ studentEmailSchoolYear + ", student=" + student + ", emailAddress=" + emailAddress
		+ ", emailTypeCode=" + emailTypeCode + ", primaryEmailAddressIndicator=" + primaryEmailAddressIndicator
		+ "]";
    }

}
