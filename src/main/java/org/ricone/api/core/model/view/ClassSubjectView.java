package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.ClassSubjectComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(ClassSubjectComposite.class)
@Immutable @Entity @Table(name = "classsubjectview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ClassSubjectView implements Serializable {
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
	private ClassView classView;

	public ClassSubjectView() {
	}

	public ClassSubjectView(String subjectId, Integer subjectSchoolYear, String subject, String subjectCode, ClassView classView) {
		this.subjectId = subjectId;
		this.subjectSchoolYear = subjectSchoolYear;
		this.subject = subject;
		this.subjectCode = subjectCode;
		this.classView = classView;
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

	public ClassView getClassView() {
		return classView;
	}

	public void setClassView(ClassView classView) {
		this.classView = classView;
	}
}
