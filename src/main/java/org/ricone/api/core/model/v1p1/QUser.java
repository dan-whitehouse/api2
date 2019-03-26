package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "onerosterv1p1_user")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QUser implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourcedId", unique = true, nullable = false, length = 64)
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear", nullable = false, length = 6)
	@Id private Integer sourcedSchoolYear;

	@Column(name = "Status", length = 20)
	private String status;

	@Column(name = "DateLastModified")
	private LocalDateTime dateLastModified;

	@Column(name = "DistrictId", length = 30)
	private String districtId;

	@Column(name = "Role", length = 30)
	private String role;

	@Column(name = "EnabledUser")
	private Boolean enabledUser;

	@Column(name = "GivenName", length = 75)
	private String givenName;

	@Column(name = "FamilyName", length = 50)
	private String familyName;

	@Column(name = "middleName", length = 40)
	private String middleName;

	@Column(name = "Identifier", length = 40)
	private String identifier;

	@Column(name = "Email", length = 30)
	private String email;

	@Column(name = "Phone", length = 50)
	private String phone;

	@Column(name = "Sms", length = 50)
	private String sms;

	@Column(name = "Grades", length = 30)
	private String grades;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QUserAgent> agents = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QUserClass> classes = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QUserIdentifier> identifiers = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QUserOrg> orgs = new HashSet<>();

	public QUser() {
	}

	public QUser(String sourcedId, Integer sourcedSchoolYear, String status, LocalDateTime dateLastModified, String districtId, String role, Boolean enabledUser, String givenName, String familyName, String middleName, String identifier, String email, String phone, String sms, String grades, Set<QUserAgent> agents, Set<QUserClass> classes, Set<QUserIdentifier> identifiers, Set<QUserOrg> orgs) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.districtId = districtId;
		this.role = role;
		this.enabledUser = enabledUser;
		this.givenName = givenName;
		this.familyName = familyName;
		this.middleName = middleName;
		this.identifier = identifier;
		this.email = email;
		this.phone = phone;
		this.sms = sms;
		this.grades = grades;
		this.agents = agents;
		this.classes = classes;
		this.identifiers = identifiers;
		this.orgs = orgs;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(LocalDateTime dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public Set<QUserAgent> getAgents() {
		return agents;
	}

	public void setAgents(Set<QUserAgent> agents) {
		this.agents = agents;
	}

	public Set<QUserClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<QUserClass> classes) {
		this.classes = classes;
	}

	public Set<QUserIdentifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<QUserIdentifier> identifiers) {
		this.identifiers = identifiers;
	}

	public Set<QUserOrg> getOrgs() {
		return orgs;
	}

	public void setOrgs(Set<QUserOrg> orgs) {
		this.orgs = orgs;
	}
}
