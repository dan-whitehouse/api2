package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.CourseSectionComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coursesection")
@IdClass(CourseSectionComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseSection implements java.io.Serializable {
	private static final long serialVersionUID = 2424737254871734955L;
	
	@Column(name = "CourseSectionRefId", unique = true, nullable = false, length = 64)
	@Id
    private String courseSectionRefId;
	
	@Column(name = "CourseSectionSchoolYear", nullable = false, length = 6)
	@Id
    private Integer courseSectionSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolCalendarSessionRefId", referencedColumnName="schoolCalendarSessionRefId", nullable = false),
		@JoinColumn(name="SchoolCalendarSessionSchoolYear", referencedColumnName="schoolCalendarSessionSchoolYear", nullable = false)
	})
	private SchoolCalendarSession schoolCalendarSession;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CourseRefId", referencedColumnName="courseRefId", nullable = false),
		@JoinColumn(name="CourseSchoolYear", referencedColumnName="courseSchoolYear", nullable = false)
	})
	private Course course;
	
	@Column(name = "SchoolSectionId", length = 50)
	private String schoolSectionId;
	
	@Column(name = "VendorSectionId", length = 50)
	private String vendorSectionId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseSection")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<CourseSectionSchedule> courseSectionSchedules = new HashSet<CourseSectionSchedule>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseSection")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<StaffCourseSection> staffCourseSections = new HashSet<StaffCourseSection>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseSection")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<StudentCourseSection> studentCourseSections = new HashSet<StudentCourseSection>(0);
	
	public CourseSection() {
	}

	public CourseSection(String courseSectionRefId, Integer courseSectionSchoolYear, SchoolCalendarSession schoolCalendarSession, Course course, String schoolSectionId, String vendorSectionId, Set<CourseSectionSchedule> courseSectionSchedules, Set<StaffCourseSection> staffCourseSections, Set<StudentCourseSection> studentCourseSections) {
		super();
		this.courseSectionRefId = courseSectionRefId;
		this.courseSectionSchoolYear = courseSectionSchoolYear;
		this.schoolCalendarSession = schoolCalendarSession;
		this.course = course;
		this.schoolSectionId = schoolSectionId;
		this.vendorSectionId = vendorSectionId;
		this.courseSectionSchedules = courseSectionSchedules;
		this.staffCourseSections = staffCourseSections;
		this.studentCourseSections = studentCourseSections;
	}

	public String getCourseSectionRefId() {
		return this.courseSectionRefId;
	}
	public void setCourseSectionRefId(String courseSectionRefId) {
		this.courseSectionRefId = courseSectionRefId;
	}
	
	public Integer getCourseSectionSchoolYear() {
		return courseSectionSchoolYear;
	}
	public void setCourseSectionSchoolYear(Integer courseSectionSchoolYear) {
		this.courseSectionSchoolYear = courseSectionSchoolYear;
	}

	public SchoolCalendarSession getSchoolCalendarSession() {
		return this.schoolCalendarSession;
	}
	public void setSchoolCalendarSession(SchoolCalendarSession schoolcalendarsession) {
		this.schoolCalendarSession = schoolcalendarsession;
	}

	public Course getCourse() {
		return this.course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSchoolSectionId() {
		return this.schoolSectionId;
	}
	public void setSchoolSectionId(String schoolSectionId) {
		this.schoolSectionId = schoolSectionId;
	}

	public String getVendorSectionId() {
		return this.vendorSectionId;
	}
	public void setVendorSectionId(String vendorSectionId) {
		this.vendorSectionId = vendorSectionId;
	}
	
	public Set<CourseSectionSchedule> getCourseSectionSchedules() {
		return this.courseSectionSchedules;
	}
	public void setCourseSectionSchedules(Set<CourseSectionSchedule> coursesectionschedules) {
		this.courseSectionSchedules = coursesectionschedules;
	}

	public Set<StaffCourseSection> getStaffCourseSections() {
		return this.staffCourseSections;
	}
	public void setStaffCourseSections(Set<StaffCourseSection> staffcoursesections) {
		this.staffCourseSections = staffcoursesections;
	}

	public Set<StudentCourseSection> getStudentCourseSections() {
		return this.studentCourseSections;
	}
	public void setStudentCourseSections(Set<StudentCourseSection> studentcoursesections) {
		this.studentCourseSections = studentcoursesections;
	}

	@Override
	public String toString() {
		return "CourseSection [courseSectionRefId=" + courseSectionRefId + ", courseSectionSchoolYear=" + courseSectionSchoolYear + ", schoolCalendarSession=" + schoolCalendarSession + ", course=" + course + ", schoolSectionId=" + schoolSectionId + ", vendorSectionId=" + vendorSectionId + ", courseSectionSchedules=" + courseSectionSchedules + ", staffCourseSections=" + staffCourseSections + ", studentCourseSections=" + studentCourseSections + "]";
	}
}
