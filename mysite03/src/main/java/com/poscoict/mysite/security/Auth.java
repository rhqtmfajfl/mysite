package com.poscoict.mysite.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)// ANNOTATION이 언제까지 살아있을지 결정 한다.
@Target({TYPE, METHOD})
public @interface Auth {
	// public String value() default "USER";
	public String role() default "USER";
	//public boolean test() default false;
}