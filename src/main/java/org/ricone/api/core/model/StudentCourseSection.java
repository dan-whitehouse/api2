package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentCourseSectionComposite;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentcoursesection")
@IdClass(StudentCourseSectionComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentCourseSection implements Serializable {
	private static final long serialVersionUID = 8992517437619935826L;
	
	@Column(name = "StudentCourseSectionRefId", unique = true, nullable = false, length = 64)
	@Id private String studentCourseSectionRefId;
	
	@Column(name = "StudentCourseSectionSchoolYear", nullable = false, length = 6)
	@Id private Integer studentCourseSectionSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CourseSectionRefId", referencedColumnName="courseSectionRefId", nullable = false),
		@JoinColumn(name="CourseSectionSchoolYear", referencedColumnName="courseSectionSchoolYear", nullable = false)
	})
	private CourseSection courseSection;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentRefId", referencedColumnName="studentRefId", nullable = false),
		@JoinColumn(name="StudentSchoolYear", referencedColumnName="studentSchoolYear", nullable = false)
	})
	private Student student;
	
	@Column(name = "CourseSectionEnrollmentStatusTypeCode", length = 50)
	private String courseSectionEnrollmentStatusTypeCode;
	
	@Column(name = "CourseSectionEntryTypeCode", length = 50)
	private String courseSectionEntryTypeCode;
	
	@Column(name = "CourseSectionExitTypeCode", length = 50)
	private String courseSectionExitTypeCode;
	
	@Column(name = "ExitOrWithdrawalStatusCode", length = 50)
	private String exitOrWithdrawalStatusCode;
	
	@Column(name = "GradeLevelWhenCourseTakenCode", length = 50)
	private String gradeLevelWhenCourseTakenCode;

	public StudentCourseSection() {
	}

	public StudentCourseSection(String studentCourseSectionRefId, Integer studentCourseSectionSchoolYear, CourseSection courseSection, Student student, String courseSectionEnrollmentStatusTypeCode, String courseSectionEntryTypeCode, String courseSectionExitTypeCode, String exitOrWithdrawalStatusCode, String gradeLevelWhenCourseTakenCode) {
		super();
		this.studentCourseSectionRefId = studentCourseSectionRefId;
		this.studentCourseSectionSchoolYear = studentCourseSectionSchoolYear;
		this.courseSection = courseSection;
		this.student = student;
		this.courseSectionEnrollmentStatusTypeCode = courseSectionEnrollmentStatusTypeCode;
		this.courseSectionEntryTypeCode = courseSectionEntryTypeCode;
		this.courseSectionExitTypeCode = courseSectionExitTypeCode;
		this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
		this.gradeLevelWhenCourseTakenCode = gradeLevelWhenCourseTakenCode;
	}

	public String getStudentCourseSectionRefId() {
		return this.studentCourseSectionRefId;
	}

	public void setStudentCourseSectionRefId(String studentCourseSectionRefId) {
		this.studentCourseSectionRefId = studentCourseSectionRefId;
	}

	public Integer getStudentCourseSectionSchoolYear() {
		return studentCourseSectionSchoolYear;
	}
	public void setStudentCourseSectionSchoolYear(Integer studentCourseSectionSchoolYear) {
		this.studentCourseSectionSchoolYear = studentCourseSectionSchoolYear;
	}

	public CourseSection getCourseSection() {
		return this.courseSection;
	}
	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}

	public Student getStudent() {
		return this.student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCourseSectionEnrollmentStatusTypeCode() {
		return this.courseSectionEnrollmentStatusTypeCode;
	}
	public void setCourseSectionEnrollmentStatusTypeCode(String courseSectionEnrollmentStatusTypeCode) {
		this.courseSectionEnrollmentStatusTypeCode = courseSectionEnrollmentStatusTypeCode;
	}

	public String getCourseSectionEntryTypeCode() {
		return this.courseSectionEntryTypeCode;
	}
	public void setCourseSectionEntryTypeCode(String courseSectionEntryTypeCode) {
		this.courseSectionEntryTypeCode = courseSectionEntryTypeCode;
	}

	public String getCourseSectionExitTypeCode() {
		return this.courseSectionExitTypeCode;
	}
	public void setCourseSectionExitTypeCode(String courseSectionExitTypeCode) {
		this.courseSectionExitTypeCode = courseSectionExitTypeCode;
	}
	
	public String getExitOrWithdrawalStatusCode() {
		return this.exitOrWithdrawalStatusCode;
	}
	public void setExitOrWithdrawalStatusCode(String exitOrWithdrawalStatusCode) {
		this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
	}

	public String getGradeLevelWhenCourseTakenCode() {
		return this.gradeLevelWhenCourseTakenCode;
	}
	public void setGradeLevelWhenCourseTakenCode(String gradeLevelWhenCourseTakenCode) {
		this.gradeLevelWhenCourseTakenCode = gradeLevelWhenCourseTakenCode;
	}
}
