package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentAcademicRecordComposite;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentacademicrecord")
@IdClass(StudentAcademicRecordComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentAcademicRecord implements java.io.Serializable {
    private static final long serialVersionUID = 6120294771566865543L;
           
    @Column(name = "StudentAcademicRecordRefId", unique = true, nullable = false, length = 50)
    @Id
    private String studentAcademicRecordRefId;
			
    @Column(name = "StudentAcademicRecordSchoolYear", nullable = false, length = 6)
    @Id
    private Integer studentAcademicRecordSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false)
    })
    private Student student;

    @Temporal(TemporalType.DATE)
    @Column(name = "AsOfDate", length = 10)
    private Date asOfDate;

    @Column(name = "GradePointAverageCumulative", precision = 9, scale = 4)
    private BigDecimal gradePointAverageCumulative;

    @Column(name = "GradePointAverageGivenSession", precision = 9, scale = 4)
    private BigDecimal gradePointAverageGivenSession;

    @Column(name = "HighSchoolStudentClassRank")
    private Integer highSchoolStudentClassRank;

    public StudentAcademicRecord() {
    }

    public StudentAcademicRecord(String studentAcademicRecordRefId, Integer studentAcademicRecordSchoolYear,
                                 Student student, Date asOfDate, BigDecimal gradePointAverageCumulative,
                                 BigDecimal gradePointAverageGivenSession, Integer highSchoolStudentClassRank) {
	this.studentAcademicRecordRefId = studentAcademicRecordRefId;
	this.studentAcademicRecordSchoolYear = studentAcademicRecordSchoolYear;
	this.student = student;
	this.asOfDate = asOfDate;
	this.gradePointAverageCumulative = gradePointAverageCumulative;
	this.gradePointAverageGivenSession = gradePointAverageGivenSession;
	this.highSchoolStudentClassRank = highSchoolStudentClassRank;
    }

    public String getStudentAcademicRecordRefId() {
	return this.studentAcademicRecordRefId;
    }

    public void setStudentAcademicRecordRefId(String studentAcademicRecordRefId) {
	this.studentAcademicRecordRefId = studentAcademicRecordRefId;
    }

    public Integer getStudentAcademicRecordSchoolYear() {
	return studentAcademicRecordSchoolYear;
    }

    public void setStudentAcademicRecordSchoolYear(Integer studentAcademicRecordSchoolYear) {
	this.studentAcademicRecordSchoolYear = studentAcademicRecordSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public Date getAsOfDate() {
	return this.asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
	this.asOfDate = asOfDate;
    }

    public BigDecimal getGradePointAverageCumulative() {
	return this.gradePointAverageCumulative;
    }

    public void setGradePointAverageCumulative(BigDecimal gradePointAverageCumulative) {
	this.gradePointAverageCumulative = gradePointAverageCumulative;
    }

    public BigDecimal getGradePointAverageGivenSession() {
	return this.gradePointAverageGivenSession;
    }

    public void setGradePointAverageGivenSession(BigDecimal gradePointAverageGivenSession) {
	this.gradePointAverageGivenSession = gradePointAverageGivenSession;
    }

    public Integer getHighSchoolStudentClassRank() {
	return this.highSchoolStudentClassRank;
    }

    public void setHighSchoolStudentClassRank(Integer highSchoolStudentClassRank) {
	this.highSchoolStudentClassRank = highSchoolStudentClassRank;
    }

    @Override
    public String toString() {
	return "StudentAcademicRecord [studentAcademicRecordRefId=" + studentAcademicRecordRefId
		+ ", studentAcademicRecordSchoolYear=" + studentAcademicRecordSchoolYear + ", student=" + student
		+ ", asOfDate=" + asOfDate + ", gradePointAverageCumulative=" + gradePointAverageCumulative
		+ ", gradePointAverageGivenSession=" + gradePointAverageGivenSession + ", highSchoolStudentClassRank="
		+ highSchoolStudentClassRank + "]";
    }

}
