package org.ricone.api.xpress.component.acl;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface XCourseACL {
	@interface Get {
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

	@interface Post {
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

	@interface Put {
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

	@interface Delete {
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

	@interface Head {
		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xCourses')")
		@interface ById { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xCourses')")
		@interface All { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xLeas/{}/xCourses')")
		@interface ByXLea { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xSchools/{}/xCourses')")
		@interface ByXSchool { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xRosters/{}/xCourses')")
		@interface ByXRoster { }
	}
}