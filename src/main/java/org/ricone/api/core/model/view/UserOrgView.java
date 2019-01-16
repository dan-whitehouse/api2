package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.UserComposite;
import org.ricone.api.core.model.view.composite.UserOrgComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(UserOrgComposite.class)
@Immutable @Entity @Table(name = "userorgview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserOrgView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "OrgId")
	@Id private String orgId;

	@Column(name = "OrgSchoolYear")
	@Id private Integer orgSchoolYear;

	@Column(name = "OrgType")
	private String orgType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourceId", referencedColumnName="sourceId", nullable = false),
			@JoinColumn(name="SourceSchoolYear", referencedColumnName="sourceSchoolYear", nullable = false),
			@JoinColumn(name="SourceRole", referencedColumnName="role", nullable = false)
	})
	private UserView userView;

	public UserOrgView() {
	}

	public UserOrgView(String orgId, Integer orgSchoolYear, String orgType, UserView userView) {
		this.orgId = orgId;
		this.orgSchoolYear = orgSchoolYear;
		this.orgType = orgType;
		this.userView = userView;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgSchoolYear() {
		return orgSchoolYear;
	}

	public void setOrgSchoolYear(Integer orgSchoolYear) {
		this.orgSchoolYear = orgSchoolYear;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
}
