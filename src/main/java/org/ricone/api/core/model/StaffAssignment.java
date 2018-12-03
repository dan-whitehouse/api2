package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StaffAssignmentComposite;

import javax.persistence.*;

@Entity
@Table(name = "staffassignment")
@IdClass(StaffAssignmentComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StaffAssignment implements java.io.Serializable {
	private static final long serialVersionUID = -3255881408462782727L;
	
	@Column(name = "StaffAssignmentRefId", unique = true, nullable = false, length = 64)
	@Id
    private String staffAssignmentRefId;
	
	@Column(name = "StaffAssignmentSchoolYear", length = 6)
	@Id
    private Integer staffAssignmentSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StaffRefId", referencedColumnName="staffRefId", nullable = false),
		@JoinColumn(name="StaffSchoolYear", referencedColumnName="staffSchoolYear", nullable = false)
	})
	private Staff staff;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolRefId", referencedColumnName="schoolRefId", nullable = false),
		@JoinColumn(name="SchoolSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
	})
	private School school;
	
	@Column(name = "PrimaryAssignment")
	private Boolean primaryAssignment;
	
	@Column(name = "PositionTitle", length = 45)
	private String positionTitle;

	public StaffAssignment() {
	}

	public StaffAssignment(String staffAssignmentRefId, Integer staffAssignmentSchoolYear, Staff staff, School school, Boolean primaryAssignment, String positionTitle) {
		super();
		this.staffAssignmentRefId = staffAssignmentRefId;
		this.staffAssignmentSchoolYear = staffAssignmentSchoolYear;
		this.staff = staff;
		this.school = school;
		this.primaryAssignment = primaryAssignment;
		this.positionTitle = positionTitle;
	}

	public String getStaffAssignmentRefId() {
		return this.staffAssignmentRefId;
	}
	public void setStaffAssignmentRefId(String staffAssignmentRefId) {
		this.staffAssignmentRefId = staffAssignmentRefId;
	}

	public Integer getStaffAssignmentSchoolYear() {
		return staffAssignmentSchoolYear;
	}
	public void setStaffAssignmentSchoolYear(Integer staffAssignmentSchoolYear) {
		this.staffAssignmentSchoolYear = staffAssignmentSchoolYear;
	}

	public Staff getStaff() {
		return this.staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	public Boolean getPrimaryAssignment() {
		return this.primaryAssignment;
	}
	public void setPrimaryAssignment(Boolean primaryAssignment) {
		this.primaryAssignment = primaryAssignment;
	}

	public String getPositionTitle() {
		return this.positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	@Override
	public String toString() {
		return "StaffAssignment [staffAssignmentRefId=" + staffAssignmentRefId + ", staffAssignmentSchoolYear=" + staffAssignmentSchoolYear + ", staff=" + staff + ", school=" + school + ", primaryAssignment=" + primaryAssignment + ", positionTitle=" + positionTitle + "]";
	}
}
