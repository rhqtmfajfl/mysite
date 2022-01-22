package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.AddreplyDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		AddreplyDao reply_add = new AddreplyDao();
		BoardVo boardvo = new BoardVo();
		
		String no = request.getParameter("no");
		int write_no = Integer.parseInt(no);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//no로 g_no오 찾고
		/*
		 * add.jsp에서 댓글 입력 누르면
		 * o_no랑 depth 각각 +1 씩 해준다.
		 * g_no
		 * 
		 * 만약에 g_no랑 no랑 같으면 이거는 아니구나
		 * g_no
		 */
		
		System.out.println(no);
		
		
		BoardVo vo = (BoardVo)reply_add.find_board_all(no); //글번호에 맞는 g_no출력된다.
		int g_no = vo.getGroupNo(); //groupno를 가지고 온다.
		System.out.println("g_no : " + g_no);
		int  o_no = vo.getOrderNo();
		int depth = vo.getDepth();
		int hit = vo.getHit();
		Long user_no = vo.getUserNo(); //사용자의 userno를 가지고 온다.
		
		System.out.println("add로 들어왔는지 확인1");
		System.out.println("user_no : " + user_no);

		
			boardvo.setTitle(title);
			boardvo.setContents(content);
			boardvo.setHit(hit);
			boardvo.setGroupNo(g_no);
			boardvo.setOrderNo(o_no);
			boardvo.setDepth(depth);
			boardvo.setUserNo(user_no);
			reply_add.update_o_no(boardvo); 
			System.out.println("add로 들어왔는지 확인2");

											/*
											 * 처음에 댓글을 저녁먹자를 단다. 
											 * 그리고 저녁먹자에 대해 모먹지라는 답글을 달려고 한다면
											 * 모먹지의 o_no와 depth가 1씩 증가한다.
											 * 그런데 업데이트는 저녁먹자에 또 답글을 달려고 한다면 
											 * 그때 모먹지의 o_no를 증가시켜야 한다.
											 * 그래서 만약 저녁먹자에 조금있다가를 입력한다면 depth는 모먹지와 같지만
											 * o_no는 
 											 * 달면 저녁먹자에 대한 모먹지?는 o_no와 depth는 
											 * 저녁먹자와 같은 1 1 이다 그리고 g_no는 같다
											 * 하지만 insert에서 o_no와 depth가 1씩 증가 한다.
											 * 그리고만약 저녁먹자에 대해 답글을 또 달고자 한다면 모먹지와 같은
											 * o_no와 depth 값이 나온다.
											 * 그리고 만약 모먹지에 대해 답글을 달면 이번에는 update에서
											 * set o_no = (o_no +1) 을 한다 이때 조건은
											 * o_no > o_no 인데 
											 */
			
			System.out.println("add로 들어왔는지 확인3");

			reply_add.insert_write(boardvo);
		
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);

		
	}

}
