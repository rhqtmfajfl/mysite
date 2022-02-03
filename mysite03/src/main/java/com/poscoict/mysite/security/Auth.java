package com.poscoict.mysite.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Auth {
//	public String values() default "USER";
//	public String role();
	public String role() default "USER";  //defualt가 USER이다.
	
//	public boolean test() default false; 
}
