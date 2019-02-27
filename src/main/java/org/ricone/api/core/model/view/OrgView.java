package org.ricone.api.core.model.view;


import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.ricone.api.core.model.Lea;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Immutable @Entity @Table(name = "orgview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@BatchSize(size = 100)
public class OrgView implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourcedId")
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear")
	@Id private Integer sourcedSchoolYear;

	@Column(name = "DistrictId")
	private String districtId;

	@Column(name = "Type", length = 30)
	private String type;

	@Column(name = "Name", length = 30)
	private String name;

	@Column(name = "Identifier", length = 60)
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

	@Column(name = "Country", length = 50)
	private String country;

	@Column(name = "ParentId")
	private String parentId;

	@Column(name = "ParentSchoolYear")
	private Integer parentSchoolYear;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgView")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<OrgChildrenView> children = new HashSet<>(0);

	public OrgView() {
	}

	public OrgView(String sourcedId, Integer sourcedSchoolYear, String districtId, String type, String name, String identifier, String line1, String line2, String city, String state, String postCode, String country, String parentId, Integer parentSchoolYear, Set<OrgChildrenView> children) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
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
		this.parentId = parentId;
		this.parentSchoolYear = parentSchoolYear;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getParentSchoolYear() {
		return parentSchoolYear;
	}

	public void setParentSchoolYear(Integer parentSchoolYear) {
		this.parentSchoolYear = parentSchoolYear;
	}

	public Set<OrgChildrenView> getChildren() {
		return children;
	}

	public void setChildren(Set<OrgChildrenView> children) {
		this.children = children;
	}
}
