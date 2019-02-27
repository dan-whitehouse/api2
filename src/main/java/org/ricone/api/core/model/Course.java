package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.CourseComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@IdClass(CourseComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class Course implements Serializable {
	private static final long serialVersionUID = -7068659701898642886L;
	
	@Column(name = "CourseRefId", unique = true, nullable = false, length = 64)
	@Id private String courseRefId;
	
	@Column(name = "CourseSchoolYear", nullable = false, length = 6)
	@Id private Integer courseSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolRefId", referencedColumnName="schoolRefId", nullable = false),
		@JoinColumn(name="SchoolSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
	})
	private School school;
	
	@Column(name = "Title", length = 60)
	private String title;
	
	@Column(name = "Description", length = 60)
	private String description;
	
	@Column(name = "SubjectCode", length = 50)
	private String subjectCode;
	
	@Column(name = "SCEDCourseCode", length = 5)
	private String scedCourseCode;
	
	@Column(name = "SCEDCourseLevelCode", length = 50)
	private String scedCourseLevelCode;
	
	@Column(name = "SCEDCourseSubjectAreaCode", length = 50)
	private String scedCourseSubjectAreaCode;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<CourseSection> courseSections = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<CourseIdentifier> courseIdentifiers = new HashSet<>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<CourseGrade> courseGrades = new HashSet<>(0);

	public Course() {
	}

	public Course(String courseRefId, Integer courseSchoolYear, School school, String title, String description, String subjectCode, String scedCourseCode, String scedCourseLevelCode, String scedCourseSubjectAreaCode, Set<CourseSection> courseSections, Set<CourseIdentifier> courseIdentifiers, Set<CourseGrade> courseGrades) {
		super();
		this.courseRefId = courseRefId;
		this.courseSchoolYear = courseSchoolYear;
		this.school = school;
		this.title = title;
		this.description = description;
		this.subjectCode = subjectCode;
		this.scedCourseCode = scedCourseCode;
		this.scedCourseLevelCode = scedCourseLevelCode;
		this.scedCourseSubjectAreaCode = scedCourseSubjectAreaCode;
		this.courseSections = courseSections;
		this.courseIdentifiers = courseIdentifiers;
		this.courseGrades = courseGrades;
	}

	public String getCourseRefId() {
		return this.courseRefId;
	}
	public void setCourseRefId(String courseRefId) {
		this.courseRefId = courseRefId;
	}
	
	public Integer getCourseSchoolYear() {
		return courseSchoolYear;
	}
	public void setCourseSchoolYear(Integer courseSchoolYear) {
		this.courseSchoolYear = courseSchoolYear;
	}

	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getScedCourseCode() {
		return this.scedCourseCode;
	}
	public void setScedCourseCode(String scedcourseCode) {
		this.scedCourseCode = scedcourseCode;
	}

	public String getScedCourseLevelCode() {
		return this.scedCourseLevelCode;
	}
	public void setScedCourseLevelCode(String scedcourseLevelCode) {
		this.scedCourseLevelCode = scedcourseLevelCode;
	}

	public String getScedCourseSubjectAreaCode() {
		return this.scedCourseSubjectAreaCode;
	}
	public void setScedCourseSubjectAreaCode(String scedcourseSubjectAreaCode) {
		this.scedCourseSubjectAreaCode = scedcourseSubjectAreaCode;
	}

	public Set<CourseSection> getCourseSections() {
		return this.courseSections;
	}
	public void setCourseSections(Set<CourseSection> coursesections) {
		this.courseSections = coursesections;
	}

	public Set<CourseIdentifier> getCourseIdentifiers() {
		return this.courseIdentifiers;
	}
	public void setCourseIdentifiers(Set<CourseIdentifier> courseidentifiers) {
		this.courseIdentifiers = courseidentifiers;
	}

	public Set<CourseGrade> getCourseGrades() {
		return this.courseGrades;
	}
	public void setCourseGrades(Set<CourseGrade> coursegrades) {
		this.courseGrades = coursegrades;
	}

	@Override
	public String toString() {
		return "Course [courseRefId=" + courseRefId + ", courseSchoolYear=" + courseSchoolYear + ", school=" + school + ", title=" + title + ", description=" + description + ", subjectCode=" + subjectCode + ", scedCourseCode=" + scedCourseCode + ", scedCourseLevelCode=" + scedCourseLevelCode + ", scedCourseSubjectAreaCode=" + scedCourseSubjectAreaCode + ", courseSections=" + courseSections + ", courseIdentifiers=" + courseIdentifiers + ", courseGrades=" + courseGrades + "]";
	}
}
