package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		
//		HttpSession session = request.getSession();
//		
//		if(session.getAttribute("test")==null) {
//			session.setAttribute("test", no);
//		}
//		
		request.setAttribute("no", no);
		MvcUtil.forward("guestbook/deleteform", request, response);
	}

}
