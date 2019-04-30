package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentLanguageComposite;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentlanguage")
@IdClass(StudentLanguageComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentLanguage implements Serializable {
    private static final long serialVersionUID = -5261716524080403446L;

    @Column(name = "StudentLanguageRefId", unique = true, nullable = false, length = 64)
    @Id private String studentLanguageRefId;

    @Column(name = "StudentLanguageSchoolYear", nullable = false, length = 6)
    @Id private Integer studentLanguageSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false) })
    private Student student;

    @Column(name = "LanguageCode", length = 50)
    private String languageCode;

    @Column(name = "LanguageUseTypeCode", length = 50)
    private String languageUseTypeCode;

    public StudentLanguage() {
    }

    public StudentLanguage(String studentLanguageRefId, Integer studentLanguageSchoolYear, Student student,
                           String languageCode, String languageUseTypeCode) {
	this.studentLanguageRefId = studentLanguageRefId;
	this.studentLanguageSchoolYear = studentLanguageSchoolYear;
	this.student = student;
	this.languageCode = languageCode;
	this.languageUseTypeCode = languageUseTypeCode;
    }

    public String getStudentLanguageRefId() {
	return this.studentLanguageRefId;
    }

    public void setStudentLanguageRefId(String studentLanguageRefId) {
	this.studentLanguageRefId = studentLanguageRefId;
    }

    public Integer getStudentLanguageSchoolYear() {
        return studentLanguageSchoolYear;
    }

    public void setStudentLanguageSchoolYear(Integer studentLanguageSchoolYear) {
        this.studentLanguageSchoolYear = studentLanguageSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
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

    @Override
    public String toString() {
	return "StudentLanguage [studentLanguageRefId=" + studentLanguageRefId + ", studentLanguageSchoolYear="
		+ studentLanguageSchoolYear + ", student=" + student + ", languageCode=" + languageCode
		+ ", languageUseTypeCode=" + languageUseTypeCode + "]";
    }

}
