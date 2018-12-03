package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StaffEmailComposite;

import javax.persistence.*;

@Entity
@Table(name = "staffemail")
@IdClass(StaffEmailComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StaffEmail implements java.io.Serializable {
	private static final long serialVersionUID = -1105974485352114510L;
	
	@Column(name = "StaffEmailRefId", unique = true, nullable = false, length = 64)
	@Id
    private String staffEmailRefId;
	
	@Column(name = "StaffEmailSchoolYear", length = 6)
	@Id
    private Integer staffEmailSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StaffRefId", referencedColumnName="staffRefId", nullable = false),
		@JoinColumn(name="StaffSchoolYear", referencedColumnName="staffSchoolYear", nullable = false)
	})
	private Staff staff;
	
	@Column(name = "EmailAddress", length = 128)
	private String emailAddress;
	
	@Column(name = "EmailTypeCode", length = 50)
	private String emailTypeCode;
	
	@Column(name = "PrimaryEmailAddressIndicator")
	private Boolean primaryEmailAddressIndicator;

	public StaffEmail() {
	}

	public StaffEmail(String staffEmailRefId, Integer staffEmailSchoolYear, Staff staff, String emailAddress, String emailTypeCode, Boolean primaryEmailAddressIndicator) {
		super();
		this.staffEmailRefId = staffEmailRefId;
		this.staffEmailSchoolYear = staffEmailSchoolYear;
		this.staff = staff;
		this.emailAddress = emailAddress;
		this.emailTypeCode = emailTypeCode;
		this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
	}

	public String getStaffEmailRefId() {
		return this.staffEmailRefId;
	}
	public void setStaffEmailRefId(String staffEmailRefId) {
		this.staffEmailRefId = staffEmailRefId;
	}
	
	public Integer getStaffEmailSchoolYear() {
		return staffEmailSchoolYear;
	}
	public void setStaffEmailSchoolYear(Integer staffEmailSchoolYear) {
		this.staffEmailSchoolYear = staffEmailSchoolYear;
	}

	public Staff getStaff() {
		return this.staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailTypeCode() {
		return this.emailTypeCode;
	}
	public void setEmailTypeCode(String emailTypeCode) {
		this.emailTypeCode = emailTypeCode;
	}

	public Boolean getPrimaryEmailAddressIndicator() {
		return this.primaryEmailAddressIndicator;
	}
	public void setPrimaryEmailAddressIndicator(Boolean primaryEmailAddressIndicator) {
		this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
	}

	@Override
	public String toString() {
		return "StaffEmail [staffEmailRefId=" + staffEmailRefId + ", staffEmailSchoolYear=" + staffEmailSchoolYear + ", staff=" + staff + ", emailAddress=" + emailAddress + ", emailTypeCode=" + emailTypeCode + ", primaryEmailAddressIndicator=" + primaryEmailAddressIndicator + "]";
	}
}
