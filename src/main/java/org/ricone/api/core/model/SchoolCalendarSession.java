package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.SchoolCalendarSessionComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schoolcalendarsession")
@IdClass(SchoolCalendarSessionComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SchoolCalendarSession implements java.io.Serializable {
	private static final long serialVersionUID = -5166710493348669924L;
	
	@Column(name = "SchoolCalendarSessionRefId", unique = true, nullable = false, length = 64)
	@Id
    private String schoolCalendarSessionRefId;

	@Column(name = "SchoolCalendarSessionSchoolYear", nullable = false, length = 6)
	@Id
    private Integer schoolCalendarSessionSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolCalendarRefId", referencedColumnName="schoolCalendarRefId", nullable = false),
		@JoinColumn(name="SchoolCalendarSchoolYear", referencedColumnName="schoolCalendarSchoolYear", nullable = false)
	})
	private SchoolCalendar schoolCalendar;
	
	@Column(name = "Designator", length = 7)
	private String designator;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BeginDate", length = 10)
	private Date beginDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "EndDate", length = 10)
	private Date endDate;
	
	@Column(name = "SessionTypeCode", length = 50)
	private String sessionTypeCode;
	
	@Column(name = "InstructionalMinutes")
	private Long instructionalMinutes;
	
	@Column(name = "Code", length = 30)
	private String code;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "MarkingTermIndicator")
	private Boolean markingTermIndicator;
	
	@Column(name = "SchedulingTermIndicator")
	private Boolean schedulingTermIndicator;
	
	@Column(name = "AttendanceTermIndicator")
	private Boolean attendanceTermIndicator;
	
	@Column(name = "DaysInSession")
	private Integer daysInSession;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FirstInstructionDate", length = 10)
	private Date firstInstructionDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LastInstructionDate", length = 10)
	private Date lastInstructionDate;
	
	@Column(name = "MinutesPerDay")
	private Integer minutesPerDay;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "SessionStartTime", length = 8)
	private Date sessionStartTime;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "SessionEndTime", length = 8)
	private Date sessionEndTime;
	
	@Column(name = "LinkedSessionCode", length = 30)
	private String linkedSessionCode;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolCalendarSession")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<CourseSection> courseSections = new HashSet<CourseSection>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolCalendarSession")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	private Set<CourseSectionSchedule> courseSectionSchedules = new HashSet<CourseSectionSchedule>(0);

	public SchoolCalendarSession() {
	}

	public SchoolCalendarSession(String schoolCalendarSessionRefId, Integer schoolCalendarSessionSchoolYear, SchoolCalendar schoolCalendar, String designator, Date beginDate, Date endDate, String sessionTypeCode, Long instructionalMinutes, String code, String description, Boolean markingTermIndicator, Boolean schedulingTermIndicator, Boolean attendanceTermIndicator, Integer daysInSession, Date firstInstructionDate, Date lastInstructionDate, Integer minutesPerDay, Date sessionStartTime, Date sessionEndTime, String linkedSessionCode, Set<CourseSection> courseSections, Set<CourseSectionSchedule> courseSectionSchedules) {
		super();
		this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
		this.schoolCalendarSessionSchoolYear = schoolCalendarSessionSchoolYear;
		this.schoolCalendar = schoolCalendar;
		this.designator = designator;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.sessionTypeCode = sessionTypeCode;
		this.instructionalMinutes = instructionalMinutes;
		this.code = code;
		this.description = description;
		this.markingTermIndicator = markingTermIndicator;
		this.schedulingTermIndicator = schedulingTermIndicator;
		this.attendanceTermIndicator = attendanceTermIndicator;
		this.daysInSession = daysInSession;
		this.firstInstructionDate = firstInstructionDate;
		this.lastInstructionDate = lastInstructionDate;
		this.minutesPerDay = minutesPerDay;
		this.sessionStartTime = sessionStartTime;
		this.sessionEndTime = sessionEndTime;
		this.linkedSessionCode = linkedSessionCode;
		this.courseSections = courseSections;
		this.courseSectionSchedules = courseSectionSchedules;
	}

	public String getSchoolCalendarSessionRefId() {
		return this.schoolCalendarSessionRefId;
	}
	public void setSchoolCalendarSessionRefId(String schoolCalendarSessionRefId) {
		this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
	}
	
	public Integer getSchoolCalendarSessionSchoolYear() {
		return schoolCalendarSessionSchoolYear;
	}
	public void setSchoolCalendarSessionSchoolYear(Integer schoolCalendarSessionSchoolYear) {
		this.schoolCalendarSessionSchoolYear = schoolCalendarSessionSchoolYear;
	}

	public SchoolCalendar getSchoolCalendar() {
		return this.schoolCalendar;
	}
	public void setSchoolCalendar(SchoolCalendar schoolCalendar) {
		this.schoolCalendar = schoolCalendar;
	}

	public String getDesignator() {
		return this.designator;
	}
	public void setDesignator(String designator) {
		this.designator = designator;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSessionTypeCode() {
		return this.sessionTypeCode;
	}
	public void setSessionTypeCode(String sessionTypeCode) {
		this.sessionTypeCode = sessionTypeCode;
	}

	public Long getInstructionalMinutes() {
		return this.instructionalMinutes;
	}
	public void setInstructionalMinutes(Long instructionalMinutes) {
		this.instructionalMinutes = instructionalMinutes;
	}

	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getMarkingTermIndicator() {
		return this.markingTermIndicator;
	}
	public void setMarkingTermIndicator(Boolean markingTermIndicator) {
		this.markingTermIndicator = markingTermIndicator;
	}

	public Boolean getSchedulingTermIndicator() {
		return this.schedulingTermIndicator;
	}
	public void setSchedulingTermIndicator(Boolean schedulingTermIndicator) {
		this.schedulingTermIndicator = schedulingTermIndicator;
	}

	public Boolean getAttendanceTermIndicator() {
		return this.attendanceTermIndicator;
	}
	public void setAttendanceTermIndicator(Boolean attendanceTermIndicator) {
		this.attendanceTermIndicator = attendanceTermIndicator;
	}

	public Integer getDaysInSession() {
		return this.daysInSession;
	}
	public void setDaysInSession(Integer daysInSession) {
		this.daysInSession = daysInSession;
	}

	public Date getFirstInstructionDate() {
		return this.firstInstructionDate;
	}
	public void setFirstInstructionDate(Date firstInstructionDate) {
		this.firstInstructionDate = firstInstructionDate;
	}

	public Date getLastInstructionDate() {
		return this.lastInstructionDate;
	}
	public void setLastInstructionDate(Date lastInstructionDate) {
		this.lastInstructionDate = lastInstructionDate;
	}

	public Integer getMinutesPerDay() {
		return this.minutesPerDay;
	}
	public void setMinutesPerDay(Integer minutesPerDay) {
		this.minutesPerDay = minutesPerDay;
	}

	public Date getSessionStartTime() {
		return this.sessionStartTime;
	}
	public void setSessionStartTime(Date sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public Date getSessionEndTime() {
		return this.sessionEndTime;
	}
	public void setSessionEndTime(Date sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	public String getLinkedSessionCode() {
		return this.linkedSessionCode;
	}
	public void setLinkedSessionCode(String linkedSessionCode) {
		this.linkedSessionCode = linkedSessionCode;
	}

	public Set<CourseSection> getCourseSections() {
		return this.courseSections;
	}
	public void setCourseSections(Set<CourseSection> coursesections) {
		this.courseSections = coursesections;
	}

	public Set<CourseSectionSchedule> getCourseSectionSchedules() {
		return this.courseSectionSchedules;
	}
	public void setCourseSectionSchedules(Set<CourseSectionSchedule> courseSectionSchedules) {
		this.courseSectionSchedules = courseSectionSchedules;
	}

	@Override
	public String toString() {
		return "SchoolCalendarSession [schoolCalendarSessionRefId=" + schoolCalendarSessionRefId + ", schoolCalendarSessionSchoolYear=" + schoolCalendarSessionSchoolYear + ", schoolCalendar=" + schoolCalendar + ", designator=" + designator + ", beginDate=" + beginDate + ", endDate=" + endDate + ", sessionTypeCode=" + sessionTypeCode + ", instructionalMinutes=" + instructionalMinutes + ", code=" + code + ", description=" + description + ", markingTermIndicator=" + markingTermIndicator + ", schedulingTermIndicator=" + schedulingTermIndicator + ", attendanceTermIndicator=" + attendanceTermIndicator + ", daysInSession=" + daysInSession + ", firstInstructionDate=" + firstInstructionDate + ", lastInstructionDate=" + lastInstructionDate + ", minutesPerDay=" + minutesPerDay + ", sessionStartTime=" + sessionStartTime + ", sessionEndTime=" + sessionEndTime + ", linkedSessionCode=" + linkedSessionCode + ", courseSections=" + courseSections + ", courseSectionSchedules=" + courseSectionSchedules + "]";
	}
}
