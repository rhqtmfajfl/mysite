package com.poscoict.mysite.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscoict.mysite.config.AppConfig;
import com.poscoict.mysite.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//AbstractAnnotationConfigDispatcherServletInitializer 여기서 context load listener설정을 하고 context param을 하는것이다.
	//이거 자체가 DIspatcher Servlet 작업을 해준다.
	//AbstractAnnotationConfigDispatcherServletInitializer은 특정 메서드를 확장하고 재정의 할 수 있따.
	
	
	//root application을 설정하는것
	@Override
	protected Class<?>[] getRootConfigClasses() {
//getRootConfigClasses() 메소드에서는 ContextLoaderListener가 생성한 애플리케이션 컨텍스트를 설정하는 데 사용된다.
//이 경우 루트 설정은 RootConfig에 정의되어 있으며, 반면 DispatcherServlet의 정의는 WebConfig에 선언되어 있다.
		
	//	
		
		return new Class<?>[]{AppConfig.class};  //generic 인데 배열을 하나 만든것  new int[5]{1,2,3,4,5} 와 같은 의미  contextparam에서 뒤에 Appconfig를 가지고 온다.
	}
	
	
	@Override
	protected String[] getServletMappings() { //DispatcherServlet이 매핑되기 위한 하나 혹은 여러 개의 패스를 지정한다.
		//애플리케이션 기본 서블릿인 / 에만 매핑이 되어 있다. 그리고 이것은 애플리케이션으로 들어오는 모든 요청을 처리한다.
		// 내부적으로 AbstractAnnotationConfigDispatcherServletInitializer는  DispatcherServlet과 ContextLoaderListener
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}

	//webConfig를 설정 하는것
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{WebConfig.class};
	}


	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		return new Filter[]{new CharacterEncodingFilter("utf-8", false)};
	}


	@Override
	protected void customizeRegistration(Dynamic registration) {

		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");  //url에 매핑되는 핸들러가 없으면  throwException으로 404에러 발생
	 //NoHandlerFound Exception이 발생한ㄷ.
	}

	
	

	
}
