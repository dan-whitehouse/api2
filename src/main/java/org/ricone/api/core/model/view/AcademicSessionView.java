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
@Immutable @Entity @Table(name = "academicsessionview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AcademicSessionView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "SourcedId")
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear")
	@Id private Integer sourcedSchoolYear;

	@Column(name = "Title")
	private String title;

	@Column(name = "Type")
	private String type;

	@Column(name = "SchoolYear")
	private String schoolYear;

	@Column(name = "BeginDate")
	LocalDate beginDate;

	@Column(name = "EndDate")
	LocalDate endDate;

	@Column(name = "AcademicSessionId")
	private String academicSessionId;

	@Column(name = "AcademicSessionSchoolYear")
	private Integer academicSessionSchoolYear;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "academicSessionView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<AcademicSessionChildrenView> children = new HashSet<>(0);

	public AcademicSessionView() {
	}

	public AcademicSessionView(String sourcedId, Integer sourcedSchoolYear, String title, String type, String schoolYear, LocalDate beginDate, LocalDate endDate, String academicSessionId, Integer academicSessionSchoolYear, Set<AcademicSessionChildrenView> children) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.title = title;
		this.type = type;
		this.schoolYear = schoolYear;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.academicSessionId = academicSessionId;
		this.academicSessionSchoolYear = academicSessionSchoolYear;
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

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public Set<AcademicSessionChildrenView> getChildren() {
		return children;
	}

	public void setChildren(Set<AcademicSessionChildrenView> children) {
		this.children = children;
	}
}
