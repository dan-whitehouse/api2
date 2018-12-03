package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StudentAddressComposite;

import javax.persistence.*;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-03-05
 */

@Entity
@Table(name = "studentaddress")
@IdClass(StudentAddressComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentAddress implements java.io.Serializable {
    private static final long serialVersionUID = -526601546736413275L;

    @Id
    @Column(name = "StudentAddressRefId", unique = true, nullable = false, length = 64)
    private String studentAddressRefId;

    @Id
    @Column(name = "StudentAddressSchoolYear", nullable = false, length = 6)
    private Integer studentAddressSchoolYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "StudentRefId", referencedColumnName = "studentRefId", nullable = false),
	    @JoinColumn(name = "StudentSchoolYear", referencedColumnName = "studentSchoolYear", nullable = false) })
    private Student student;

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

    public StudentAddress() {
    }

    public StudentAddress(String studentAddressRefId, Integer studentAddressSchoolYear, Student student,
                          String addressTypeCode, String streetNumberAndName, String apartmentRoomOrSuiteNumber, String city,
                          String stateCode, String postalCode, String addressCountyName, String countryCode) {
	this.studentAddressRefId = studentAddressRefId;
	this.studentAddressSchoolYear = studentAddressSchoolYear;
	this.student = student;
	this.addressTypeCode = addressTypeCode;
	this.streetNumberAndName = streetNumberAndName;
	this.apartmentRoomOrSuiteNumber = apartmentRoomOrSuiteNumber;
	this.city = city;
	this.stateCode = stateCode;
	this.postalCode = postalCode;
	this.addressCountyName = addressCountyName;
	this.countryCode = countryCode;
    }

    public String getStudentAddressRefId() {
	return this.studentAddressRefId;
    }

    public void setStudentAddressRefId(String studentAddressRefId) {
	this.studentAddressRefId = studentAddressRefId;
    }

    public Integer getStudentAddressSchoolYear() {
	return studentAddressSchoolYear;
    }

    public void setStudentAddressSchoolYear(Integer studentAddressSchoolYear) {
	this.studentAddressSchoolYear = studentAddressSchoolYear;
    }

    public Student getStudent() {
	return this.student;
    }

    public void setStudent(Student student) {
	this.student = student;
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

    @Override
    public String toString() {
	return "StudentAddress [studentAddressRefId=" + studentAddressRefId + ", studentAddressSchoolYear="
		+ studentAddressSchoolYear + ", student=" + student + ", addressTypeCode=" + addressTypeCode
		+ ", streetNumberAndName=" + streetNumberAndName + ", apartmentRoomOrSuiteNumber="
		+ apartmentRoomOrSuiteNumber + ", city=" + city + ", stateCode=" + stateCode + ", postalCode="
		+ postalCode + ", addressCountyName=" + addressCountyName + ", countryCode=" + countryCode + "]";
    }

}
