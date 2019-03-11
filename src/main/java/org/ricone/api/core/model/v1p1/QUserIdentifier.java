package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "onerosterv1p1_useridentifier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QUserIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "UserIdentifierId", unique = true, nullable = false, length = 64)
	@Id private String userIdentifierId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "UserId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "UserSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QUser user;

	@Column(name = "Code", length = 50)
	private String code;

	@Column(name = "Id", length = 50)
	private String id;

	public QUserIdentifier() {
	}

	public QUserIdentifier(String userIdentifierId, QUser user, String code, String id) {
		this.userIdentifierId = userIdentifierId;
		this.user = user;
		this.code = code;
		this.id = id;
	}

	public String getUserIdentifierId() {
		return userIdentifierId;
	}

	public void setUserIdentifierId(String userIdentifierId) {
		this.userIdentifierId = userIdentifierId;
	}

	public QUser getUser() {
		return user;
	}

	public void setUser(QUser user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
