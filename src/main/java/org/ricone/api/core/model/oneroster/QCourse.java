package org.ricone.api.core.model.oneroster;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "onerosterv1p1_course")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QCourse implements Serializable {
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

	@Column(name = "CourseCode", length = 75)
	private String courseCode;

	@Column(name = "Grades", length = 50)
	private String grades;

	@Column(name = "Subjects", length = 40)
	private String subjects;

	@Column(name = "SubjectCodes", length = 40)
	private String subjectCodes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "AcademicSessionId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "AcademicSessionSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QAcademicSession academicSession;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QOrg org;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QClass> classes = new HashSet<>();

	public QCourse() {
	}

	public QCourse(String sourcedId, Integer sourcedSchoolYear, String status, LocalDateTime dateLastModified, String districtId, String title, String courseCode, String grades, String subjects, String subjectCodes, QAcademicSession academicSession, QOrg org, Set<QClass> classes) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.districtId = districtId;
		this.title = title;
		this.courseCode = courseCode;
		this.grades = grades;
		this.subjects = subjects;
		this.subjectCodes = subjectCodes;
		this.academicSession = academicSession;
		this.org = org;
		this.classes = classes;
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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
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

	public QAcademicSession getAcademicSession() {
		return academicSession;
	}

	public void setAcademicSession(QAcademicSession academicSession) {
		this.academicSession = academicSession;
	}

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}

	public Set<QClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<QClass> classes) {
		this.classes = classes;
	}
}
