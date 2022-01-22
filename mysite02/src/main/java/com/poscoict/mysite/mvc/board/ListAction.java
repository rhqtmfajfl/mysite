package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDao board_dao = new BoardDao();
		UserDao user_dao = new UserDao();
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		// authUser 를 다른 곳에 객체로 가지고 다니면서 그곳에서 getAttribute("userno", authUser_no)
		
		if(authUser==null) {
			request.setAttribute("board_list", board_dao.find());
			request.setAttribute("userno", 0);
			MvcUtil.forward("board/list", request, response);

		}
		else {
		Long authUser_no = authUser.getNo();

		request.setAttribute("board_list", board_dao.find());
//		request.setAttribute("user_list", user_dao.find_all());
//		System.out.println(board_dao.find());
		request.setAttribute("userno", authUser_no);
		
//		request.setAttribute("group_no", authUser_no);
		
//		System.out.println(user_dao.find_no(authUser_no) + "testsetsetset");
		MvcUtil.forward("board/list", request, response);

		}
		
		
		
//		System.out.println(authUser_no + "12312312");

//		request.getAttribute(authUser.)
		
//		request.setAttribute("board_list", board_dao.find());
////		request.setAttribute("user_list", user_dao.find_all());
////		System.out.println(board_dao.find());
//		request.setAttribute("userno", authUser_no);
//		
////		System.out.println(user_dao.find_no(authUser_no) + "testsetsetset");
//		MvcUtil.forward("board/list", request, response);
	}

}