package org.ricone.api.xpress.component.acl;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface XCalendarsACL {
	@interface Get {
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

	@interface Post {
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

	@interface Put {
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

	@interface Delete {
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

	@interface Head {
		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xSchools')")
		@interface ById { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xSchools')")
		@interface All { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xLeas/{}/xSchools')")
		@interface ByXLea { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xSchools/{}/xSchools')")
		@interface ByXSchool { }
	}
}