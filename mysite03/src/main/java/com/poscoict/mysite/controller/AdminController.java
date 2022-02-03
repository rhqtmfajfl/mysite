package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

@Auth(role = "ADMIN") // 여기 컨트롤러의 모든 것은 인증 받아라
//@Auth(role="admin")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		SiteVo url = siteService.select();
		model.addAttribute("siteVo", url);
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
	
	
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String update(@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="welcomeMessage", required=true, defaultValue="") String welcomeMessage,
			@RequestParam(value="file1") MultipartFile multipartFile,
			@RequestParam(value="description", required=true, defaultValue="") String description
			) {
			
			SiteVo vo = new SiteVo();
			
			vo.setTitle(title);
			vo.setWelcome(welcomeMessage);
		

//			System.out.println("multi입니다. : " + fileUploadService.restore(multipartFile));
			vo.setProfile(fileUploadService.restore(multipartFile));
			vo.setDescription(description);
			
			
			siteService.update(vo);
	
		
		return "redirect:/admin/main";
	}
	
	
}
