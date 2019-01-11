package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.Lea;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "orgview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OrgView implements java.io.Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourceId", length = 30)
	@Id private String sourceId;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="LEARefId", referencedColumnName="leaRefId"),
			@JoinColumn(name="LEASchoolYear", referencedColumnName="leaSchoolYear")
	})
	private Lea lea;

	public OrgView() {
	}

	public OrgView(String sourceId, String type, String name, String identifier, String line1, String line2, String city, String state, String postCode, String country, Lea lea) {
		this.sourceId = sourceId;
		this.type = type;
		this.name = name;
		this.identifier = identifier;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.country = country;
		this.lea = lea;
	}

	public Lea getLea() {
		return lea;
	}

	public void setLea(Lea lea) {
		this.lea = lea;
	}

	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
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


}
