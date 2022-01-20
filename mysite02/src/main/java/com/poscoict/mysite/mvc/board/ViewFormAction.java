package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.dao.BoardHitDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewFormAction implements Action {
	private static final int COOKIE_NAME = 60;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDao dao = new BoardDao();
		BoardVo bvo = new BoardVo();
		
		BoardHitDao bhd = new BoardHitDao();
		
		//앞에서 no를 가지고 온다.
		String no = request.getParameter("no");

		Long long_no = bhd.find_hit_no_long(no);
		
		
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//접근 제어 코드
		if(authUser == null) {
			//로그인을 해야 회원정보 수정을 하게끔한다.
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return;// 끝나면 완료 하다록 한다.
		}
		

		//1. session  있을떄 사용자 id + 글넘버
		
		//2. session 없을떄 글넘버
		
		//여기서 새롭게 
		request.setAttribute("no", no);
		request.setAttribute("findtitle", dao.findtitle(no));
		request.setAttribute("findcontents", dao.findcontent(no));
		
		System.out.println(dao.findtitle(no));
		System.out.println( dao.findcontent(no));

		System.out.println("여기서 hit 들어가는지 테스트");
		
//		int visitCount1 = 0;
		
		BoardVo test = dao.findall(no);
//		System.out.println(test);
		int hit_test = test.getHit();
//		int visitCount =0;

		if(no.equals(bhd.find_hit_no(no))) {
			System.out.println("여기서 hit 들어가는지 테스트2");

			Cookie[] cookies = request.getCookies(); // 모든 쿠키는 받아와서 배열로 받는다.
					
//			bvo.setNo(long_no);
			boolean check = true;
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (no.equals(cookie.getName())) { // 첫번째 요청시 쿠키 네임이 안와서 안으로 안들어간다.
						hit_test = Integer.parseInt(cookie.getValue());
						check =false;
						break;	
						
					}
		
				}
			}
			if(check) {
				bvo.setNo(long_no);
				bvo.setHit(hit_test+1);
				boolean hit2 = new BoardHitDao().update_hit(bvo);
			}
//			visitCount++; // 1 쿠키 1 이 나온다.
			
			if(authUser==null) {
				// 쿠키 쓰기(굽기) 쿠키 구워라고 나온다.
			Cookie cookie = new Cookie(no, String.valueOf(hit_test));
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60); //60 sec
			response.addCookie(cookie); // 이러면 set 쿠키로 나간다.
			
//			//화면 출력 Cookie: visitcount=1 f12에 net
//			response.setContentType("text/html; charset=utf-8"); // 여기서 쿠키가 1인것 확인 다음에 쿠키 읽기에서 1이 넘어간다.
//			PrintWriter out = response.getWriter();
//			out.println("<h1> 방문 횟수 : " + visitCount + "</h1>");
//			System.out.println(visitCount);
			request.setAttribute("board_list", dao.findno());
//			System.out.println();
			request.setAttribute("userno", 0);
			MvcUtil.forward("board/view", request, response);
			}else {
				Cookie cookie = new Cookie(no, String.valueOf(hit_test));
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60); //60 sec
				response.addCookie(cookie); // 이러면 set 쿠키로 나간다.
				
//				// 화면 출력 Cookie: visitcount=1 f12에 net
//				response.setContentType("text/html; charset=utf-8"); // 여기서 쿠키가 1인것 확인 다음에 쿠키 읽기에서 1이 넘어간다.
//				PrintWriter out = response.getWriter();
//				out.println("<h1> 방문 횟수 : " + visitCount + "</h1>");
//				System.out.println(visitCount);
				Long authUser_no = authUser.getNo();
				request.setAttribute("board_list", dao.findno());
				request.setAttribute("userno", authUser_no);

				MvcUtil.forward("board/view", request, response);
			}
			

			
		}
			
		
//		MvcUtil.forward("board/view", request, response);
//		MvcUtil.redirect("/mysite02/hit", request, response);

	}

}
