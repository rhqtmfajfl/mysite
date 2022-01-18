package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.GuestbookDao;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestbookDao dao = new GuestbookDao();
		
		
		request.setAttribute("list", dao.findAll());
		System.out.println(dao.findAll());
		MvcUtil.forward("guestbook/index", request, response);  //guestbook/index 가 path가 된다. 
		// 그러면 MvcUtil.java로 forward 되고
	}

}