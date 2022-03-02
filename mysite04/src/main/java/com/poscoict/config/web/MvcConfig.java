package com.poscoict.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc  //Mvc 를 설정 하게 해준다. mvc:annotation-driven 과 같은 것
public class MvcConfig extends WebMvcConfigurerAdapter {  //WebMvcConfigurerAdapter여기에 들어있다.	<!-- 서블릿 컨테이너(tomcat)의 DefaultServlet 위임(delegate) Handler -->
// WebMvcConfigurerAdapter durldp 

	
	//view Resolver 설정		
	@Bean
		public ViewResolver viewResolver() {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class); //		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" /> 이부분
			viewResolver.setPrefix("/WEB-INF/views/");//		<property name="prefix" value="/WEB-INF/views/"/>
			viewResolver.setSuffix(".jsp");//		<property name="suffix" value=".jsp"/>
			
			return viewResolver;
		}
		
		// Message Converter
		@Bean
		public StringHttpMessageConverter stringHttpMessageConverter() {
			StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
			
			
			
//			List<MediaType> list = new ArrayList<>();
//			list.add(new MediaType("text","html", Charset.forName("utf-8")));
			
//			Arrays.asList(new MediaType("text","html", Charset.forName("utf-8")));
			messageConverter.setSupportedMediaTypes(
					Arrays.asList(
							new MediaType("text","html", Charset.forName("utf-8"))
					)
				);
			
			return messageConverter;
		}
		
		
		@Bean
		public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
			Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
					.indentOutput(true)
					.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));
			
			MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
			messageConverter.setSupportedMediaTypes(
					Arrays.asList(
							new MediaType("application", "json", Charset.forName("utf-8"))
					)
			);
			return messageConverter;		
		}
		
		
		
		
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			converters.add(stringHttpMessageConverter());
			converters.add(mappingJackson2HttpMessageConverter());
		}

		//		//DefaultServlet 위임(delegate) Handler
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
	
	
}
