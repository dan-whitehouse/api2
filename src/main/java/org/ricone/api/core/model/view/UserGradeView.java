package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.UserGradeComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(UserGradeComposite.class)
@Immutable @Entity @Table(name = "usergradeview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserGradeView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "GradeId")
	@Id private String gradeId;

	@Column(name = "GradeSchoolYear")
	@Id private Integer gradeSchoolYear;

	@Column(name = "GradeLevel")
	private String GradeLevel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false)/*,
			@JoinColumn(name="SourcedRole", referencedColumnName="role", nullable = false)*/
	})
	private UserView userView;

	public UserGradeView() {
	}

	public UserGradeView(String gradeId, Integer gradeSchoolYear, String gradeLevel, UserView userView) {
		this.gradeId = gradeId;
		this.gradeSchoolYear = gradeSchoolYear;
		GradeLevel = gradeLevel;
		this.userView = userView;
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
		return GradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		GradeLevel = gradeLevel;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
}
