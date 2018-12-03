package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentContactAddressComposite;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactaddress")
@IdClass(StudentContactAddressComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentContactAddress implements java.io.Serializable {
	private static final long serialVersionUID = -4100803613028097486L;
	
	@Column(name = "StudentContactAddressRefId", unique = true, nullable = false, length = 64)
	@Id
    private String studentContactAddressRefId;
	
	@Column(name = "StudentContactAddressSchoolYear", length = 6)
	@Id
    private Integer studentContactAddressSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentContactRefId", referencedColumnName="studentContactRefId", nullable = false),
		@JoinColumn(name="StudentContactSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
	})
	private StudentContact studentContact;
	
	@Column(name = "AddressTypeCode", length = 50)
	private String addressTypeCode;
	
	@Column(name = "StreetNumberAndName", length = 40)
	private String streetNumberAndName;
	
	@Column(name = "ApartmentRoomOrSuiteNumber", length = 30)
	private String apartmentRoomOrSuiteNumber;
	
	@Column(name = "City", length = 30)
	private String city;
	
	@Column(name = "StateCode", length = 50)
	private String stateCode;
	
	@Column(name = "PostalCode", length = 50)
	private String postalCode;
	
	@Column(name = "AddressCountyName", length = 30)
	private String addressCountyName;
	
	@Column(name = "CountryCode", length = 50)
	private String countryCode;

	public StudentContactAddress() {
	}

	public StudentContactAddress(String studentContactAddressRefId, Integer studentContactAddressSchoolYear, StudentContact studentContact, String addressTypeCode, String streetNumberAndName, String apartmentRoomOrSuiteNumber, String city, String stateCode, String postalCode, String addressCountyName, String countryCode) {
		super();
		this.studentContactAddressRefId = studentContactAddressRefId;
		this.studentContactAddressSchoolYear = studentContactAddressSchoolYear;
		this.studentContact = studentContact;
		this.addressTypeCode = addressTypeCode;
		this.streetNumberAndName = streetNumberAndName;
		this.apartmentRoomOrSuiteNumber = apartmentRoomOrSuiteNumber;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.addressCountyName = addressCountyName;
		this.countryCode = countryCode;
	}

	public String getStudentContactAddressRefId() {
		return this.studentContactAddressRefId;
	}
	public void setStudentContactAddressRefId(String studentContactAddressRefId) {
		this.studentContactAddressRefId = studentContactAddressRefId;
	}
	
	public Integer getStudentContactAddressSchoolYear() {
		return studentContactAddressSchoolYear;
	}
	public void setStudentContactAddressSchoolYear(Integer studentContactAddressSchoolYear) {
		this.studentContactAddressSchoolYear = studentContactAddressSchoolYear;
	}

	public StudentContact getStudentContact() {
		return this.studentContact;
	}
	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
	}

	public String getAddressTypeCode() {
		return this.addressTypeCode;
	}
	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public String getStreetNumberAndName() {
		return this.streetNumberAndName;
	}
	public void setStreetNumberAndName(String streetNumberAndName) {
		this.streetNumberAndName = streetNumberAndName;
	}

	public String getApartmentRoomOrSuiteNumber() {
		return this.apartmentRoomOrSuiteNumber;
	}
	public void setApartmentRoomOrSuiteNumber(String apartmentRoomOrSuiteNumber) {
		this.apartmentRoomOrSuiteNumber = apartmentRoomOrSuiteNumber;
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
	
	public String getCountryCode() {
		return this.countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
