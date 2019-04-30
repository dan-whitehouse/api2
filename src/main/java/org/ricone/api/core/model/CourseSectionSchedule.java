package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.CourseSectionScheduleComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "coursesectionschedule")
@IdClass(CourseSectionScheduleComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseSectionSchedule implements Serializable {
	private static final long serialVersionUID = 3034286521311489646L;
	
	@Column(name = "CourseSectionScheduleRefId", unique = true, nullable = false, length = 64)
	@Id private String courseSectionScheduleRefId;
	
	@Column(name = "CourseSectionScheduleSchoolYear", nullable = false, length = 6)
	@Id private Integer courseSectionScheduleSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CourseSectionRefId", referencedColumnName="courseSectionRefId", nullable = false),
		@JoinColumn(name="CourseSectionSchoolYear", referencedColumnName="courseSectionSchoolYear", nullable = false)
	})
	private CourseSection courseSection;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolCalendarSessionRefId", referencedColumnName="schoolCalendarSessionRefId", nullable = false),
		@JoinColumn(name="SchoolCalendarSessionSchoolYear", referencedColumnName="schoolCalendarSessionSchoolYear", nullable = false)
	})
	private SchoolCalendarSession schoolCalendarSession;
	
	@Column(name = "ClassMeetingDays", length = 60)
	private String classMeetingDays;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "ClassBeginningTime", length = 8)
	private Date classBeginningTime;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "ClassEndingTime", length = 8)
	private Date classEndingTime;
	
	@Column(name = "ClassPeriod", length = 30)
	private String classPeriod;
	
	@Column(name = "TimeDayIdentifier", length = 30)
	private String timeDayIdentifier;
	
	@Column(name = "ClassroomIdentifier", length = 30)
	private String classroomIdentifier;
	
	public CourseSectionSchedule() {		
	}

	public CourseSectionSchedule(String courseSectionScheduleRefId, Integer courseSectionScheduleSchoolYear, CourseSection courseSection, SchoolCalendarSession schoolCalendarSession, String classMeetingDays, Date classBeginningTime, Date classEndingTime, String classPeriod, String timeDayIdentifier, String classroomIdentifier) {
		super();
		this.courseSectionScheduleRefId = courseSectionScheduleRefId;
		this.courseSectionScheduleSchoolYear = courseSectionScheduleSchoolYear;
		this.courseSection = courseSection;
		this.schoolCalendarSession = schoolCalendarSession;
		this.classMeetingDays = classMeetingDays;
		this.classBeginningTime = classBeginningTime;
		this.classEndingTime = classEndingTime;
		this.classPeriod = classPeriod;
		this.timeDayIdentifier = timeDayIdentifier;
		this.classroomIdentifier = classroomIdentifier;
	}

	public String getCourseSectionScheduleRefId() {
		return this.courseSectionScheduleRefId;
	}
	public void setCourseSectionScheduleRefId(String courseSectionScheduleRefId) {
		this.courseSectionScheduleRefId = courseSectionScheduleRefId;
	}

	public CourseSection getCourseSection() {
		return this.courseSection;
	}
	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}
	
	public Integer getCourseSectionScheduleSchoolYear() {
		return courseSectionScheduleSchoolYear;
	}
	public void setCourseSectionScheduleSchoolYear(Integer courseSectionScheduleSchoolYear) {
		this.courseSectionScheduleSchoolYear = courseSectionScheduleSchoolYear;
	}

	public SchoolCalendarSession getSchoolCalendarSession() {
		return this.schoolCalendarSession;
	}
	public void setSchoolCalendarSession(SchoolCalendarSession schoolcalendarsession) {
		this.schoolCalendarSession = schoolcalendarsession;
	}

	public String getClassMeetingDays() {
		return this.classMeetingDays;
	}
	public void setClassMeetingDays(String classMeetingDays) {
		this.classMeetingDays = classMeetingDays;
	}

	public Date getClassBeginningTime() {
		return this.classBeginningTime;
	}
	public void setClassBeginningTime(Date classBeginningTime) {
		this.classBeginningTime = classBeginningTime;
	}

	public Date getClassEndingTime() {
		return this.classEndingTime;
	}
	public void setClassEndingTime(Date classEndingTime) {
		this.classEndingTime = classEndingTime;
	}

	public String getClassPeriod() {
		return this.classPeriod;
	}
	public void setClassPeriod(String classPeriod) {
		this.classPeriod = classPeriod;
	}

	public String getTimeDayIdentifier() {
		return this.timeDayIdentifier;
	}
	public void setTimeDayIdentifier(String timeDayIdentifier) {
		this.timeDayIdentifier = timeDayIdentifier;
	}
	
	public String getClassroomIdentifier() {
		return this.classroomIdentifier;
	}
	public void setClassroomIdentifier(String classroomIdentifier) {
		this.classroomIdentifier = classroomIdentifier;
	}

	@Override
	public String toString() {
		return "CourseSectionSchedule [courseSectionScheduleRefId=" + courseSectionScheduleRefId + ", courseSectionScheduleSchoolYear=" + courseSectionScheduleSchoolYear + ", courseSection=" + courseSection + ", schoolCalendarSession=" + schoolCalendarSession + ", classMeetingDays=" + classMeetingDays + ", classBeginningTime=" + classBeginningTime + ", classEndingTime=" + classEndingTime + ", classPeriod=" + classPeriod + ", timeDayIdentifier=" + timeDayIdentifier + ", classroomIdentifier=" + classroomIdentifier + "]";
	}
}
