package org.ricone.api.core.model.oneroster;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "onerosterv1p1_org")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QOrg implements Serializable {
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

	@Column(name = "Type", length = 8)
	private String type;

	@Column(name = "Name", length = 75)
	private String name;

	@Column(name = "Identifier", length = 50)
	private String identifier;

	@Column(name = "Line1", length = 40)
	private String line1;

	@Column(name = "Line2", length = 40)
	private String line2;

	@Column(name = "City", length = 30)
	private String city;

	@Column(name = "State", length = 50)
	private String state;

	@Column(name = "PostCode", length = 50)
	private String postCode;

	@Column(name = "country", length = 30)
	private String country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId"),
			@JoinColumn(name = "OrgSchoolYear")
	})
	private QOrg org;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "org")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<QOrgChild> children = new HashSet<>();

	public QOrg() {
	}

	public QOrg(String sourcedId, Integer sourcedSchoolYear, String status, LocalDateTime dateLastModified, String districtId, String type, String name, String identifier, String line1, String line2, String city, String state, String postCode, String country, QOrg org, Set<QOrgChild> children) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.districtId = districtId;
		this.type = type;
		this.name = name;
		this.identifier = identifier;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.country = country;
		this.org = org;
		this.children = children;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}

	public Set<QOrgChild> getChildren() {
		return children;
	}

	public void setChildren(Set<QOrgChild> children) {
		this.children = children;
	}
}
