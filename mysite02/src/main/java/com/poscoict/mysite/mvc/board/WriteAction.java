package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardWriteDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BoardWriteDao board_dao = new BoardWriteDao();
		BoardVo bvo = new BoardVo();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			//로그인을 해야 회원정보 수정을 하게끔한다.
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		
		String username = authUser.getName();		
		bvo.setTitle(title);
		bvo.setContents(content);
		bvo.setUserName(username);
//		board_dao.insert_write(bvo);
		System.out.println(username);
		boolean test = board_dao.insert_write(bvo);
		System.out.println(test ? "success" : "fail");
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
		
//		MvcUtil.forward("user/updateform", request, response);  // redirect로 보내야 한다.
	}

}
