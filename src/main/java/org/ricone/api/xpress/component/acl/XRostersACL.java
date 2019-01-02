package org.ricone.api.xpress.component.acl;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface XRostersACL {
	@interface Get {
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

	@interface Post {
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

	@interface Put {
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

	@interface Delete {
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

	@interface Head {
		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xRosters')")
		@interface ById { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xRosters')")
		@interface All { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xLeas/{}/xRosters')")
		@interface ByXLea { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xSchools/{}/xRosters')")
		@interface ByXSchool { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xCourses/{}/xRosters')")
		@interface ByXCourse { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xStaffs/{}/xRosters')")
		@interface ByXStaff { }

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@PreAuthorize("hasAuthority('head:/requests/xStudents/{}/xRosters')")
		@interface ByXStudent { }
	}
}