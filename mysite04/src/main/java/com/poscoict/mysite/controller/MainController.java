package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

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
}
