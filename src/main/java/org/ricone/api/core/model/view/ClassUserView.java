package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.ClassUserComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(ClassUserComposite.class)
@Immutable @Entity @Table(name = "classuserview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ClassUserView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "UserId")
	@Id private String userId;

	@Column(name = "UserSchoolYear")
	@Id private Integer userSchoolYear;

	@Column(name = "Role")
	@Id private String role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private ClassView classView;

	public ClassUserView() {
	}

	public ClassUserView(String userId, Integer userSchoolYear, String role, ClassView classView) {
		this.userId = userId;
		this.userSchoolYear = userSchoolYear;
		this.role = role;
		this.classView = classView;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserSchoolYear() {
		return userSchoolYear;
	}

	public void setUserSchoolYear(Integer userSchoolYear) {
		this.userSchoolYear = userSchoolYear;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ClassView getClassView() {
		return classView;
	}

	public void setClassView(ClassView classView) {
		this.classView = classView;
	}
}
