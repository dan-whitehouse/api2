package org.ricone.api.core.model;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.LeaComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lea")
@IdClass(LeaComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class Lea implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "LEARefId", unique = true, nullable = false, length = 64)
	@Id private String leaRefId;

	@Column(name = "LEASchoolYear", nullable = false, length = 6)
	@Id private Integer leaSchoolYear;

	@Column(name = "LEAId", length = 30)
	private String leaId;

	@Column(name = "LEASEAId", length = 30)
	private String leaSeaId;

	@Column(name = "LEANCESId", length = 30)
	private String leaNcesId;

	@Column(name = "LEAName", length = 60)
	private String leaName;

	@Column(name = "StreetNumberAndName", length = 40)
	private String streetNumberAndName;

	@Column(name = "City", length = 30)
	private String city;

	@Column(name = "StateCode", length = 50)
	private String stateCode;

	@Column(name = "PostalCode", length = 50)
	private String postalCode;

	@Column(name = "AddressCountyName", length = 30)
	private String addressCountyName;

	@Column(name = "LEAVendorId", length = 30)
	private String leaVendorId;

	@Column(name = "AddressType", length = 32)
	private String addressType;

	@Column(name = "Line2", length = 40)
	private String line2;

	@Column(name = "CountryCode", length = 50)
	private String countryCode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lea")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<School> schools = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lea")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<LeaTelephone> leaTelephones = new HashSet<>(0);


	public Lea() {
	}

	public Lea(String leaRefId, Integer leaSchoolYear, String leaId, String leaSeaId, String leaNcesId, String leaName, String streetNumberAndName, String city, String stateCode, String postalCode, String addressCountyName, String leaVendorId, String addressType, String line2, String countryCode, Set<School> schools, Set<LeaTelephone> leaTelephones) {
		this.leaRefId = leaRefId;
		this.leaSchoolYear = leaSchoolYear;
		this.leaId = leaId;
		this.leaSeaId = leaSeaId;
		this.leaNcesId = leaNcesId;
		this.leaName = leaName;
		this.streetNumberAndName = streetNumberAndName;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.addressCountyName = addressCountyName;
		this.leaVendorId = leaVendorId;
		this.addressType = addressType;
		this.line2 = line2;
		this.countryCode = countryCode;
		this.schools = schools;
		this.leaTelephones = leaTelephones;
	}


	public String getLeaRefId() {
		return this.leaRefId;
	}
	public void setLeaRefId(String learefId) {
		this.leaRefId = learefId;
	}

	public Integer getLeaSchoolYear() { return leaSchoolYear; }
	public void setLeaSchoolYear(Integer leaSchoolYear) { this.leaSchoolYear = leaSchoolYear; }

	public String getLeaId() {
		return this.leaId;
	}
	public void setLeaId(String leaid) {
		this.leaId = leaid;
	}

	public String getLeaSeaId() {
		return this.leaSeaId;
	}
	public void setLeaSeaId(String leaseaid) {
		this.leaSeaId = leaseaid;
	}

	public String getLeaNcesId() {
		return this.leaNcesId;
	}
	public void setLeaNcesId(String leancesid) {
		this.leaNcesId = leancesid;
	}

	public String getLeaName() {
		return this.leaName;
	}
	public void setLeaName(String leaname) {
		this.leaName = leaname;
	}

	public String getStreetNumberAndName() {
		return this.streetNumberAndName;
	}
	public void setStreetNumberAndName(String streetNumberAndName) {
		this.streetNumberAndName = streetNumberAndName;
	}

	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return this.stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPostalCode() {
		return this.postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddressCountyName() {
		return this.addressCountyName;
	}
	public void setAddressCountyName(String addressCountyName) {
		this.addressCountyName = addressCountyName;
	}

	public String getLeaVendorId() {
		return this.leaVendorId;
	}
	public void setLeaVendorId(String leavendorId) {
		this.leaVendorId = leavendorId;
	}

	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Set<School> getSchools() {
		return this.schools;
	}
	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}

	public Set<LeaTelephone> getLeaTelephones() {
		return this.leaTelephones;
	}
	public void setLeaTelephones(Set<LeaTelephone> leatelephones) {
		this.leaTelephones = leatelephones;
	}


	@Override
	public String toString() {
		return "Lea [leaRefId=" + leaRefId + ", leaSchoolYear=" + leaSchoolYear + ", leaId=" + leaId + ", leaSeaId=" + leaSeaId + ", leaNcesId=" + leaNcesId + ", leaName=" + leaName + ", streetNumberAndName=" + streetNumberAndName + ", city=" + city + ", stateCode=" + stateCode + ", postalCode=" + postalCode + ", addressCountyName=" + addressCountyName + ", leaVendorId=" + leaVendorId + ", addressType=" + addressType + ", line2=" + line2 + ", countryCode=" + countryCode + ", schools=" + schools + ", leaTelephones=" + leaTelephones + "]";
	}	
}
