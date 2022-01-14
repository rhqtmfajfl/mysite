package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.GuestbookDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		MvcUtil.forward("guestbook/delete", request, response);
//		delete 는 redirect로 가야한다.
		
		String password = request.getParameter("password");
		int no = Integer.parseInt(request.getParameter("no"));
		
		new GuestbookDao().delete(no,password);
		
		
		MvcUtil.redirect("/WEB-INF/views/mysite02.guestbook.index.jsp", request, response);
	}

}
