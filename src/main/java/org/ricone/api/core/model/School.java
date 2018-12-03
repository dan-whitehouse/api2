package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.SchoolComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school")
@IdClass(SchoolComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class School implements java.io.Serializable {
	private static final long serialVersionUID = -4011491046830009714L;
		
	@Column(name = "SchoolRefId", unique = true, nullable = false, length = 64)
	@Id
    private String schoolRefId;
	
	@Column(name = "SchoolSchoolYear", nullable = false, length = 6)
	@Id
    private Integer schoolSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="LEARefId", referencedColumnName="leaRefId", nullable = false),
		@JoinColumn(name="LEASchoolYear", referencedColumnName="leaSchoolYear", nullable = false)
	})
	private Lea lea;
	
	@Column(name = "SchoolName", length = 60)
	private String schoolName;
	
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
	
	@Column(name = "AddressType", length = 32)
	private String addressType;
	
	@Column(name = "Line2", length = 40)
	private String line2;
	
	@Column(name = "CountryCode", length = 50)
	private String countryCode;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<SchoolGrade> schoolGrades = new HashSet<SchoolGrade>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<SchoolTelephone> schoolTelephones = new HashSet<SchoolTelephone>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	private Set<StaffAssignment> staffAssignments = new HashSet<StaffAssignment>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<SchoolIdentifier> schoolIdentifiers = new HashSet<SchoolIdentifier>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<SchoolCalendar> schoolCalendars = new HashSet<SchoolCalendar>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	private Set<StudentEnrollment> studentEnrollments = new HashSet<StudentEnrollment>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<Course> courses = new HashSet<Course>(0);

	public School() {
	}

	public School(String schoolRefId, Integer schoolSchoolYear, Lea lea, String schoolName, String streetNumberAndName, String city, String stateCode, String postalCode, String addressCountyName, String addressType, String line2, String countryCode, Set<SchoolGrade> schoolGrades, Set<SchoolTelephone> schoolTelephones, Set<StaffAssignment> staffAssignments, Set<SchoolIdentifier> schoolIdentifiers, Set<SchoolCalendar> schoolCalendars, Set<StudentEnrollment> studentEnrollments, Set<Course> courses) {
		super();
		this.schoolRefId = schoolRefId;
		this.schoolSchoolYear = schoolSchoolYear;
		this.lea = lea;
		this.schoolName = schoolName;
		this.streetNumberAndName = streetNumberAndName;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.addressCountyName = addressCountyName;
		this.addressType = addressType;
		this.line2 = line2;
		this.countryCode = countryCode;
		this.schoolGrades = schoolGrades;
		this.schoolTelephones = schoolTelephones;
		this.staffAssignments = staffAssignments;
		this.schoolIdentifiers = schoolIdentifiers;
		this.schoolCalendars = schoolCalendars;
		this.studentEnrollments = studentEnrollments;
		this.courses = courses;
	}

	public String getSchoolRefId() {
		return this.schoolRefId;
	}
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}
	
	public Integer getSchoolSchoolYear() {
		return schoolSchoolYear;
	}
	public void setSchoolSchoolYear(Integer schoolSchoolYear) {
		this.schoolSchoolYear = schoolSchoolYear;
	}

	public Lea getLea() {
		return this.lea;
	}
	public void setLea(Lea lea) {
		this.lea = lea;
	}

	public String getSchoolName() {
		return this.schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	
	public Set<SchoolGrade> getSchoolGrades() {
		return this.schoolGrades;
	}
	public void setSchoolGrades(Set<SchoolGrade> schoolgrades) {
		this.schoolGrades = schoolgrades;
	}

	public Set<SchoolTelephone> getSchoolTelephones() {
		return this.schoolTelephones;
	}
	public void setSchoolTelephones(Set<SchoolTelephone> schooltelephones) {
		this.schoolTelephones = schooltelephones;
	}

	public Set<StaffAssignment> getStaffAssignments() {
		return this.staffAssignments;
	}
	public void setStaffAssignments(Set<StaffAssignment> staffassignments) {
		this.staffAssignments = staffassignments;
	}

	public Set<SchoolIdentifier> getSchoolIdentifiers() {
		return this.schoolIdentifiers;
	}
	public void setSchoolIdentifiers(Set<SchoolIdentifier> schoolidentifiers) {
		this.schoolIdentifiers = schoolidentifiers;
	}

	public Set<SchoolCalendar> getSchoolCalendars() {
		return this.schoolCalendars;
	}
	public void setSchoolCalendars(Set<SchoolCalendar> schoolcalendars) {
		this.schoolCalendars = schoolcalendars;
	}

	public Set<StudentEnrollment> getStudentEnrollments() {
		return this.studentEnrollments;
	}
	public void setStudentEnrollments(Set<StudentEnrollment> studentenrollments) {
		this.studentEnrollments = studentenrollments;
	}

	public Set<Course> getCourses() {
		return this.courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "School [schoolRefId=" + schoolRefId + ", schoolSchoolYear=" + schoolSchoolYear + ", lea=" + lea + ", schoolName=" + schoolName + ", streetNumberAndName=" + streetNumberAndName + ", city=" + city + ", stateCode=" + stateCode + ", postalCode=" + postalCode + ", addressCountyName=" + addressCountyName + ", addressType=" + addressType + ", line2=" + line2 + ", countryCode=" + countryCode + ", schoolGrades=" + schoolGrades + ", schoolTelephones=" + schoolTelephones + ", staffAssignments=" + staffAssignments + ", schoolIdentifiers=" + schoolIdentifiers + ", schoolCalendars=" + schoolCalendars + ", studentEnrollments=" + studentEnrollments + ", courses=" + courses + "]";
	}
}
