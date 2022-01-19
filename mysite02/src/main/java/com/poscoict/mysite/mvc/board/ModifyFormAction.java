package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDao dao = new BoardDao();
		BoardVo bvo = new BoardVo();
		
		
//		//앞에서 no를 가지고 온다.
		String no = request.getParameter("no");

		
//		System.out.println(no);
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			//로그인을 해야 회원정보 수정을 하게끔한다.
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		
//		Long no = authUser.getNo();
		
//		System.out.println(no + "1232341532555555555555");
		request.setAttribute("no", no);
		request.setAttribute("title", dao.findtitle(no));
		
//		System.out.println(dao.findtitle(no) + "12412341234");
		
		request.setAttribute("contents", dao.findcontent(no));
		
//		System.out.println( dao.findcontent(no) + "12412341234");

		
		MvcUtil.forward("board/modify", request, response);

	}

}
