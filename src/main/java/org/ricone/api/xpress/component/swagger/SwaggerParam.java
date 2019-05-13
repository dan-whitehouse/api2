package org.ricone.api.xpress.component.swagger;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface SwaggerParam {
	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface SchoolYear { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface NavigationPage { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface NavigationPageSize { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface IdType {
		boolean required() default false;
		String[] values() default {"local", "state", "beds"};
	}

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface Id { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface ChangesSince { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface AUPP {
		String[] values() default {"createUsers", "getUsers", "deleteUsers"};
	}
}