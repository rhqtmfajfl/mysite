package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.dao.find_emailDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//여기서 엑세스 제어
		//updateform의 값을 가져와서 보낸다.
		//접근제어 접근 제어 코드들이 들어가지만 나중에 aop에서는 공통 빼낸다.
		//여기서 find_no 를 새로 만들어서 email을 가져 온다.  -> 여기 아님
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			//로그인을 해야 회원정보 수정을 하게끔한다.
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		
		
		UserVo vo = new UserVo();
		
		
		
		
//		Long findno = new UserDao().find_no(name, password);
		
//		
//		System.out.println(findno);
//		vo.setNo(findno);
		Long no = authUser.getNo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);
		
		boolean test = new UserDao().update(vo);
		System.out.println(test ? "success update" : "fail update");
		
		MvcUtil.redirect(request.getContextPath(), request, response);

	}

}
