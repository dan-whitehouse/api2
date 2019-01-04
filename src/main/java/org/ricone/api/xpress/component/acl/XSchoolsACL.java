package org.ricone.api.xpress.component.acl;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface XSchoolsACL {
	@interface Get {
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

	@interface Post {
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

	@interface Put {
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

	@interface Delete {
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
}