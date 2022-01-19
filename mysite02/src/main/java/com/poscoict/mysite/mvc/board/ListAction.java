package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDao board_dao = new BoardDao();
		
		request.setAttribute("board_list", board_dao.find());
//		System.out.println(board_dao.find());
		MvcUtil.forward("board/list", request, response);
	}

}