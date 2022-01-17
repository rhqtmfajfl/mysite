package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//여기서 엑세스 제어
		//updateform의 값을 가져와서 보낸다.
		//접근제어 접근 제어 코드들이 들어가지만 나중에 aop에서는 공통 빼낸다.
		
		
	}

}
