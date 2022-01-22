package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class AddFormction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 
		 * 처음에 
		 * g_no랑 같은데 첫 댓글을 누르면 o_no 와 depth 증가
		 * 
		 * no를 찾아서 그 no 에 맞는 board의 g_no를 알게된다면 
		 * g_no랑 no랑 맞으면 o_no depth 1씩 증가
		 * 
		 */
		
		String no = request.getParameter("no");
		String user_no = request.getParameter("user_no"); //이거 필요 없는것
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			//로그인을 해야 회원정보 수정을 하게끔한다.
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		
		
		request.setAttribute("write_no", no);
		request.setAttribute("add_user_no", user_no); //이거 필요없는것
		
		MvcUtil.forward("board/add", request, response);

		
	}

}
