package org.ricone.api.xpress.component;

import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
public @interface ISO8601 {
}