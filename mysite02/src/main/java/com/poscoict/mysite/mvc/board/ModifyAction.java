package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardModifyDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardModifyDao bm = new BoardModifyDao();
		BoardVo bvo_modify = new BoardVo();

		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Long modify_no = bm.find_no(no);
		
		bvo_modify.setNo(modify_no);
		bvo_modify.setTitle(title);
		bvo_modify.setContents(content);
		
		request.setAttribute("hit_no",no);
		
		
		boolean test = bm.update_modify(bvo_modify);
		System.out.println(test ? "success modify" : "fail modify");
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);

	}

}
