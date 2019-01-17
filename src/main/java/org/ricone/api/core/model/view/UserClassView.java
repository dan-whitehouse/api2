package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.UserOrgComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(UserOrgComposite.class)
@Immutable @Entity @Table(name = "userclassview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserClassView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "ClassId")
	@Id private String classId;

	@Column(name = "ClassSchoolYear")
	@Id private Integer classSchoolYear;

	@Column(name = "OrgId")
	private String orgId;

	@Column(name = "OrgSchoolYear")
	private String orgSchoolYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourceId", referencedColumnName="sourceId", nullable = false),
			@JoinColumn(name="SourceSchoolYear", referencedColumnName="sourceSchoolYear", nullable = false),
			@JoinColumn(name="SourceRole", referencedColumnName="role", nullable = false)
	})
	private UserView userView;

	public UserClassView() {
	}

	public UserClassView(String classId, Integer classSchoolYear, String orgId, String orgSchoolYear, UserView userView) {
		this.classId = classId;
		this.classSchoolYear = classSchoolYear;
		this.orgId = orgId;
		this.orgSchoolYear = orgSchoolYear;
		this.userView = userView;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Integer getClassSchoolYear() {
		return classSchoolYear;
	}

	public void setClassSchoolYear(Integer classSchoolYear) {
		this.classSchoolYear = classSchoolYear;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgSchoolYear() {
		return orgSchoolYear;
	}

	public void setOrgSchoolYear(String orgSchoolYear) {
		this.orgSchoolYear = orgSchoolYear;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
}
