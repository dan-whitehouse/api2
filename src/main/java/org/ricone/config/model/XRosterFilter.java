package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "courseRefId", "courseTitle", "sectionRefId", "subject", "schoolRefId", "schoolSectionId", "schoolYear", "sessionCode", "schoolCalendarRefId", "meetingTimessessionCode", "meetingTimesschoolCalendarRefId", "meetingTimesmeetingTimeTableDay", "meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay", "meetingTimesmeetingTimetimeTablePeriod", "meetingTimesmeetingTimeroomNumber", "meetingTimesmeetingTimeclassBeginningTime", "meetingTimesmeetingTimeclassEndingTime", "studentsstudentReferencerefID", "studentsstudentReferencelocalId", "studentsstudentReferencegivenName", "studentsstudentReferencefamilyName", "primaryStaffstaffPersonReferencerefId", "primaryStaffstaffPersonReferencelocalId", "primaryStaffstaffPersonReferencegivenName", "primaryStaffstaffPersonReferencefamilyName", "primaryStaffteacherOfRecord", "primaryStaffpercentResponsible", "otherStaffsotherStaffstaffPersonReferencerefId", "otherStaffsotherStaffstaffPersonReferencelocalId", "otherStaffsotherStaffstaffPersonReferencegivenName", "otherStaffsotherStaffstaffPersonReferencefamilyName", "otherStaffsotherStaffteacherOfRecord", "otherStaffsotherStaffpercentResponsible", "id"})
public class XRosterFilter implements IFilter<XRosterFilter>, Serializable {

    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("courseRefId")
    private Boolean courseRefId;
    @JsonProperty("courseTitle")
    private Boolean courseTitle;
    @JsonProperty("sectionRefId")
    private Boolean sectionRefId;
    @JsonProperty("subject")
    private Boolean subject;
    @JsonProperty("schoolRefId")
    private Boolean schoolRefId;
    @JsonProperty("schoolSectionId")
    private Boolean schoolSectionId;
    @JsonProperty("schoolYear")
    private Boolean schoolYear;
    @JsonProperty("sessionCode")
    private Boolean sessionCode;
    @JsonProperty("schoolCalendarRefId")
    private Boolean schoolCalendarRefId;
    @JsonProperty("meetingTimessessionCode")
    private Boolean meetingTimessessionCode;
    @JsonProperty("meetingTimesschoolCalendarRefId")
    private Boolean meetingTimesschoolCalendarRefId;
    @JsonProperty("meetingTimesmeetingTimeTableDay")
    private Boolean meetingTimesmeetingTimeTableDay;
    @JsonProperty("meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay")
    private Boolean meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay;
    @JsonProperty("meetingTimesmeetingTimetimeTablePeriod")
    private Boolean meetingTimesmeetingTimetimeTablePeriod;
    @JsonProperty("meetingTimesmeetingTimeroomNumber")
    private Boolean meetingTimesmeetingTimeroomNumber;
    @JsonProperty("meetingTimesmeetingTimeclassBeginningTime")
    private Boolean meetingTimesmeetingTimeclassBeginningTime;
    @JsonProperty("meetingTimesmeetingTimeclassEndingTime")
    private Boolean meetingTimesmeetingTimeclassEndingTime;
    @JsonProperty("studentsstudentReferencerefID")
    private Boolean studentsstudentReferencerefID;
    @JsonProperty("studentsstudentReferencelocalId")
    private Boolean studentsstudentReferencelocalId;
    @JsonProperty("studentsstudentReferencegivenName")
    private Boolean studentsstudentReferencegivenName;
    @JsonProperty("studentsstudentReferencefamilyName")
    private Boolean studentsstudentReferencefamilyName;
    @JsonProperty("primaryStaffstaffPersonReferencerefId")
    private Boolean primaryStaffstaffPersonReferencerefId;
    @JsonProperty("primaryStaffstaffPersonReferencelocalId")
    private Boolean primaryStaffstaffPersonReferencelocalId;
    @JsonProperty("primaryStaffstaffPersonReferencegivenName")
    private Boolean primaryStaffstaffPersonReferencegivenName;
    @JsonProperty("primaryStaffstaffPersonReferencefamilyName")
    private Boolean primaryStaffstaffPersonReferencefamilyName;
    @JsonProperty("primaryStaffteacherOfRecord")
    private Boolean primaryStaffteacherOfRecord;
    @JsonProperty("primaryStaffpercentResponsible")
    private Boolean primaryStaffpercentResponsible;
    @JsonProperty("otherStaffsotherStaffstaffPersonReferencerefId")
    private Boolean otherStaffsotherStaffstaffPersonReferencerefId;
    @JsonProperty("otherStaffsotherStaffstaffPersonReferencelocalId")
    private Boolean otherStaffsotherStaffstaffPersonReferencelocalId;
    @JsonProperty("otherStaffsotherStaffstaffPersonReferencegivenName")
    private Boolean otherStaffsotherStaffstaffPersonReferencegivenName;
    @JsonProperty("otherStaffsotherStaffstaffPersonReferencefamilyName")
    private Boolean otherStaffsotherStaffstaffPersonReferencefamilyName;
    @JsonProperty("otherStaffsotherStaffteacherOfRecord")
    private Boolean otherStaffsotherStaffteacherOfRecord;
    @JsonProperty("otherStaffsotherStaffpercentResponsible")
    private Boolean otherStaffsotherStaffpercentResponsible;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = 4225682287089126496L;

    /**
     * No args constructor for use in serialization
     */
    public XRosterFilter() {
    }

    /**
     * @param studentsstudentReferencefamilyName
     * @param sessionCode
     * @param meetingTimesmeetingTimeclassEndingTime
     * @param meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay
     * @param subject
     * @param refId
     * @param primaryStaffstaffPersonReferencegivenName
     * @param meetingTimesmeetingTimetimeTablePeriod
     * @param otherStaffsotherStaffpercentResponsible
     * @param otherStaffsotherStaffstaffPersonReferencegivenName
     * @param meetingTimesmeetingTimeroomNumber
     * @param id
     * @param primaryStaffstaffPersonReferencefamilyName
     * @param studentsstudentReferencegivenName
     * @param meetingTimessessionCode
     * @param primaryStaffstaffPersonReferencelocalId
     * @param meetingTimesschoolCalendarRefId
     * @param schoolCalendarRefId
     * @param meetingTimesmeetingTimeTableDay
     * @param studentsstudentReferencerefID
     * @param schoolSectionId
     * @param otherStaffsotherStaffstaffPersonReferencelocalId
     * @param courseRefId
     * @param studentsstudentReferencelocalId
     * @param schoolRefId
     * @param primaryStaffteacherOfRecord
     * @param schoolYear
     * @param sectionRefId
     * @param otherStaffsotherStaffstaffPersonReferencefamilyName
     * @param courseTitle
     * @param primaryStaffpercentResponsible
     * @param otherStaffsotherStaffteacherOfRecord
     * @param primaryStaffstaffPersonReferencerefId
     * @param meetingTimesmeetingTimeclassBeginningTime
     * @param otherStaffsotherStaffstaffPersonReferencerefId
     */
    public XRosterFilter(Boolean refId, Boolean courseRefId, Boolean courseTitle, Boolean sectionRefId, Boolean subject, Boolean schoolRefId, Boolean schoolSectionId, Boolean schoolYear, Boolean sessionCode, Boolean schoolCalendarRefId, Boolean meetingTimessessionCode, Boolean meetingTimesschoolCalendarRefId, Boolean meetingTimesmeetingTimeTableDay, Boolean meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay, Boolean meetingTimesmeetingTimetimeTablePeriod, Boolean meetingTimesmeetingTimeroomNumber, Boolean meetingTimesmeetingTimeclassBeginningTime, Boolean meetingTimesmeetingTimeclassEndingTime, Boolean studentsstudentReferencerefID, Boolean studentsstudentReferencelocalId, Boolean studentsstudentReferencegivenName, Boolean studentsstudentReferencefamilyName, Boolean primaryStaffstaffPersonReferencerefId, Boolean primaryStaffstaffPersonReferencelocalId, Boolean primaryStaffstaffPersonReferencegivenName, Boolean primaryStaffstaffPersonReferencefamilyName, Boolean primaryStaffteacherOfRecord, Boolean primaryStaffpercentResponsible, Boolean otherStaffsotherStaffstaffPersonReferencerefId, Boolean otherStaffsotherStaffstaffPersonReferencelocalId, Boolean otherStaffsotherStaffstaffPersonReferencegivenName, Boolean otherStaffsotherStaffstaffPersonReferencefamilyName, Boolean otherStaffsotherStaffteacherOfRecord, Boolean otherStaffsotherStaffpercentResponsible, Integer id) {
        super();
        this.refId = refId;
        this.courseRefId = courseRefId;
        this.courseTitle = courseTitle;
        this.sectionRefId = sectionRefId;
        this.subject = subject;
        this.schoolRefId = schoolRefId;
        this.schoolSectionId = schoolSectionId;
        this.schoolYear = schoolYear;
        this.sessionCode = sessionCode;
        this.schoolCalendarRefId = schoolCalendarRefId;
        this.meetingTimessessionCode = meetingTimessessionCode;
        this.meetingTimesschoolCalendarRefId = meetingTimesschoolCalendarRefId;
        this.meetingTimesmeetingTimeTableDay = meetingTimesmeetingTimeTableDay;
        this.meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay = meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay;
        this.meetingTimesmeetingTimetimeTablePeriod = meetingTimesmeetingTimetimeTablePeriod;
        this.meetingTimesmeetingTimeroomNumber = meetingTimesmeetingTimeroomNumber;
        this.meetingTimesmeetingTimeclassBeginningTime = meetingTimesmeetingTimeclassBeginningTime;
        this.meetingTimesmeetingTimeclassEndingTime = meetingTimesmeetingTimeclassEndingTime;
        this.studentsstudentReferencerefID = studentsstudentReferencerefID;
        this.studentsstudentReferencelocalId = studentsstudentReferencelocalId;
        this.studentsstudentReferencegivenName = studentsstudentReferencegivenName;
        this.studentsstudentReferencefamilyName = studentsstudentReferencefamilyName;
        this.primaryStaffstaffPersonReferencerefId = primaryStaffstaffPersonReferencerefId;
        this.primaryStaffstaffPersonReferencelocalId = primaryStaffstaffPersonReferencelocalId;
        this.primaryStaffstaffPersonReferencegivenName = primaryStaffstaffPersonReferencegivenName;
        this.primaryStaffstaffPersonReferencefamilyName = primaryStaffstaffPersonReferencefamilyName;
        this.primaryStaffteacherOfRecord = primaryStaffteacherOfRecord;
        this.primaryStaffpercentResponsible = primaryStaffpercentResponsible;
        this.otherStaffsotherStaffstaffPersonReferencerefId = otherStaffsotherStaffstaffPersonReferencerefId;
        this.otherStaffsotherStaffstaffPersonReferencelocalId = otherStaffsotherStaffstaffPersonReferencelocalId;
        this.otherStaffsotherStaffstaffPersonReferencegivenName = otherStaffsotherStaffstaffPersonReferencegivenName;
        this.otherStaffsotherStaffstaffPersonReferencefamilyName = otherStaffsotherStaffstaffPersonReferencefamilyName;
        this.otherStaffsotherStaffteacherOfRecord = otherStaffsotherStaffteacherOfRecord;
        this.otherStaffsotherStaffpercentResponsible = otherStaffsotherStaffpercentResponsible;
        this.id = id;
    }

    @JsonProperty("refId")
    public Boolean getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(Boolean refId) {
        this.refId = refId;
    }

    @JsonProperty("courseRefId")
    public Boolean getCourseRefId() {
        return courseRefId;
    }

    @JsonProperty("courseRefId")
    public void setCourseRefId(Boolean courseRefId) {
        this.courseRefId = courseRefId;
    }

    @JsonProperty("courseTitle")
    public Boolean getCourseTitle() {
        return courseTitle;
    }

    @JsonProperty("courseTitle")
    public void setCourseTitle(Boolean courseTitle) {
        this.courseTitle = courseTitle;
    }

    @JsonProperty("sectionRefId")
    public Boolean getSectionRefId() {
        return sectionRefId;
    }

    @JsonProperty("sectionRefId")
    public void setSectionRefId(Boolean sectionRefId) {
        this.sectionRefId = sectionRefId;
    }

    @JsonProperty("subject")
    public Boolean getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(Boolean subject) {
        this.subject = subject;
    }

    @JsonProperty("schoolRefId")
    public Boolean getSchoolRefId() {
        return schoolRefId;
    }

    @JsonProperty("schoolRefId")
    public void setSchoolRefId(Boolean schoolRefId) {
        this.schoolRefId = schoolRefId;
    }

    @JsonProperty("schoolSectionId")
    public Boolean getSchoolSectionId() {
        return schoolSectionId;
    }

    @JsonProperty("schoolSectionId")
    public void setSchoolSectionId(Boolean schoolSectionId) {
        this.schoolSectionId = schoolSectionId;
    }

    @JsonProperty("schoolYear")
    public Boolean getSchoolYear() {
        return schoolYear;
    }

    @JsonProperty("schoolYear")
    public void setSchoolYear(Boolean schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonProperty("sessionCode")
    public Boolean getSessionCode() {
        return sessionCode;
    }

    @JsonProperty("sessionCode")
    public void setSessionCode(Boolean sessionCode) {
        this.sessionCode = sessionCode;
    }

    @JsonProperty("schoolCalendarRefId")
    public Boolean getSchoolCalendarRefId() {
        return schoolCalendarRefId;
    }

    @JsonProperty("schoolCalendarRefId")
    public void setSchoolCalendarRefId(Boolean schoolCalendarRefId) {
        this.schoolCalendarRefId = schoolCalendarRefId;
    }

    @JsonProperty("meetingTimessessionCode")
    public Boolean getMeetingTimessessionCode() {
        return meetingTimessessionCode;
    }

    @JsonProperty("meetingTimessessionCode")
    public void setMeetingTimessessionCode(Boolean meetingTimessessionCode) {
        this.meetingTimessessionCode = meetingTimessessionCode;
    }

    @JsonProperty("meetingTimesschoolCalendarRefId")
    public Boolean getMeetingTimesschoolCalendarRefId() {
        return meetingTimesschoolCalendarRefId;
    }

    @JsonProperty("meetingTimesschoolCalendarRefId")
    public void setMeetingTimesschoolCalendarRefId(Boolean meetingTimesschoolCalendarRefId) {
        this.meetingTimesschoolCalendarRefId = meetingTimesschoolCalendarRefId;
    }

    @JsonProperty("meetingTimesmeetingTimeTableDay")
    public Boolean getMeetingTimesmeetingTimeTableDay() {
        return meetingTimesmeetingTimeTableDay;
    }

    @JsonProperty("meetingTimesmeetingTimeTableDay")
    public void setMeetingTimesmeetingTimeTableDay(Boolean meetingTimesmeetingTimeTableDay) {
        this.meetingTimesmeetingTimeTableDay = meetingTimesmeetingTimeTableDay;
    }

    @JsonProperty("meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay")
    public Boolean getMeetingTimesmeetingTimeclassMeetingDaysbellScheduleDay() {
        return meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay;
    }

    @JsonProperty("meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay")
    public void setMeetingTimesmeetingTimeclassMeetingDaysbellScheduleDay(Boolean meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay) {
        this.meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay = meetingTimesmeetingTimeclassMeetingDaysbellScheduleDay;
    }

    @JsonProperty("meetingTimesmeetingTimetimeTablePeriod")
    public Boolean getMeetingTimesmeetingTimetimeTablePeriod() {
        return meetingTimesmeetingTimetimeTablePeriod;
    }

    @JsonProperty("meetingTimesmeetingTimetimeTablePeriod")
    public void setMeetingTimesmeetingTimetimeTablePeriod(Boolean meetingTimesmeetingTimetimeTablePeriod) {
        this.meetingTimesmeetingTimetimeTablePeriod = meetingTimesmeetingTimetimeTablePeriod;
    }

    @JsonProperty("meetingTimesmeetingTimeroomNumber")
    public Boolean getMeetingTimesmeetingTimeroomNumber() {
        return meetingTimesmeetingTimeroomNumber;
    }

    @JsonProperty("meetingTimesmeetingTimeroomNumber")
    public void setMeetingTimesmeetingTimeroomNumber(Boolean meetingTimesmeetingTimeroomNumber) {
        this.meetingTimesmeetingTimeroomNumber = meetingTimesmeetingTimeroomNumber;
    }

    @JsonProperty("meetingTimesmeetingTimeclassBeginningTime")
    public Boolean getMeetingTimesmeetingTimeclassBeginningTime() {
        return meetingTimesmeetingTimeclassBeginningTime;
    }

    @JsonProperty("meetingTimesmeetingTimeclassBeginningTime")
    public void setMeetingTimesmeetingTimeclassBeginningTime(Boolean meetingTimesmeetingTimeclassBeginningTime) {
        this.meetingTimesmeetingTimeclassBeginningTime = meetingTimesmeetingTimeclassBeginningTime;
    }

    @JsonProperty("meetingTimesmeetingTimeclassEndingTime")
    public Boolean getMeetingTimesmeetingTimeclassEndingTime() {
        return meetingTimesmeetingTimeclassEndingTime;
    }

    @JsonProperty("meetingTimesmeetingTimeclassEndingTime")
    public void setMeetingTimesmeetingTimeclassEndingTime(Boolean meetingTimesmeetingTimeclassEndingTime) {
        this.meetingTimesmeetingTimeclassEndingTime = meetingTimesmeetingTimeclassEndingTime;
    }

    @JsonProperty("studentsstudentReferencerefID")
    public Boolean getStudentsstudentReferencerefID() {
        return studentsstudentReferencerefID;
    }

    @JsonProperty("studentsstudentReferencerefID")
    public void setStudentsstudentReferencerefID(Boolean studentsstudentReferencerefID) {
        this.studentsstudentReferencerefID = studentsstudentReferencerefID;
    }

    @JsonProperty("studentsstudentReferencelocalId")
    public Boolean getStudentsstudentReferencelocalId() {
        return studentsstudentReferencelocalId;
    }

    @JsonProperty("studentsstudentReferencelocalId")
    public void setStudentsstudentReferencelocalId(Boolean studentsstudentReferencelocalId) {
        this.studentsstudentReferencelocalId = studentsstudentReferencelocalId;
    }

    @JsonProperty("studentsstudentReferencegivenName")
    public Boolean getStudentsstudentReferencegivenName() {
        return studentsstudentReferencegivenName;
    }

    @JsonProperty("studentsstudentReferencegivenName")
    public void setStudentsstudentReferencegivenName(Boolean studentsstudentReferencegivenName) {
        this.studentsstudentReferencegivenName = studentsstudentReferencegivenName;
    }

    @JsonProperty("studentsstudentReferencefamilyName")
    public Boolean getStudentsstudentReferencefamilyName() {
        return studentsstudentReferencefamilyName;
    }

    @JsonProperty("studentsstudentReferencefamilyName")
    public void setStudentsstudentReferencefamilyName(Boolean studentsstudentReferencefamilyName) {
        this.studentsstudentReferencefamilyName = studentsstudentReferencefamilyName;
    }

    @JsonProperty("primaryStaffstaffPersonReferencerefId")
    public Boolean getPrimaryStaffstaffPersonReferencerefId() {
        return primaryStaffstaffPersonReferencerefId;
    }

    @JsonProperty("primaryStaffstaffPersonReferencerefId")
    public void setPrimaryStaffstaffPersonReferencerefId(Boolean primaryStaffstaffPersonReferencerefId) {
        this.primaryStaffstaffPersonReferencerefId = primaryStaffstaffPersonReferencerefId;
    }

    @JsonProperty("primaryStaffstaffPersonReferencelocalId")
    public Boolean getPrimaryStaffstaffPersonReferencelocalId() {
        return primaryStaffstaffPersonReferencelocalId;
    }

    @JsonProperty("primaryStaffstaffPersonReferencelocalId")
    public void setPrimaryStaffstaffPersonReferencelocalId(Boolean primaryStaffstaffPersonReferencelocalId) {
        this.primaryStaffstaffPersonReferencelocalId = primaryStaffstaffPersonReferencelocalId;
    }

    @JsonProperty("primaryStaffstaffPersonReferencegivenName")
    public Boolean getPrimaryStaffstaffPersonReferencegivenName() {
        return primaryStaffstaffPersonReferencegivenName;
    }

    @JsonProperty("primaryStaffstaffPersonReferencegivenName")
    public void setPrimaryStaffstaffPersonReferencegivenName(Boolean primaryStaffstaffPersonReferencegivenName) {
        this.primaryStaffstaffPersonReferencegivenName = primaryStaffstaffPersonReferencegivenName;
    }

    @JsonProperty("primaryStaffstaffPersonReferencefamilyName")
    public Boolean getPrimaryStaffstaffPersonReferencefamilyName() {
        return primaryStaffstaffPersonReferencefamilyName;
    }

    @JsonProperty("primaryStaffstaffPersonReferencefamilyName")
    public void setPrimaryStaffstaffPersonReferencefamilyName(Boolean primaryStaffstaffPersonReferencefamilyName) {
        this.primaryStaffstaffPersonReferencefamilyName = primaryStaffstaffPersonReferencefamilyName;
    }

    @JsonProperty("primaryStaffteacherOfRecord")
    public Boolean getPrimaryStaffteacherOfRecord() {
        return primaryStaffteacherOfRecord;
    }

    @JsonProperty("primaryStaffteacherOfRecord")
    public void setPrimaryStaffteacherOfRecord(Boolean primaryStaffteacherOfRecord) {
        this.primaryStaffteacherOfRecord = primaryStaffteacherOfRecord;
    }

    @JsonProperty("primaryStaffpercentResponsible")
    public Boolean getPrimaryStaffpercentResponsible() {
        return primaryStaffpercentResponsible;
    }

    @JsonProperty("primaryStaffpercentResponsible")
    public void setPrimaryStaffpercentResponsible(Boolean primaryStaffpercentResponsible) {
        this.primaryStaffpercentResponsible = primaryStaffpercentResponsible;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencerefId")
    public Boolean getOtherStaffsotherStaffstaffPersonReferencerefId() {
        return otherStaffsotherStaffstaffPersonReferencerefId;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencerefId")
    public void setOtherStaffsotherStaffstaffPersonReferencerefId(Boolean otherStaffsotherStaffstaffPersonReferencerefId) {
        this.otherStaffsotherStaffstaffPersonReferencerefId = otherStaffsotherStaffstaffPersonReferencerefId;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencelocalId")
    public Boolean getOtherStaffsotherStaffstaffPersonReferencelocalId() {
        return otherStaffsotherStaffstaffPersonReferencelocalId;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencelocalId")
    public void setOtherStaffsotherStaffstaffPersonReferencelocalId(Boolean otherStaffsotherStaffstaffPersonReferencelocalId) {
        this.otherStaffsotherStaffstaffPersonReferencelocalId = otherStaffsotherStaffstaffPersonReferencelocalId;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencegivenName")
    public Boolean getOtherStaffsotherStaffstaffPersonReferencegivenName() {
        return otherStaffsotherStaffstaffPersonReferencegivenName;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencegivenName")
    public void setOtherStaffsotherStaffstaffPersonReferencegivenName(Boolean otherStaffsotherStaffstaffPersonReferencegivenName) {
        this.otherStaffsotherStaffstaffPersonReferencegivenName = otherStaffsotherStaffstaffPersonReferencegivenName;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencefamilyName")
    public Boolean getOtherStaffsotherStaffstaffPersonReferencefamilyName() {
        return otherStaffsotherStaffstaffPersonReferencefamilyName;
    }

    @JsonProperty("otherStaffsotherStaffstaffPersonReferencefamilyName")
    public void setOtherStaffsotherStaffstaffPersonReferencefamilyName(Boolean otherStaffsotherStaffstaffPersonReferencefamilyName) {
        this.otherStaffsotherStaffstaffPersonReferencefamilyName = otherStaffsotherStaffstaffPersonReferencefamilyName;
    }

    @JsonProperty("otherStaffsotherStaffteacherOfRecord")
    public Boolean getOtherStaffsotherStaffteacherOfRecord() {
        return otherStaffsotherStaffteacherOfRecord;
    }

    @JsonProperty("otherStaffsotherStaffteacherOfRecord")
    public void setOtherStaffsotherStaffteacherOfRecord(Boolean otherStaffsotherStaffteacherOfRecord) {
        this.otherStaffsotherStaffteacherOfRecord = otherStaffsotherStaffteacherOfRecord;
    }

    @JsonProperty("otherStaffsotherStaffpercentResponsible")
    public Boolean getOtherStaffsotherStaffpercentResponsible() {
        return otherStaffsotherStaffpercentResponsible;
    }

    @JsonProperty("otherStaffsotherStaffpercentResponsible")
    public void setOtherStaffsotherStaffpercentResponsible(Boolean otherStaffsotherStaffpercentResponsible) {
        this.otherStaffsotherStaffpercentResponsible = otherStaffsotherStaffpercentResponsible;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

}