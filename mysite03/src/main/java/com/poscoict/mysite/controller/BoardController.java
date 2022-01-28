package com.poscoict.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(Model model) {

		Map<String, Object> map = boardService.getContentsList();
		
		model.addAttribute("board_list", map);
	
//		System.out.println("asdfasdfasdf");
//		System.out.println(map);
//		System.out.println("asdfasdfasdf");
		return "/board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable Long no, Model model) {
		
		BoardVo boardVo = boardService.getContents(no);
//no, title, contents, g_no as groupNo, o_no as orderNo, depth, user_no as userNo
		//이값을 리턴해준다. 그리고 이값을 boardVo라는 객체로 정해주고 view에 보내준다.
		model.addAttribute("boardVo", boardVo);
		System.out.println(boardVo.getNo());
		return "board/view";
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
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(HttpSession session, @PathVariable("no") Long no) {
		
		/* access control*/
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		System.out.println("no : " + no + ", authUser : " + authUser.getNo());
		System.out.println("no : " + no + ", authUser : " + authUser.getNo());
		System.out.println("no : " + no + ", authUser : " + authUser.getNo());
		System.out.println("no : " + no + ", authUser : " + authUser.getNo());

		System.out.println("no : " + no + ", authUser : " + authUser.getNo());
		boardService.deleteContents(no, authUser.getNo());
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write_form(HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session,
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="content", required=true, defaultValue="") String content) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		
		String username = authUser.getName();
		
		BoardVo vo = new BoardVo();

		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserName(username);
		vo.setUserNo(authUser.getNo());
		boardService.addContents(vo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/modifyform/{no}", method=RequestMethod.GET)
	public String modify(HttpSession session,
			@PathVariable("no") Long no, Model model) {
		/* access control */
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		
//		BoardVo modify_no = new BoardVo();
		
//		modify_no.setNo(no);
		
		//여기서 사용자 no를 가지고 와서  find로 찾느다.
		BoardVo update_view = boardService.getContents_modify(no);
		model.addAttribute("boardVo", update_view);

		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(HttpSession session, @PathVariable("no") Long no,
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="content", required=true, defaultValue="") String content) {
		
//		String username = authUser.getName();
		
		BoardVo vo = new BoardVo();
		
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(content);
//		vo.setUserName(username);
		
		boardService.updateContents(vo);
		return "redirect:/board";
	}
	
	
	//댓글 작성
	
	@RequestMapping(value="/addform/{no}", method=RequestMethod.GET)
	public String addform(HttpSession session,
			@PathVariable("no") Long no, Model model) {
		/* access control */
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		
		BoardVo reply_boardVo = boardService.getContents(no);
//no, title, contents, g_no as groupNo, o_no as orderNo, depth, user_no as userNo
		//이값을 리턴해준다. 그리고 이값을 boardVo라는 객체로 정해주고 view에 보내준다.
		model.addAttribute("reply_boardVo", reply_boardVo);

//		System.out.println("no : " + reply_boardVo.getNo());
		return "board/add";
	}
	
	@RequestMapping(value="/add/{no}", method=RequestMethod.POST)
	public String add(HttpSession session,
			@PathVariable("no") Long no,
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="content", required=true, defaultValue="") String content) {
		
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "user/login";
		}
		BoardVo reply_boardVo = boardService.getContents(no);
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(content);
//		vo.setHit(reply_boardVo.getHit());
		vo.setGroupNo(reply_boardVo.getGroupNo());
		vo.setOrderNo(reply_boardVo.getOrderNo()+1);
		vo.setDepth(reply_boardVo.getDepth()+1);
		vo.setUserNo(reply_boardVo.getUserNo());
		
//		System.out.println("no : "+ reply_boardVo.getUserNo());
		boardService.addContents(vo);
		
		return "redirect:/board";
	}
}