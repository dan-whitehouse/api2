package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.ClassPeriodComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(ClassPeriodComposite.class)
@Immutable @Entity @Table(name = "classperiodview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ClassPeriodView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "PeriodId")
	@Id private String periodId;

	@Column(name = "PeriodSchoolYear")
	@Id private Integer periodSchoolYear;

	@Column(name = "Period")
	private String period;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private ClassView classView;

	public ClassPeriodView() {
	}

	public ClassPeriodView(String periodId, Integer periodSchoolYear, String period, ClassView classView) {
		this.periodId = periodId;
		this.periodSchoolYear = periodSchoolYear;
		this.period = period;
		this.classView = classView;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public Integer getPeriodSchoolYear() {
		return periodSchoolYear;
	}

	public void setPeriodSchoolYear(Integer periodSchoolYear) {
		this.periodSchoolYear = periodSchoolYear;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public ClassView getClassView() {
		return classView;
	}

	public void setClassView(ClassView classView) {
		this.classView = classView;
	}
}
