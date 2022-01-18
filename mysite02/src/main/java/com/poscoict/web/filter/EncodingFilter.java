package com.poscoict.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
//읽는 코드를 여기서 만들어 준다.
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");

			// default encoding
			if(encoding==null) { //default encoding
				//filter는 기술이라고 생각한다. spring에서는 기술을 spring에서 만들고 나머지 비즈니스 부분을 개발자가 코딩 하게끔
				
				encoding = "UTF-8";
			}
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* request 처리 */
		request.setCharacterEncoding(encoding);


		chain.doFilter(request, response);
		
		/* response 처리 */
		
	}

	public void destroy() {

	}
	
}
