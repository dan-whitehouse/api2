package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.CourseGradeComposite;

import javax.persistence.*;

@Entity
@Table(name = "coursegrade")
@IdClass(CourseGradeComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseGrade implements java.io.Serializable {
	private static final long serialVersionUID = 2650694715490007664L;
	
	@Column(name = "CourseGradeRefId", unique = true, nullable = false, length = 64)
	@Id
    private String courseGradeRefId;
	
	@Column(name = "CourseGradeSchoolYear", nullable = false, length = 6)
	@Id
    private Integer courseGradeSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CourseRefId", referencedColumnName="courseRefId", nullable = false),
		@JoinColumn(name="CourseSchoolYear", referencedColumnName="courseSchoolYear", nullable = false)
	})
	private Course course;
	
	@Column(name = "GradeLevelCode", nullable = false, length = 50)
	private String gradeLevelCode;

	public CourseGrade() {
	}
	
	public CourseGrade(String courseGradeRefId, Integer courseGradeSchoolYear, Course course, String gradeLevelCode) {
		super();
		this.courseGradeRefId = courseGradeRefId;
		this.courseGradeSchoolYear = courseGradeSchoolYear;
		this.course = course;
		this.gradeLevelCode = gradeLevelCode;
	}

	public String getCourseGradeRefId() {
		return this.courseGradeRefId;
	}
	public void setCourseGradeRefId(String courseGradeRefId) {
		this.courseGradeRefId = courseGradeRefId;
	}
	
	public Integer getCourseGradeSchoolYear() {
		return courseGradeSchoolYear;
	}
	public void setCourseGradeSchoolYear(Integer courseGradeSchoolYear) {
		this.courseGradeSchoolYear = courseGradeSchoolYear;
	}

	public Course getCourse() {
		return this.course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String getGradeLevelCode() {
		return this.gradeLevelCode;
	}
	public void setGradeLevelCode(String gradeLevelCode) {
		this.gradeLevelCode = gradeLevelCode;
	}

	@Override
	public String toString() {
		return "CourseGrade [courseGradeRefId=" + courseGradeRefId + ", courseGradeSchoolYear=" + courseGradeSchoolYear + ", course=" + course + ", gradeLevelCode=" + gradeLevelCode + "]";
	}
}
