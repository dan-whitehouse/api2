/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timeTableDay", "classMeetingDays", "timeTablePeriod", "roomNumber", "classBeginningTime", "classEndingTime", "sessionCode", "schoolCalendarRefId"})
public class MeetingTime {
	@JsonProperty("timeTableDay")
	@ApiModelProperty(position = 1, value = "The unique identifier for the locally defined rotation cycle date code when the class meets (e.g., in a two day schedule, valid values could be 'A' and 'B', or '1' and '2')")
	private String timeTableDay;

	@JsonProperty("classMeetingDays")
	@ApiModelProperty(position = 2, value = "The day(s) of the week (e.g., Monday, Wednesday) that the class meets or an indication that a class meets 'out-of-school' or 'self-paced'")
	private ClassMeetingDays classMeetingDays;

	@JsonProperty("timeTablePeriod")
	@ApiModelProperty(position = 3, value = "An indication of the portion of a typical daily session in which students receive instruction in a specified subject (e.g., morning, sixth period, block period, or AB schedules)")
	private String timeTablePeriod;

	@JsonProperty("roomNumber")
	@ApiModelProperty(position = 4, value = "A unique number or alphanumeric code assigned to a room by a school, school system, state, or other agency or entity")
	private String roomNumber;

	@JsonProperty("classBeginningTime")
	@ApiModelProperty(position = 5, value = "An indication of the time of day the class begins")
	private String classBeginningTime;

	@JsonProperty("classEndingTime")
	@ApiModelProperty(position = 6, value = "An indication of the time of day the class ends")
	private String classEndingTime;

	@JsonProperty("sessionCode")
	@ApiModelProperty(position = 7, value = "A local code given to the session, usually for a session that represents a term within the school year such as a marking term")
	private String sessionCode;

	@JsonProperty("schoolCalendarRefId")
	@ApiModelProperty(position = 8, value = "The RefId of the school calendar")
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