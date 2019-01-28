package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.SubjectComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(SubjectComposite.class)
@Immutable @Entity @Table(name = "coursesubjectview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CourseSubjectView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "SubjectId")
	@Id private String subjectId;

	@Column(name = "SubjectSchoolYear")
	@Id private Integer subjectSchoolYear;

	@Column(name = "Subject")
	private String subject;

	@Column(name = "SubjectCode")
	private String subjectCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private CourseView courseView;

	public CourseSubjectView() {
	}

	public CourseSubjectView(String subjectId, Integer subjectSchoolYear, String subject, String subjectCode, CourseView courseView) {
		this.subjectId = subjectId;
		this.subjectSchoolYear = subjectSchoolYear;
		this.subject = subject;
		this.subjectCode = subjectCode;
		this.courseView = courseView;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getSubjectSchoolYear() {
		return subjectSchoolYear;
	}

	public void setSubjectSchoolYear(Integer subjectSchoolYear) {
		this.subjectSchoolYear = subjectSchoolYear;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public CourseView getCourseView() {
		return courseView;
	}

	public void setCourseView(CourseView courseView) {
		this.courseView = courseView;
	}
}
