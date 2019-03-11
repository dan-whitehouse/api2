package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "onerosterv1p1_orgchild")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QOrgChild implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "OrgChildId", unique = true, nullable = false, length = 64)
	@Id private String orgChildId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ChildId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "ChildSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QOrg child;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QOrg org;

	public QOrgChild() {
	}

	public QOrgChild(String orgChildId, QOrg child, QOrg org) {
		this.orgChildId = orgChildId;
		this.child = child;
		this.org = org;
	}

	public String getOrgChildId() {
		return orgChildId;
	}

	public void setOrgChildId(String orgChildId) {
		this.orgChildId = orgChildId;
	}

	public QOrg getChild() {
		return child;
	}

	public void setChild(QOrg child) {
		this.child = child;
	}

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}
}
