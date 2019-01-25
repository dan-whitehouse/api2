package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.ClassTermComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(ClassTermComposite.class)
@Immutable @Entity @Table(name = "classtermview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ClassTermView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "TermId")
	@Id private String termId;

	@Column(name = "TermSchoolYear")
	@Id private Integer termSchoolYear;

	@Column(name = "Type")
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private ClassView classView;

	public ClassTermView() {
	}

	public ClassTermView(String termId, Integer termSchoolYear, String type, ClassView classView) {
		this.termId = termId;
		this.termSchoolYear = termSchoolYear;
		this.type = type;
		this.classView = classView;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public Integer getTermSchoolYear() {
		return termSchoolYear;
	}

	public void setTermSchoolYear(Integer termSchoolYear) {
		this.termSchoolYear = termSchoolYear;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ClassView getClassView() {
		return classView;
	}

	public void setClassView(ClassView classView) {
		this.classView = classView;
	}
}
