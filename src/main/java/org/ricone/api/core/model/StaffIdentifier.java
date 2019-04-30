package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StaffIdentifierComposite;

import javax.persistence.*;

@Entity
@Table(name = "staffidentifier")
@IdClass(StaffIdentifierComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StaffIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -4307876689810647938L;
	
	@Column(name = "StaffIdentifierRefId", unique = true, nullable = false, length = 64)
	@Id private String staffIdentifierRefId;
	
	@Column(name = "StaffIdentifierSchoolYear", length = 6)
	@Id private Integer staffIdentifierSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StaffRefId", referencedColumnName="staffRefId", nullable = false),
		@JoinColumn(name="StaffSchoolYear", referencedColumnName="staffSchoolYear", nullable = false)
	})
	private Staff staff;
	
	@Column(name = "IdentificationSystemCode", length = 50)
	private String identificationSystemCode;
	
	@Column(name = "StaffId", length = 50)
	private String staffId;

	public StaffIdentifier() {
	}

	public StaffIdentifier(String staffIdentifierRefId, Integer staffIdentifierSchoolYear, Staff staff, String identificationSystemCode, String staffId) {
		super();
		this.staffIdentifierRefId = staffIdentifierRefId;
		this.staffIdentifierSchoolYear = staffIdentifierSchoolYear;
		this.staff = staff;
		this.identificationSystemCode = identificationSystemCode;
		this.staffId = staffId;
	}

	public String getStaffIdentifierRefId() {
		return this.staffIdentifierRefId;
	}
	public void setStaffIdentifierRefId(String staffIdentifierRefId) {
		this.staffIdentifierRefId = staffIdentifierRefId;
	}

	public Integer getStaffIdentifierSchoolYear() {
		return staffIdentifierSchoolYear;
	}
	public void setStaffIdentifierSchoolYear(Integer staffIdentifierSchoolYear) {
		this.staffIdentifierSchoolYear = staffIdentifierSchoolYear;
	}

	public Staff getStaff() {
		return this.staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getIdentificationSystemCode() {
		return this.identificationSystemCode;
	}
	public void setIdentificationSystemCode(String identificationSystemCode) {
		this.identificationSystemCode = identificationSystemCode;
	}

	public String getStaffId() {
		return this.staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Override
	public String toString() {
		return "StaffIdentifier [staffIdentifierRefId=" + staffIdentifierRefId + ", staffIdentifierSchoolYear=" + staffIdentifierSchoolYear + ", staff=" + staff + ", identificationSystemCode=" + identificationSystemCode + ", staffId=" + staffId + "]";
	}
}
