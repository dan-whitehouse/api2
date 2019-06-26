package org.ricone.api.oneroster.component.springfox;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface SwaggerParam {
	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface Field { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface Filter { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface Sort { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface OrderBy { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface Limit { }

	@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface Offset { }
}