package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

//		UserDao dao = new UserDao();
		
//		dao.findByEmailAndPassword();
		UserVo authUser = new UserDao().findByEmailAndPassword(email,password);
		
		if(authUser == null) {
			/* 이메일 또는 비밀 번호가 틀림 */
			request.setAttribute("result","fail");
			request.setAttribute("email", email);
			MvcUtil.forward("user/loginform", request, response);
			//여기서는 함수가 끝난게 아니므로 return 해서 끝내야 한다.
			//응답이 끈타면 return 해준다.
			return;
		}
		
		//인증처리(Session)
		
		
		//여기까지 온다는 것은 인증이 성공한것이므로 인증 처리 해준다.Session 처리
		
		//로그인이 성공하면 응답 처리 해준다. redirect 사용 sendRedirect 메인으로 가며는
		// response
		//redirect:/mysite
		
		HttpSession session = request.getSession(true); //업으면 만들어라고 하는것
//		if(authUser.equals(null)) {
//			session.setAttribute("authUser_no",0);
//			//메인으로 가게
//			MvcUtil.redirect(request.getContextPath(), request, response);
//
//		}else {
		
			session.setAttribute("authUser", authUser);  //위에 있는ㄴ authUser
			//메인으로 가게
			MvcUtil.redirect(request.getContextPath(), request, response);

//		}
		
				
	}

}
	