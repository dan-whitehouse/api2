package org.ricone.api.core.model.view;


import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.ricone.api.core.model.view.composite.SourcedComposite;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@IdClass(SourcedComposite.class)
@Immutable @Entity @Table(name = "classview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@BatchSize(size = 100)
public class ClassView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "SourcedId")
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear")
	@Id private Integer sourcedSchoolYear;

	@Column(name = "DistrictId")
	private String districtId;

	@Column(name = "Title")
	private String title;

	@Column(name = "ClassCode")
	private String classCode;

	@Column(name = "ClassType")
	private String classType;

	@Column(name = "Location")
	private String location;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<ClassGradeView> grades = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<ClassSubjectView> subjects = new HashSet<>(0);

	@Column(name = "CourseId")
	private String courseId;

	@Column(name = "CourseSchoolYear")
	private Integer courseSchoolYear;

	@Column(name = "OrgId")
	private String orgId;

	@Column(name = "OrgSchoolYear")
	private Integer orgSchoolYear;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<ClassTermView> terms = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<ClassPeriodView> periods = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<ClassUserView> users = new HashSet<>(0);

	public ClassView() {
	}

	public ClassView(String sourcedId, Integer sourcedSchoolYear, String districtId, String title, String classCode, String classType, String location, Set<ClassGradeView> grades, Set<ClassSubjectView> subjects, String courseId, Integer courseSchoolYear, String orgId, Integer orgSchoolYear, Set<ClassTermView> terms, Set<ClassPeriodView> periods, Set<ClassUserView> users) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.districtId = districtId;
		this.title = title;
		this.classCode = classCode;
		this.classType = classType;
		this.location = location;
		this.grades = grades;
		this.subjects = subjects;
		this.courseId = courseId;
		this.courseSchoolYear = courseSchoolYear;
		this.orgId = orgId;
		this.orgSchoolYear = orgSchoolYear;
		this.terms = terms;
		this.periods = periods;
		this.users = users;
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

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<ClassGradeView> getGrades() {
		return grades;
	}

	public void setGrades(Set<ClassGradeView> grades) {
		this.grades = grades;
	}

	public Set<ClassSubjectView> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<ClassSubjectView> subjects) {
		this.subjects = subjects;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseSchoolYear() {
		return courseSchoolYear;
	}

	public void setCourseSchoolYear(Integer courseSchoolYear) {
		this.courseSchoolYear = courseSchoolYear;
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

	public Set<ClassTermView> getTerms() {
		return terms;
	}

	public void setTerms(Set<ClassTermView> terms) {
		this.terms = terms;
	}

	public Set<ClassPeriodView> getPeriods() {
		return periods;
	}

	public void setPeriods(Set<ClassPeriodView> periods) {
		this.periods = periods;
	}
}
