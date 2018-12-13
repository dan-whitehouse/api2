package org.ricone.api.xpress.component.acl;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface XLeasACL {
	@interface Get {
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
		@interface ByXSchools { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xCalendars/{}/xLeas')")
		@interface ByXCalendars { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xLeas')")
		@interface ByXCourses { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xRosters/{}/xLea')")
		@interface ByXRosters { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xStaffs/{}/xLeas')")
		@interface ByXStaffs { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xStudents/{}/xLeas')")
		@interface ByXStudents { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xContacts/{}/xLeas')")
		@interface ByXContacts { }
	}

	@interface Post {
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
		@interface ByXSchools { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('post:/requests/xCalendars/{}/xLeas')")
		@interface ByXCalendars { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('get:/requests/xCourses/{}/xLeas')")
		@interface ByXCourses { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('post:/requests/xRosters/{}/xLea')")
		@interface ByXRosters { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('post:/requests/xStaffs/{}/xLeas')")
		@interface ByXStaffs { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('post:/requests/xStudents/{}/xLeas')")
		@interface ByXStudents { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('post:/requests/xContacts/{}/xLeas')")
		@interface ByXContacts { }
	}

	@interface Put {
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
		@interface ByXSchools { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('put:/requests/xCalendars/{}/xLeas')")
		@interface ByXCalendars { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('put:/requests/xCourses/{}/xLeas')")
		@interface ByXCourses { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('put:/requests/xRosters/{}/xLea')")
		@interface ByXRosters { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('put:/requests/xStaffs/{}/xLeas')")
		@interface ByXStaffs { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('put:/requests/xStudents/{}/xLeas')")
		@interface ByXStudents { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('put:/requests/xContacts/{}/xLeas')")
		@interface ByXContacts { }
	}

	@interface Delete {
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
		@interface ByXSchools { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('delete:/requests/xCalendars/{}/xLeas')")
		@interface ByXCalendars { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('delete:/requests/xCourses/{}/xLeas')")
		@interface ByXCourses { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('delete:/requests/xRosters/{}/xLea')")
		@interface ByXRosters { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('delete:/requests/xStaffs/{}/xLeas')")
		@interface ByXStaffs { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('delete:/requests/xStudents/{}/xLeas')")
		@interface ByXStudents { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('delete:/requests/xContacts/{}/xLeas')")
		@interface ByXContacts { }
	}

	@interface Head {
		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xLeas')")
		@interface ById { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xLeas')")
		@interface All { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xSchools/{}/xLeas')")
		@interface ByXSchools { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xCalendars/{}/xLeas')")
		@interface ByXCalendars { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xCourses/{}/xLeas')")
		@interface ByXCourses { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xRosters/{}/xLea')")
		@interface ByXRosters { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xStaffs/{}/xLeas')")
		@interface ByXStaffs { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xStudents/{}/xLeas')")
		@interface ByXStudents { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xContacts/{}/xLeas')")
		@interface ByXContacts { }
	}
}