package org.ricone.api.core.model.view;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.SourcedComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

	public AcademicSessionView() {
	}

	public AcademicSessionView(String sourcedId, Integer sourcedSchoolYear, String title, String type, String schoolYear, LocalDate beginDate, LocalDate endDate) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.title = title;
		this.type = type;
		this.schoolYear = schoolYear;
		this.beginDate = beginDate;
		this.endDate = endDate;
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
}
