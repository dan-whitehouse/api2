package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "onerosterv1p1_userclass")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QUserClass implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "UserClassId", unique = true, nullable = false, length = 64)
	@Id private String userClassId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "UserId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "UserSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ClassId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "ClassSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QClass clazz;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QOrg org;

	public QUserClass() {
	}

	public QUserClass(String userClassId, QUser user, QClass clazz, QOrg org) {
		this.userClassId = userClassId;
		this.user = user;
		this.clazz = clazz;
		this.org = org;
	}

	public String getUserClassId() {
		return userClassId;
	}

	public void setUserClassId(String userClassId) {
		this.userClassId = userClassId;
	}

	public QUser getUser() {
		return user;
	}

	public void setUser(QUser user) {
		this.user = user;
	}

	public QClass getClazz() {
		return clazz;
	}

	public void setClazz(QClass clazz) {
		this.clazz = clazz;
	}

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}
}
