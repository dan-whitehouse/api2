package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.ricone.api.core.model.composite.SchoolCalendarComposite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schoolcalendar")
@IdClass(SchoolCalendarComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class SchoolCalendar implements Serializable {
	private static final long serialVersionUID = -4748613855950099628L;
	
	@Column(name = "SchoolCalendarRefId", unique = true, nullable = false, length = 64)
	@Id private String schoolCalendarRefId;
	
	@Column(name = "SchoolCalendarSchoolYear", nullable = false, length = 6)
	@Id private Integer schoolCalendarSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolRefId", referencedColumnName="schoolRefId", nullable = false),
		@JoinColumn(name="SchoolSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
	})
	private School school;
	
	@Column(name = "CalendarCode", length = 30)
	private String calendarCode;
	
	@Column(name = "CalendarDescription", nullable = false, length = 60)
	private String calendarDescription;
	
	@Column(name = "CalendarYear", length = 4)
	private String calendarYear;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolCalendar")
	@Fetch(FetchMode.SELECT) @BatchSize(size = 100)
	private Set<SchoolCalendarSession> schoolCalendarSessions = new HashSet<>(0);
	
	public SchoolCalendar() {
	}

	public SchoolCalendar(String schoolCalendarRefId, Integer schoolCalendarSchoolYear, School school, String calendarCode, String calendarDescription, String calendarYear, Set<SchoolCalendarSession> schoolCalendarSessions) {
		super();
		this.schoolCalendarRefId = schoolCalendarRefId;
		this.schoolCalendarSchoolYear = schoolCalendarSchoolYear;
		this.school = school;
		this.calendarCode = calendarCode;
		this.calendarDescription = calendarDescription;
		this.calendarYear = calendarYear;
		this.schoolCalendarSessions = schoolCalendarSessions;
	}

	public String getSchoolCalendarRefId() {
		return this.schoolCalendarRefId;
	}
	public void setSchoolCalendarRefId(String schoolCalendarRefId) {
		this.schoolCalendarRefId = schoolCalendarRefId;
	}
	
	public Integer getSchoolCalendarSchoolYear() {
		return schoolCalendarSchoolYear;
	}
	public void setSchoolCalendarSchoolYear(Integer schoolCalendarSchoolYear) {
		this.schoolCalendarSchoolYear = schoolCalendarSchoolYear;
	}

	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public String getCalendarCode() {
		return this.calendarCode;
	}
	public void setCalendarCode(String calendarCode) {
		this.calendarCode = calendarCode;
	}

	public String getCalendarDescription() {
		return this.calendarDescription;
	}
	public void setCalendarDescription(String calendarDescription) {
		this.calendarDescription = calendarDescription;
	}

	public String getCalendarYear() {
		return this.calendarYear;
	}
	public void setCalendarYear(String calendarYear) {
		this.calendarYear = calendarYear;
	}

	public Set<SchoolCalendarSession> getSchoolCalendarSessions() {
		return this.schoolCalendarSessions;
	}
	public void setSchoolCalendarSessions(Set<SchoolCalendarSession> schoolcalendarsessions) {
		this.schoolCalendarSessions = schoolcalendarsessions;
	}

	@Override
	public String toString() {
		return "SchoolCalendar [schoolCalendarRefId=" + schoolCalendarRefId + ", schoolCalendarSchoolYear=" + schoolCalendarSchoolYear + ", school=" + school + ", calendarCode=" + calendarCode + ", calendarDescription=" + calendarDescription + ", calendarYear=" + calendarYear + ", schoolCalendarSessions=" + schoolCalendarSessions + "]";
	}
}
