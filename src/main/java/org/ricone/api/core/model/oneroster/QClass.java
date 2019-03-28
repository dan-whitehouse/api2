package org.ricone.api.core.model.oneroster;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "onerosterv1p1_class")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QClass implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourcedId", unique = true, nullable = false, length = 64)
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear", nullable = false, length = 6)
	@Id private Integer sourcedSchoolYear;

	@Column(name = "Status", length = 20)
	private String status;

	@Column(name = "DateLastModified")
	private LocalDateTime dateLastModified;

	@Column(name = "DistrictId", length = 30)
	private String districtId;

	@Column(name = "Title", length = 8)
	private String title;

	@Column(name = "ClassCode", length = 75)
	private String classCode;

	@Column(name = "ClassType", length = 75)
	private String classType;

	@Column(name = "Location", length = 75)
	private String location;

	@Column(name = "Grades", length = 50)
	private String grades;

	@Column(name = "Subjects", length = 40)
	private String subjects;

	@Column(name = "SubjectCodes", length = 40)
	private String subjectCodes;

	@Column(name = "Periods", length = 40)
	private String periods;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "CourseId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "CourseSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QCourse course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QOrg org;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clazz")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QClassAcademicSession> terms = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clazz")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QUserClass> users = new HashSet<>();

	public QClass() {
	}

	public QClass(String sourcedId, Integer sourcedSchoolYear, String status, LocalDateTime dateLastModified, String districtId, String title, String classCode, String classType, String location, String grades, String subjects, String subjectCodes, String periods, QCourse course, QOrg org, Set<QClassAcademicSession> terms, Set<QUserClass> users) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.districtId = districtId;
		this.title = title;
		this.classCode = classCode;
		this.classType = classType;
		this.location = location;
		this.grades = grades;
		this.subjects = subjects;
		this.subjectCodes = subjectCodes;
		this.periods = periods;
		this.course = course;
		this.org = org;
		this.terms = terms;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(LocalDateTime dateLastModified) {
		this.dateLastModified = dateLastModified;
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

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getSubjectCodes() {
		return subjectCodes;
	}

	public void setSubjectCodes(String subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public QCourse getCourse() {
		return course;
	}

	public void setCourse(QCourse course) {
		this.course = course;
	}

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}

	public Set<QClassAcademicSession> getTerms() {
		return terms;
	}

	public void setTerms(Set<QClassAcademicSession> terms) {
		this.terms = terms;
	}

	public Set<QUserClass> getUsers() {
		return users;
	}

	public void setUsers(Set<QUserClass> users) {
		this.users = users;
	}
}
