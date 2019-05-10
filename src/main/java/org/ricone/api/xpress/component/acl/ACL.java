package org.ricone.api.xpress.component.acl;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface ACL {

	@interface Get {

		@interface XLea {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xLeas')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCalendars/{}/xLeas')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xLeas')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xLea')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xLeas')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xLeas')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xContacts/{}/xLeas')")
			@interface ByXContact { }
		}

		@interface XSchool {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xSchools')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCalendars/{}/xSchools')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xSchools')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xLea')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xSchools')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xSchools')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xContacts/{}/xSchools')")
			@interface ByXContact { }
		}

		@interface XCalendar {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCalendars')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCalendars')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xCalendars')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xCalendars')")
			@interface ByXSchool { }
		}

		@interface XCourse {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xCourses')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xCourses')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xCourses')")
			@interface ByXRoster { }
		}

		@interface XRoster {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xRosters')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xRosters')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xRosters')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xRosters')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xRosters')")
			@interface ByXStudent { }
		}

		@interface XStaff {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStaffs')")
			@interface ByRefId { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStaffs')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xStaffs')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xStaffs')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xStaffs')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xStaffs')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xStaffs')")
			@interface ByXStudent { }
		}

		@interface XStudent {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents')")
			@interface ByRefId { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xStudents')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xStudents')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xStudents')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xStudents')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xContacts/{}/xStudents')")
			@interface ByXContact { }
		}

		@interface XContact {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xContacts')")
			@interface ByRefId { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xContacts')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xLeas/{}/xContacts')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xSchools/{}/xContacts')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xContacts')")
			@interface ByXStudent { }
		}
	}

	@interface Post {

		@interface XLea {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xLeas')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCalendars/{}/xLeas')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xLeas')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters/{}/xLea')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStaffs/{}/xLeas')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents/{}/xLeas')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xContacts/{}/xLeas')")
			@interface ByXContact { }
		}

		@interface XSchool {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xSchools')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCalendars/{}/xSchools')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xSchools')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters/{}/xSchools')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStaffs/{}/xSchools')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents/{}/xSchools')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xContacts/{}/xSchools')")
			@interface ByXContact { }
		}

		@interface XCalendar {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCalendars')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCalendars')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xCalendars')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xCalendars')")
			@interface ByXSchool { }
		}

		@interface XCourse {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCourses')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCourses')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xCourses')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xCourses')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters/{}/xCourses')")
			@interface ByXRoster { }
		}

		@interface XRoster {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xRosters')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xRosters')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCourses/{}/xRosters')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStaffs/{}/xRosters')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents/{}/xRosters')")
			@interface ByXStudent { }
		}

		@interface XStaff {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStaffs')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStaffs')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xStaffs')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xStaffs')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xCourses/{}/xStaffs')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters/{}/xStaffs')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents/{}/xStaffs')")
			@interface ByXStudent { }
		}

		@interface XStudent {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xStudents')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xStudents')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xRosters/{}/xStudents')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStaffs/{}/xStudents')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xContacts/{}/xStudents')")
			@interface ByXContact { }
		}

		@interface XContact {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xContacts')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xContacts')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xLeas/{}/xContacts')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xSchools/{}/xContacts')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('post:/requests/xStudents/{}/xContacts')")
			@interface ByXStudent { }
		}
	}

	@interface Put {

		@interface XLea {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xLeas')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCalendars/{}/xLeas')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCourses/{}/xLeas')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters/{}/xLea')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStaffs/{}/xLeas')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents/{}/xLeas')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xContacts/{}/xLeas')")
			@interface ByXContact { }
		}

		@interface XSchool {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xSchools')")
			@interface ByXLeas { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCalendars/{}/xSchools')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCourses/{}/xSchools')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters/{}/xSchools')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStaffs/{}/xSchools')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents/{}/xSchools')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xContacts/{}/xSchools')")
			@interface ByXContact { }
		}

		@interface XCalendar {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCalendars')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCalendars')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xCalendars')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xCalendars')")
			@interface ByXSchool { }
		}

		@interface XCourse {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCourses')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCourses')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xCourses')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xCourses')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters/{}/xCourses')")
			@interface ByXRoster { }
		}

		@interface XRoster {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xRosters')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xRosters')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCourses/{}/xRosters')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStaffs/{}/xRosters')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents/{}/xRosters')")
			@interface ByXStudent { }
		}

		@interface XStaff {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStaffs')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStaffs')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xStaffs')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xStaffs')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xCourses/{}/xStaffs')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters/{}/xStaffs')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents/{}/xStaffs')")
			@interface ByXStudent { }
		}

		@interface XStudent {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xStudents')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xStudents')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xRosters/{}/xStudents')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStaffs/{}/xStudents')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xContacts/{}/xStudents')")
			@interface ByXContact { }
		}

		@interface XContact {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xContacts')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xContacts')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xLeas/{}/xContacts')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xSchools/{}/xContacts')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('put:/requests/xStudents/{}/xContacts')")
			@interface ByXStudent { }
		}
	}

	@interface Delete {

		@interface XLea {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xLeas')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCalendars/{}/xLeas')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCourses/{}/xLeas')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters/{}/xLea')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStaffs/{}/xLeas')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents/{}/xLeas')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xContacts/{}/xLeas')")
			@interface ByXContact { }
		}

		@interface XSchool {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xSchools')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCalendars/{}/xSchools')")
			@interface ByXCalendar { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCourses/{}/xSchools')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters/{}/xLea')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStaffs/{}/xSchools')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents/{}/xSchools')")
			@interface ByXStudent { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xContacts/{}/xSchools')")
			@interface ByXContact { }
		}

		@interface XCalendar {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCalendars')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCalendars')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas/{}/xCalendars')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xCalendars')")
			@interface ByXSchool { }
		}

		@interface XCourse {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCourses')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCourses')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas/{}/xCourses')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xCourses')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters/{}/xCourses')")
			@interface ByXRoster { }
		}

		@interface XRoster {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas/{}/xRosters')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xRosters')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCourses/{}/xRosters')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStaffs/{}/xRosters')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents/{}/xRosters')")
			@interface ByXStudent { }
		}

		@interface XStaff {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStaffs')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStaffs')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas/{}/xStaffs')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xStaffs')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xCourses/{}/xStaffs')")
			@interface ByXCourse { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters/{}/xStaffs')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents/{}/xStaffs')")
			@interface ByXStudent { }
		}

		@interface XStudent {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas/{}/xStudents')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xStudents')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xRosters/{}/xStudents')")
			@interface ByXRoster { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStaffs/{}/xStudents')")
			@interface ByXStaff { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xContacts/{}/xStudents')")
			@interface ByXContact { }
		}

		@interface XContact {
			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xContacts')")
			@interface ById { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xContacts')")
			@interface All { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xLeas/{}/xContacts')")
			@interface ByXLea { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xSchools/{}/xContacts')")
			@interface ByXSchool { }

			@Target(ElementType.METHOD)
			@Retention(RetentionPolicy.RUNTIME)
			@PreAuthorize("hasAuthority('delete:/requests/xStudents/{}/xContacts')")
			@interface ByXStudent { }
		}
	}
}