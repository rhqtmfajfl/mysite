package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handler Method 에 @Auth가 없으면 Type에 있는지 확인(과제)
		if(auth == null) {
			auth = handlerMethod
					.getMethod()
					.getDeclaringClass()
					.getAnnotation(Auth.class); 
		}
		
		//5. type(4)과 method(3)에 @Auth 가 적용이 안되어 있는 경우
		if(auth == null) {
			return true;
		}

		//6. @Auth가 적용이 되어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//7. 권한(Authorization) 체크를 위해서 @Auth의 role 가져오기("USER","ADMIN")
		String role = auth.role();         //여기는 null이 올수 없다.  이유는 5번에서 이미 auth는 null이 되어 있기 때문에 여기서 null이면 자동으로 통과 된다.
											// role이 user이다.
		
		//8. @Auth의 role이 "USER" 인 경우 , authUser의 role은 상관이 없다.
		if("USER".equals(role)) {
			return true;
		}
		
		//9. @Auth의 role이 "ADMIN" 인 경우 , authUser의 "ADMIN" 이어야 한다.
		if("ADMIN".equals(authUser.getRole())==false) { //
			response.sendRedirect(request.getContextPath());
			return false;  //false를 함으로써 뒤로 못가게 한다.
		}
		
		//6. 인증 확인!!! -> controller의 hanlder(method) 실행
		
		//10. 옳은 관리자
		//옳은 권한을 가진 인증한 사용자
		// @Auth role: "ADMIN"
		//authUser의 role: "ADMIN"
		
		return true;  //여기서 RETURN 되는 CONTROLLER는 ADMIN Controller이다.
	}

}