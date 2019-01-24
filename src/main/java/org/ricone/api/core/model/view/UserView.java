package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.view.composite.UserComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@IdClass(UserComposite.class)
@Immutable @Entity @Table(name = "userview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserView implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourcedId")
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear")
	@Id private Integer sourcedSchoolYear;

	@Column(name = "Role")
	@Id private String role;

	@Column(name = "EnabledUser")
	private Boolean enabledUser;

	@Column(name = "GivenName")
	private String givenName;

	@Column(name = "FamilyName")
	private String familyName;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "Identifier")
	private String identifier;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Sms")
	private String sms;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<UserIdentifierView> userIds = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<UserOrgView> userOrgs = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<UserAgentView> userAgents = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<UserClassView> userClasses = new HashSet<>(0);

	public UserView() {
	}

	public UserView(String sourcedId, Integer sourcedSchoolYear, String role, Boolean enabledUser, String givenName, String familyName, String middleName, String identifier, String email, String phone, String sms, Set<UserIdentifierView> userIds, Set<UserOrgView> userOrgs, Set<UserAgentView> userAgents, Set<UserClassView> userClasses) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.role = role;
		this.enabledUser = enabledUser;
		this.givenName = givenName;
		this.familyName = familyName;
		this.middleName = middleName;
		this.identifier = identifier;
		this.email = email;
		this.phone = phone;
		this.sms = sms;
		this.userIds = userIds;
		this.userOrgs = userOrgs;
		this.userAgents = userAgents;
		this.userClasses = userClasses;
	}

	public String getSourcedId() {
		return sourcedId;
	}

	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	public Integer getSourcedSchoolYear() {
		return sourcedSchoolYear;
	}

	public void setSourcedSchoolYear(Integer sourcedSchoolYear) {
		this.sourcedSchoolYear = sourcedSchoolYear;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabledUser() {
		return enabledUser;
	}

	public void setEnabledUser(Boolean enabledUser) {
		this.enabledUser = enabledUser;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public Set<UserIdentifierView> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<UserIdentifierView> userIds) {
		this.userIds = userIds;
	}

	public Set<UserOrgView> getUserOrgs() {
		return userOrgs;
	}

	public void setUserOrgs(Set<UserOrgView> userOrgs) {
		this.userOrgs = userOrgs;
	}

	public Set<UserAgentView> getUserAgents() {
		return userAgents;
	}

	public void setUserAgents(Set<UserAgentView> userAgents) {
		this.userAgents = userAgents;
	}

	public Set<UserClassView> getUserClasses() {
		return userClasses;
	}

	public void setUserClasses(Set<UserClassView> userClasses) {
		this.userClasses = userClasses;
	}
}
