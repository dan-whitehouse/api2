package org.ricone.api.core.model.oneroster;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "onerosterv1p1_academicsession")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QAcademicSession implements Serializable {
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

	@Column(name = "Title", length = 1000)
	private String title;

	@Column(name = "Type", length = 10)
	private String type;

	@Column(name = "SchoolYear", length = 50)
	private Integer schoolYear;

	@Column(name = "StartDate", length = 40)
	private LocalDate startDate;

	@Column(name = "EndDate", length = 40)
	private LocalDate endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "AcademicSessionId"),
			@JoinColumn(name = "AcademicSessionSchoolYear")
	})
	private QAcademicSession academicSession;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QOrg org;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "academicSession")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QAcademicSessionChild> children = new HashSet<>();

	public QAcademicSession() {
	}

	public QAcademicSession(String sourcedId, Integer sourcedSchoolYear, String status, LocalDateTime dateLastModified, String districtId, String title, String type, Integer schoolYear, LocalDate startDate, LocalDate endDate, QAcademicSession academicSession, QOrg org, Set<QAcademicSessionChild> children) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.districtId = districtId;
		this.title = title;
		this.type = type;
		this.schoolYear = schoolYear;
		this.startDate = startDate;
		this.endDate = endDate;
		this.academicSession = academicSession;
		this.org = org;
		this.children = children;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public Set<QAcademicSessionChild> getChildren() {
		return children;
	}

	public void setChildren(Set<QAcademicSessionChild> children) {
		this.children = children;
	}
}
