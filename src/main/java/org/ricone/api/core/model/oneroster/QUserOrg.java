package org.ricone.api.core.model.oneroster;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "onerosterv1p1_userorg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QUserOrg implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "UserOrgId", unique = true, nullable = false, length = 64)
	@Id private String userOrgId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "UserId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "UserSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QOrg org;

	public QUserOrg() {
	}

	public QUserOrg(String userOrgId, QUser user, QOrg org) {
		this.userOrgId = userOrgId;
		this.user = user;
		this.org = org;
	}

	public String getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}

	public QUser getUser() {
		return user;
	}

	public void setUser(QUser user) {
		this.user = user;
	}

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}
}
