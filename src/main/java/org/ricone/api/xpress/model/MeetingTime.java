/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timeTableDay", "classMeetingDays", "timeTablePeriod", "roomNumber", "classBeginningTime", "classEndingTime", "sessionCode", "schoolCalendarRefId"})
public class MeetingTime {

	@JsonProperty("timeTableDay")
	private String timeTableDay;
	@JsonProperty("classMeetingDays")
	private ClassMeetingDays classMeetingDays;
	@JsonProperty("timeTablePeriod")
	private String timeTablePeriod;
	@JsonProperty("roomNumber")
	private String roomNumber;
	@JsonProperty("classBeginningTime")
	private String classBeginningTime;
	@JsonProperty("classEndingTime")
	private String classEndingTime;
	@JsonProperty("sessionCode")
	private String sessionCode;
	@JsonProperty("schoolCalendarRefId")
	private String schoolCalendarRefId;

	public MeetingTime() {
	}

	public MeetingTime(String timeTableDay, ClassMeetingDays classMeetingDays, String timeTablePeriod, String roomNumber, String classBeginningTime, String classEndingTime, String sessionCode, String schoolCalendarRefId) {
		super();
		this.timeTableDay = timeTableDay;
		this.classMeetingDays = classMeetingDays;
		this.timeTablePeriod = timeTablePeriod;
		this.roomNumber = roomNumber;
		this.classBeginningTime = classBeginningTime;
		this.classEndingTime = classEndingTime;
		this.sessionCode = sessionCode;
		this.schoolCalendarRefId = schoolCalendarRefId;
	}

	@JsonProperty("timeTableDay")
	public String getTimeTableDay() {
		return timeTableDay;
	}

	@JsonProperty("timeTableDay")
	public void setTimeTableDay(String timeTableDay) {
		this.timeTableDay = timeTableDay;
	}

	@JsonProperty("classMeetingDays")
	public ClassMeetingDays getClassMeetingDays() {
		return classMeetingDays;
	}

	@JsonProperty("classMeetingDays")
	public void setClassMeetingDays(ClassMeetingDays classMeetingDays) {
		this.classMeetingDays = classMeetingDays;
	}

	@JsonProperty("timeTablePeriod")
	public String getTimeTablePeriod() {
		return timeTablePeriod;
	}

	@JsonProperty("timeTablePeriod")
	public void setTimeTablePeriod(String timeTablePeriod) {
		this.timeTablePeriod = timeTablePeriod;
	}

	@JsonProperty("roomNumber")
	public String getRoomNumber() {
		return roomNumber;
	}

	@JsonProperty("roomNumber")
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@JsonProperty("classBeginningTime")
	public String getClassBeginningTime() {
		return classBeginningTime;
	}

	@JsonProperty("classBeginningTime")
	public void setClassBeginningTime(String classBeginningTime) {
		this.classBeginningTime = classBeginningTime;
	}

	@JsonProperty("classEndingTime")
	public String getClassEndingTime() {
		return classEndingTime;
	}

	@JsonProperty("classEndingTime")
	public void setClassEndingTime(String classEndingTime) {
		this.classEndingTime = classEndingTime;
	}

	@JsonProperty("sessionCode")
	public String getSessionCode() {
		return sessionCode;
	}

	@JsonProperty("sessionCode")
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	@JsonProperty("schoolCalendarRefId")
	public String getSchoolCalendarRefId() {
		return schoolCalendarRefId;
	}

	@JsonProperty("schoolCalendarRefId")
	public void setSchoolCalendarRefId(String schoolCalendarRefId) {
		this.schoolCalendarRefId = schoolCalendarRefId;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(timeTableDay, classMeetingDays, timeTablePeriod, roomNumber, classBeginningTime, classEndingTime, sessionCode, schoolCalendarRefId).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "MeetingTime{" + "timeTableDay='" + timeTableDay + '\'' + ", classMeetingDays=" + classMeetingDays + ", timeTablePeriod='" + timeTablePeriod + '\'' + ", roomNumber='" + roomNumber + '\'' + ", classBeginningTime='" + classBeginningTime + '\'' + ", classEndingTime='" + classEndingTime + '\'' + ", sessionCode='" + sessionCode + '\'' + ", schoolCalendarRefId='" + schoolCalendarRefId + '\'' + '}';
	}
}