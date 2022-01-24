package com.poscoict.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(Model model) {

		Map<String, Object> map = boardService.getContentsList();
		
		model.addAttribute("board_list", map);
		System.out.println("asdfasdfasdf");
		System.out.println(map);
		System.out.println("asdfasdfasdf");
		return "/board/list";
	}
	
//	@RequestMapping(value="/list", method=RequestMethod.POST)
//	public String list(HttpSession session, Model model) {
////		List<BoardVo> list = new BoardRepository().find();
//		
//		
//		Map<String, Object> map = boardService.getContentsList();
//		model.addAttribute("board_list", map);
//
//		return "redirect:/board/list";
//	}
	
	
	
}
