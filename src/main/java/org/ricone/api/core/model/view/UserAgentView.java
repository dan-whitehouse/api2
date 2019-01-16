package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.UserAgentComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(UserAgentComposite.class)
@Immutable @Entity @Table(name = "useragentview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserAgentView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "AgentId")
	@Id private String agentId;

	@Column(name = "AgentSchoolYear")
	@Id private Integer agentSchoolYear;

	@Column(name = "AgentType")
	private String agentType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourceId", referencedColumnName="sourceId", nullable = false),
			@JoinColumn(name="SourceSchoolYear", referencedColumnName="sourceSchoolYear", nullable = false),
			@JoinColumn(name="SourceRole", referencedColumnName="role", nullable = false)
	})
	private UserView userView;

	public UserAgentView() {
	}

	public UserAgentView(String agentId, Integer agentSchoolYear, String agentType, UserView userView) {
		this.agentId = agentId;
		this.agentSchoolYear = agentSchoolYear;
		this.agentType = agentType;
		this.userView = userView;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Integer getAgentSchoolYear() {
		return agentSchoolYear;
	}

	public void setAgentSchoolYear(Integer agentSchoolYear) {
		this.agentSchoolYear = agentSchoolYear;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
}
