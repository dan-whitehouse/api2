package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.UserIdentifierComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(UserIdentifierComposite.class)
@Immutable @Entity @Table(name = "useridentifierview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserIdentifierView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "IdentifierId")
	@Id private String identifierId;

	@Column(name = "IdentifierSchoolYear")
	@Id private Integer identifierSchoolYear;

	@Column(name = "Code")
	private String code;

	@Column(name = "Id")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
			@JoinColumn(name="SourcedRole", referencedColumnName="role", nullable = false)
	})
	private UserView userView;

	public UserIdentifierView() {
	}

	public UserIdentifierView(String identifierId, Integer identifierSchoolYear, String code, String id, UserView userView) {
		this.identifierId = identifierId;
		this.identifierSchoolYear = identifierSchoolYear;
		this.code = code;
		this.id = id;
		this.userView = userView;
	}

	public String getIdentifierId() {
		return identifierId;
	}

	public void setIdentifierId(String identifierId) {
		this.identifierId = identifierId;
	}

	public Integer getIdentifierSchoolYear() {
		return identifierSchoolYear;
	}

	public void setIdentifierSchoolYear(Integer identifierSchoolYear) {
		this.identifierSchoolYear = identifierSchoolYear;
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

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}
}
