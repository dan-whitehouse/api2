package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.GradeComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(GradeComposite.class)
@Immutable @Entity @Table(name = "coursegradeview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CourseGradeView implements Serializable {
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
	private CourseView courseView;

	public CourseGradeView() {
	}

	public CourseGradeView(String gradeId, Integer gradeSchoolYear, String gradeLevel, CourseView courseView) {
		this.gradeId = gradeId;
		this.gradeSchoolYear = gradeSchoolYear;
		this.gradeLevel = gradeLevel;
		this.courseView = courseView;
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

	public CourseView getCourseView() {
		return courseView;
	}

	public void setCourseView(CourseView courseView) {
		this.courseView = courseView;
	}
}
