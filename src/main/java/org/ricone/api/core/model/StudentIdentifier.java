package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentIdentifierComposite;

import javax.persistence.*;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentidentifier")
@IdClass(StudentIdentifierComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentIdentifier implements java.io.Serializable {
    private static final long serialVersionUID = -7539817796906734793L;
	
    @Column(name = "StudentIdentifierRefId", unique = true, nullable = false, length = 64)
    @Id
    private String studentIdentifierRefId;
    
    @Column(name = "StudentIdentifierSchoolYear", nullable = false, length = 6)
    @Id
    private Integer studentIdentifierSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false)
	})
    private Student student;

    @Column(name = "IdentificationSystemCode", length = 50)
    private String identificationSystemCode;

    @Column(name = "StudentId", length = 50)
    private String studentId;

    public StudentIdentifier() {
    }

    public StudentIdentifier(String studentIdentifierRefId, Integer studentIdentifierSchoolYear, Student student,
                             String identificationSystemCode, String studentId) {
	this.studentIdentifierRefId = studentIdentifierRefId;
	this.studentIdentifierSchoolYear = studentIdentifierSchoolYear;
	this.student = student;
	this.identificationSystemCode = identificationSystemCode;
	this.studentId = studentId;
    }

    public String getStudentIdentifierRefId() {
	return this.studentIdentifierRefId;
    }

    public void setStudentIdentifierRefId(String studentIdentifierRefId) {
	this.studentIdentifierRefId = studentIdentifierRefId;
    }

    public Integer getStudentIdentifierSchoolYear() {
	return studentIdentifierSchoolYear;
    }

    public void setStudentIdentifierSchoolYear(Integer studentIdentifierSchoolYear) {
	this.studentIdentifierSchoolYear = studentIdentifierSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public String getIdentificationSystemCode() {
	return this.identificationSystemCode;
    }

    public void setIdentificationSystemCode(String identificationSystemCode) {
	this.identificationSystemCode = identificationSystemCode;
    }

    public String getStudentId() {
	return this.studentId;
    }

    public void setStudentId(String studentId) {
	this.studentId = studentId;
    }

    @Override
    public String toString() {
	return "StudentIdentifier [studentIdentifierRefId=" + studentIdentifierRefId + ", studentIdentifierSchoolYear="
		+ studentIdentifierSchoolYear + ", student=" + student + ", identificationSystemCode="
		+ identificationSystemCode + ", studentId=" + studentId + "]";
    }

}
