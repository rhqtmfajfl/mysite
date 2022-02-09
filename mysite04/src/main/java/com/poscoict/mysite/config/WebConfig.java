package com.poscoict.mysite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.config.web.MvcConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.mysite.controller","com.poscoict.mysite.exception"})
@Import({MvcConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {  //여기서  interceptor

		//	<!-- auto proxy --> 
		//	<aop:aspectj-autoproxy />  -> @EnableAspectJAutoProxy
	
	//interceptor 시 bean을 여러개 만들어서 하나하나 인터셉터를 해주면 된다.
	
	@Bean
	public HandlerInterceptor siteInterceptor() {
		
		
		return siteInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(siteInterceptor()).//			<bean class="com.poscoict.mysite.security.SiteInterceptor"/>
		addPathPatterns("/**");  //						<mvc:mapping path="/**" />



	}
	
		
}
