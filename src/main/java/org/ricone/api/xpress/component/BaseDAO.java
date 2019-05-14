package org.ricone.api.xpress.component;

public class BaseDAO {
    /* Joins */
    protected final String JOIN_LEA = "lea";
    protected final String JOIN_LEA_TELEPHONES = "leaTelephones";
    protected final String JOIN_SCHOOL = "school";
    protected final String JOIN_SCHOOLS = "schools";
    protected final String JOIN_SCHOOL_GRADES = "schoolGrades";
    protected final String JOIN_SCHOOL_TELEPHONES = "schoolTelephones";
    protected final String JOIN_SCHOOL_IDENTIFIERS = "schoolIdentifiers";
    protected final String JOIN_SCHOOL_CALENDAR = "schoolCalendar";
    protected final String JOIN_SCHOOL_CALENDARS = "schoolCalendars";
    protected final String JOIN_SCHOOL_CALENDAR_SESSIONS = "schoolCalendarSessions";
    protected final String JOIN_COURSE = "course";
    protected final String JOIN_COURSES = "courses";
    protected final String JOIN_COURSE_IDENTIFIERS = "courseIdentifiers";
    protected final String JOIN_COURSE_GRADES = "courseGrades";
    protected final String JOIN_COURSE_SECTION = "courseSection";
    protected final String JOIN_COURSE_SECTIONS = "courseSections";
    protected final String JOIN_STAFF = "staff";
    protected final String JOIN_STAFF_IDENTIFIERS = "staffIdentifiers";
    protected final String JOIN_STAFF_ASSIGNMENTS = "staffAssignments";
    protected final String JOIN_STAFF_EMAILS = "staffEmails";
    protected final String JOIN_STAFF_COURSE_SECTIONS = "staffCourseSections";
    protected final String JOIN_STUDENT = "student";
    protected final String JOIN_STUDENT_COURSE_SECTIONS = "studentCourseSections";
    protected final String JOIN_STUDENT_IDENTIFIERS = "studentIdentifiers";
    protected final String JOIN_STUDENT_ENROLLMENTS = "studentEnrollments";
    protected final String JOIN_ENTRY_EXIT_CODES = "entryExitCodes";
    protected final String JOIN_STUDENT_CONTACT = "studentContact";
    protected final String JOIN_STUDENT_CONTACT_RELATIONSHIPS = "studentContactRelationships";

    /* Ref Ids */
    protected final String LEA_REF_ID = "leaRefId";
    protected final String SCHOOL_REF_ID = "schoolRefId";
    protected final String SCHOOL_CALENDAR_REF_ID = "schoolCalendarRefId";
    protected final String COURSE_REF_ID = "courseRefId";
    protected final String COURSE_SECTION_REF_ID = "courseSectionRefId";
    protected final String STAFF_REF_ID = "staffRefId";
    protected final String STUDENT_REF_ID = "studentRefId";
    protected final String STUDENT_CONTACT_REF_ID = "studentContactRefId";

    /* Event Log */
    protected final String EVENT_TIMESTAMP = "eventTimestamp";
}