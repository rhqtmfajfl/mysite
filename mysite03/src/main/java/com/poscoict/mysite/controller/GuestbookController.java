package com.poscoict.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired  //annotatin으로 자동으로 생성
	private GuestbookService guestbookService; //new 객체를 따로 선언을 안하고 그냥 여기서 @Autowired를 사용해서 편하게 간단
	
	
	@RequestMapping("/spa")   // emaillist03 하면 들어가게끔 해준다.  
	public String spa(Model model) {
		
		return "guestbook/index-spa";    //"emaillist"; guestbook의 index로 보낸다.
	}
	
//	@ResponseBody  //AJAX 사용전에는 굳이 사용안해도 됨
	@RequestMapping("")   // emaillist03 하면 들어가게끔 해준다.  
	public String index(Model model) {
		
//		System.out.println(emaillistRepository);
		List<GuestbookVo> list = guestbookService.findAll(); // guestbook
		
		model.addAttribute("list", list);  //list형식으로 객체 만듬
		// 그래서 보낸다.
		
		System.out.println("testsetsetasetaet");
		return "guestbook/index";    //"emaillist"; guestbook의 index로 보낸다.
	}
	
	
	
//	@RequestMapping("/index")  // guestbook에 등록한 리스트명
//	public String form() {
//		
//		
//		return "/WEB-INF/views/index.jsp";
//		
//		
//	}
	
	
	@RequestMapping(value = "/add" , method=RequestMethod.POST)  //insert 가능하게 됨
	public String add(GuestbookVo vo) {
		guestbookService.addMessage(vo); // guestbookService.addMessage(vo)로 메시지를 
		
		System.out.println(vo);
		return "redirect:/guestbook";
		
	}
	
	@RequestMapping(value = "/deleteform" , method=RequestMethod.GET)  //delete 가능하게 됨
	public String delete(Long no, Model model) {
//		guestbookRepository.delete(vo);
		model.addAttribute("no", no);
//		System.out.println(vo);
		return "guestbook/deleteform";
		
	}
	
	@RequestMapping(value = "/delete" , method=RequestMethod.POST)  //delete 가능하게 됨
	public String delete(Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		guestbookService.deleteMessage(no,password);
		
//		System.out.println(vo);
		return "redirect:/guestbook";
		
	}
}