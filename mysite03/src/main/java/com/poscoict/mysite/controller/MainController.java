package com.poscoict.mysite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscoict.mysite.service.SiteService;

@Controller
public class MainController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"", "/main"})
	public String index(Model model) {
//		return "/WEB-INF/views/main/index.jsp";
//		SiteVo url = siteService.select();
//		model.addAttribute("siteVo", url);

		return "main/index";

	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	@RequestMapping("/msg02")
	public void message02(HttpServletResponse resp) throws Exception {
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().print("{\"message\": \"Hello World\"}");
	}	

	@ResponseBody
	@RequestMapping("/msg03")
	public Object message03() {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		
		return map;
	}	
	
}
