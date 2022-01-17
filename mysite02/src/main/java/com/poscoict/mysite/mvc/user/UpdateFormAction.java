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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//로그인을 해야 들어갈수 있게 하기 위해 접근제어 코드가 여기 들어가야 한다.
			// 여기는 항상 접근제어 코드(Access Control)
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		
//		UserVo vo = new UserDao().findByNo(authUser.getNo()); //여기서 빼올 때 v패스워드 안가져 온다.
		//forwarding user/updateform으로 보낸다.
//		request.setAttribute("userVo", vo);
		MvcUtil.forward("user/updateform", request, response);
	}

}
