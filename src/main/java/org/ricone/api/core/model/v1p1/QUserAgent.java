package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "OneRosterV1P1_UserAgent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QUserAgent implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "UserAgentId", unique = true, nullable = false, length = 64)
	@Id private String userAgentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "UserId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "UserSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "AgentId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "AgentSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QUser agent;

	public QUserAgent() {
	}

	public QUserAgent(String userAgentId, QUser user, QUser agent) {
		this.userAgentId = userAgentId;
		this.user = user;
		this.agent = agent;
	}

	public String getUserAgentId() {
		return userAgentId;
	}

	public void setUserAgentId(String userAgentId) {
		this.userAgentId = userAgentId;
	}

	public QUser getUser() {
		return user;
	}

	public void setUser(QUser user) {
		this.user = user;
	}

	public QUser getAgent() {
		return agent;
	}

	public void setAgent(QUser agent) {
		this.agent = agent;
	}
}
