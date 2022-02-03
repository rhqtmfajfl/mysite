package com.poscoict.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscoict.mysite.security.Auth;

@Auth(role = "ADMIN") // 여기 컨트롤러의 모든 것은 인증 받아라
//@Auth(role="admin")
@Controller
@RequestMapping("/admin")
public class AdminController {

	
	
	@RequestMapping({"","/main"})
	public String main() {
		return "admin/main";
	}
	
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	@RequestMapping("/main/update")
	public String update() {
		return "redirect:/main";
	}
	
	
}
