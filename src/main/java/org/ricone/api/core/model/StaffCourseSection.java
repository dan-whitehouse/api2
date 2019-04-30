package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.StaffCourseSectionComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "staffcoursesection")
@IdClass(StaffCourseSectionComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StaffCourseSection implements Serializable {
	private static final long serialVersionUID = -180003633066217606L;
	
	@Column(name = "StaffCourseSectionRefId", unique = true, nullable = false, length = 64)
	@Id
    private String staffCourseSectionRefId;
	
	@Column(name = "StaffCourseSectionSchoolYear", length = 6)
	@Id
    private Integer staffCourseSectionSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CourseSectionRefId", referencedColumnName="courseSectionRefId", nullable = false),
		@JoinColumn(name="CourseSectionSchoolYear", referencedColumnName="courseSectionSchoolYear", nullable = false)
	})
	private CourseSection courseSection;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StaffRefId", referencedColumnName="staffRefId", nullable = false),
		@JoinColumn(name="StaffSchoolYear", referencedColumnName="staffSchoolYear", nullable = false)
	})
	private Staff staff;
	
	@Column(name = "TeacherOfRecord")
	private Boolean teacherOfRecord;
	
	@Column(name = "ContributionPercentage", precision = 5)
	private BigDecimal contributionPercentage;
	
	@Column(name = "ClassroomPositionType", length = 50)
	private String classroomPositionType;

	public StaffCourseSection() {
	}
	
	public StaffCourseSection(String staffCourseSectionRefId, Integer staffCourseSectionSchoolYear, CourseSection courseSection, Staff staff, Boolean teacherOfRecord, BigDecimal contributionPercentage, String classroomPositionType) {
		super();
		this.staffCourseSectionRefId = staffCourseSectionRefId;
		this.staffCourseSectionSchoolYear = staffCourseSectionSchoolYear;
		this.courseSection = courseSection;
		this.staff = staff;
		this.teacherOfRecord = teacherOfRecord;
		this.contributionPercentage = contributionPercentage;
		this.classroomPositionType = classroomPositionType;
	}

	public String getStaffCourseSectionRefId() {
		return this.staffCourseSectionRefId;
	}
	public void setStaffCourseSectionRefId(String staffCourseSectionRefId) {
		this.staffCourseSectionRefId = staffCourseSectionRefId;
	}
	
	public Integer getStaffCourseSectionSchoolYear() {
		return staffCourseSectionSchoolYear;
	}
	public void setStaffCourseSectionSchoolYear(Integer staffCourseSectionSchoolYear) {
		this.staffCourseSectionSchoolYear = staffCourseSectionSchoolYear;
	}

	public CourseSection getCourseSection() {
		return this.courseSection;
	}
	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}

	public Staff getStaff() {
		return this.staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Boolean getTeacherOfRecord() {
		return this.teacherOfRecord;
	}
	public void setTeacherOfRecord(Boolean teacherOfRecord) {
		this.teacherOfRecord = teacherOfRecord;
	}

	public BigDecimal getContributionPercentage() {
		return this.contributionPercentage;
	}
	public void setContributionPercentage(BigDecimal contributionPercentage) {
		this.contributionPercentage = contributionPercentage;
	}

	public String getClassroomPositionType() {
		return this.classroomPositionType;
	}
	public void setClassroomPositionType(String classroomPositionType) {
		this.classroomPositionType = classroomPositionType;
	}

	@Override
	public String toString() {
		return "StaffCourseSection [staffCourseSectionRefId=" + staffCourseSectionRefId + ", staffCourseSectionSchoolYear=" + staffCourseSectionSchoolYear + ", courseSection=" + courseSection + ", staff=" + staff + ", teacherOfRecord=" + teacherOfRecord + ", contributionPercentage=" + contributionPercentage + ", classroomPositionType=" + classroomPositionType + "]";
	}
}
