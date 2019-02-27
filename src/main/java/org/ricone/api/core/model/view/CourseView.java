package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.view.composite.SourcedComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@IdClass(SourcedComposite.class)
@Immutable @Entity @Table(name = "courseview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@BatchSize(size = 100)
public class CourseView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "SourcedId")
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear")
	@Id private Integer sourcedSchoolYear;

	@Column(name = "DistrictId")
	private String districtId;

	@Column(name = "Title")
	private String title;

	@Column(name = "CourseCode")
	private String courseCode;

	@Column(name = "AcademicSessionId")
	private String academicSessionId;

	@Column(name = "AcademicSessionSchoolYear")
	private Integer academicSessionSchoolYear;

	@Column(name = "OrgId")
	private String orgId;

	@Column(name = "OrgSchoolYear")
	private Integer orgSchoolYear;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<CourseGradeView> grades = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<CourseSubjectView> subjects = new HashSet<>(0);

	public CourseView() {
	}

	public CourseView(String sourcedId, Integer sourcedSchoolYear, String districtId, String title, String courseCode, String academicSessionId, Integer academicSessionSchoolYear, String orgId, Integer orgSchoolYear, Set<CourseGradeView> grades, Set<CourseSubjectView> subjects) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.districtId = districtId;
		this.title = title;
		this.courseCode = courseCode;
		this.academicSessionId = academicSessionId;
		this.academicSessionSchoolYear = academicSessionSchoolYear;
		this.orgId = orgId;
		this.orgSchoolYear = orgSchoolYear;
		this.grades = grades;
		this.subjects = subjects;
	}

	public String getSourcedId() {
		return sourcedId;
	}

	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	public Integer getSourcedSchoolYear() {
		return sourcedSchoolYear;
	}

	public void setSourcedSchoolYear(Integer sourcedSchoolYear) {
		this.sourcedSchoolYear = sourcedSchoolYear;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getAcademicSessionId() {
		return academicSessionId;
	}

	public void setAcademicSessionId(String academicSessionId) {
		this.academicSessionId = academicSessionId;
	}

	public Integer getAcademicSessionSchoolYear() {
		return academicSessionSchoolYear;
	}

	public void setAcademicSessionSchoolYear(Integer academicSessionSchoolYear) {
		this.academicSessionSchoolYear = academicSessionSchoolYear;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgSchoolYear() {
		return orgSchoolYear;
	}

	public void setOrgSchoolYear(Integer orgSchoolYear) {
		this.orgSchoolYear = orgSchoolYear;
	}

	public Set<CourseGradeView> getGrades() {
		return grades;
	}

	public void setGrades(Set<CourseGradeView> grades) {
		this.grades = grades;
	}

	public Set<CourseSubjectView> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<CourseSubjectView> subjects) {
		this.subjects = subjects;
	}
}
