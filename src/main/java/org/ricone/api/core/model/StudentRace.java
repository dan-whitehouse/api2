package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentRaceComposite;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentrace")
@IdClass(StudentRaceComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentRace implements Serializable {
    private static final long serialVersionUID = -6465888681459728010L;

    @Column(name = "StudentRaceRefId", unique = true, nullable = false, length = 64)
    @Id private String studentRaceRefId;

    @Column(name = "StudentRaceSchoolYear", nullable = false, length = 6)
    @Id private Integer studentRaceSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false) })
    private Student student;

    @Column(name = "RaceCode", length = 50)
    private String raceCode;

    public StudentRace() {
    }

    public StudentRace(String studentRaceRefId, Integer studentRaceSchoolYear, Student student, String raceCode) {
	this.studentRaceRefId = studentRaceRefId;
	this.studentRaceSchoolYear = studentRaceSchoolYear;
	this.student = student;
	this.raceCode = raceCode;
    }

    public String getStudentRaceRefId() {
	return this.studentRaceRefId;
    }

    public void setStudentRaceRefId(String studentRaceRefId) {
	this.studentRaceRefId = studentRaceRefId;
    }

    public Integer getStudentRaceSchoolYear() {
	return studentRaceSchoolYear;
    }

    public void setStudentRaceSchoolYear(Integer studentRaceSchoolYear) {
	this.studentRaceSchoolYear = studentRaceSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public String getRaceCode() {
	return this.raceCode;
    }

    public void setRaceCode(String raceCode) {
	this.raceCode = raceCode;
    }

    @Override
    public String toString() {
	return "StudentRace [studentRaceRefId=" + studentRaceRefId + ", studentRaceSchoolYear=" + studentRaceSchoolYear
		+ ", student=" + student + ", raceCode=" + raceCode + "]";
    }

}
