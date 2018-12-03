package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentOtherNameComposite;

import javax.persistence.*;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentothername")
@IdClass(StudentOtherNameComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentOtherName implements java.io.Serializable {
    private static final long serialVersionUID = -2946697742300249084L;

    @Id
    @Column(name = "StudentOtherNameRefId", unique = true, nullable = false, length = 64)
    private String studentOtherNameRefId;

    @Id
    @Column(name = "StudentOtherNameSchoolYear", nullable = false, length = 6)
    private Integer studentOtherNameSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false) })
    private Student student;

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

    @Column(name = "Type", length = 50)
    private String type;

    public StudentOtherName() {
    }

    public StudentOtherName(String studentOtherNameRefId, Integer studentOtherNameSchoolYear, Student student,
                            String firstName, String middleName, String lastName, String generationCode, String prefix, String type) {
	this.studentOtherNameRefId = studentOtherNameRefId;
	this.studentOtherNameSchoolYear = studentOtherNameSchoolYear;
	this.student = student;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.generationCode = generationCode;
	this.prefix = prefix;
	this.type = type;
    }

    public String getStudentOtherNameRefId() {
	return this.studentOtherNameRefId;
    }

    public void setStudentOtherNameRefId(String studentOtherNameRefId) {
	this.studentOtherNameRefId = studentOtherNameRefId;
    }

    public Integer getStudentOtherNameSchoolYear() {
	return studentOtherNameSchoolYear;
    }

    public void setStudentOtherNameSchoolYear(Integer studentOtherNameSchoolYear) {
	this.studentOtherNameSchoolYear = studentOtherNameSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
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

    public String getType() {
	return this.type;
    }

    public void setType(String type) {
	this.type = type;
    }

}
