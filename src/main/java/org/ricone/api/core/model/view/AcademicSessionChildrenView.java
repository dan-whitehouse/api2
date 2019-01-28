package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.AcademicSessionChildComposite;
import org.ricone.api.core.model.view.composite.ClassTermComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(AcademicSessionChildComposite.class)
@Immutable @Entity @Table(name = "academicsessionchildrenview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AcademicSessionChildrenView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "ChildId")
	@Id private String childId;

	@Column(name = "ChildSchoolYear")
	@Id private Integer childSchoolYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private AcademicSessionView academicSessionView;

	public AcademicSessionChildrenView() {
	}

	public AcademicSessionChildrenView(String childId, Integer childSchoolYear, AcademicSessionView academicSessionView) {
		this.childId = childId;
		this.childSchoolYear = childSchoolYear;
		this.academicSessionView = academicSessionView;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public Integer getChildSchoolYear() {
		return childSchoolYear;
	}

	public void setChildSchoolYear(Integer childSchoolYear) {
		this.childSchoolYear = childSchoolYear;
	}

	public AcademicSessionView getAcademicSessionView() {
		return academicSessionView;
	}

	public void setAcademicSessionView(AcademicSessionView academicSessionView) {
		this.academicSessionView = academicSessionView;
	}
}
