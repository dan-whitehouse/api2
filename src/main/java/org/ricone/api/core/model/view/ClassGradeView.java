package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.ClassGradeComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(ClassGradeComposite.class)
@Immutable @Entity @Table(name = "classgradeview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ClassGradeView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "GradeId")
	@Id private String gradeId;

	@Column(name = "GradeSchoolYear")
	@Id private Integer gradeSchoolYear;

	@Column(name = "GradeLevel")
	private String gradeLevel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private ClassView classView;

	public ClassGradeView() {
	}

	public ClassGradeView(String gradeId, Integer gradeSchoolYear, String gradeLevel, ClassView classView) {
		this.gradeId = gradeId;
		this.gradeSchoolYear = gradeSchoolYear;
		this.gradeLevel = gradeLevel;
		this.classView = classView;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getGradeSchoolYear() {
		return gradeSchoolYear;
	}

	public void setGradeSchoolYear(Integer gradeSchoolYear) {
		this.gradeSchoolYear = gradeSchoolYear;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public ClassView getClassView() {
		return classView;
	}

	public void setClassView(ClassView classView) {
		this.classView = classView;
	}
}
