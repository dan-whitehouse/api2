package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.LeaTelephoneComposite;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "leatelephone")
@IdClass(LeaTelephoneComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LeaTelephone implements Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "LEATelephoneRefId", unique = true, nullable = false, length = 64)
	@Id private String leaTelephoneRefId;

	@Column(name = "LEATelephoneSchoolYear", length = 6)
	@Id private Integer leaTelephoneSchoolYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "LEARefId", referencedColumnName = "leaRefId", nullable = false), @JoinColumn(name = "LEASchoolYear", referencedColumnName = "leaSchoolYear", nullable = false) })
	private Lea lea;

	@Column(name = "TelephoneNumber", nullable = false, length = 24)
	private String telephoneNumber;

	@Column(name = "PrimaryTelephoneNumberIndicator")
	private Boolean primaryTelephoneNumberIndicator;

	@Column(name = "TelephoneNumberTypeCode", length = 50)
	private String telephoneNumberTypeCode;

	public LeaTelephone() {
	}

	public LeaTelephone(String leaTelephoneRefId, Integer leaTelephoneSchoolYear, Lea lea, String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode) {
		this.leaTelephoneRefId = leaTelephoneRefId;
		this.leaTelephoneSchoolYear = leaTelephoneSchoolYear;
		this.lea = lea;
		this.telephoneNumber = telephoneNumber;
		this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
		this.telephoneNumberTypeCode = telephoneNumberTypeCode;
	}

	public String getLeaTelephoneRefId() {
		return this.leaTelephoneRefId;
	}

	public void setLeaTelephoneRefId(String leaTelephoneRefId) {
		this.leaTelephoneRefId = leaTelephoneRefId;
	}

	public Integer getLeaTelephoneSchoolYear() {
		return leaTelephoneSchoolYear;
	}

	public void setLeaTelephoneSchoolYear(Integer leaTelephoneSchoolYear) {
		this.leaTelephoneSchoolYear = leaTelephoneSchoolYear;
	}

	public Lea getLea() {
		return this.lea;
	}

	public void setLea(Lea lea) {
		this.lea = lea;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Boolean getPrimaryTelephoneNumberIndicator() {
		return this.primaryTelephoneNumberIndicator;
	}

	public void setPrimaryTelephoneNumberIndicator(Boolean primaryTelephoneNumberIndicator) {
		this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
	}

	public String getTelephoneNumberTypeCode() {
		return this.telephoneNumberTypeCode;
	}

	public void setTelephoneNumberTypeCode(String telephoneNumberTypeCode) {
		this.telephoneNumberTypeCode = telephoneNumberTypeCode;
	}

	@Override
	public String toString() {
		return "LeaTelephone [leaTelephoneRefId=" + leaTelephoneRefId + ", leaTelephoneSchoolYear=" + leaTelephoneSchoolYear + ", lea=" + lea + ", telephoneNumber=" + telephoneNumber + ", primaryTelephoneNumberIndicator=" + primaryTelephoneNumberIndicator + ", telephoneNumberTypeCode=" + telephoneNumberTypeCode + "]";
	}
}
