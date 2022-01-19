package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDeleteDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDeleteDao bd = new BoardDeleteDao();
		BoardVo bvo_delete = new BoardVo();

		String no = request.getParameter("no");

		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			//로그인을 해야 회원정보 수정을 하게끔한다.
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		
		Long delete_no = bd.delete_no(no);
		bvo_delete.setNo(delete_no);
		
		boolean test = bd.delete_title(bvo_delete);
		System.out.println(test ? "success delete" : "fail delete");
		
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);

	}

}
