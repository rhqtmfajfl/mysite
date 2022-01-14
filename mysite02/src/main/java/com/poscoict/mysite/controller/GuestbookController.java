package com.poscoict.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.mvc.guestbook.GuestbookActionFactory;
//import com.poscoict.mysite.mvc.guestbook.GuestbookActionFactory;
import com.poscoict.mysite.mvc.main.MainActionFactory;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");  //a 파라미터를 받아서 actionName에 넣어준다.

		ActionFactory af = new GuestbookActionFactory();  //af 라는 guestbookactionfactory 객체 만들고 
		// getAction이라는 함수로 String actionName을 사용 할 수 있게 해주는 Action Factory
		Action action = af.getAction(actionName); //deleteform이면 /WEB-INF/views/ guestbook/deleteform.jsp 가 action에 들어간다.
		//IndexAction이면 /WEB-INF/views/ guestbook/index.jsp로 간다.
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}