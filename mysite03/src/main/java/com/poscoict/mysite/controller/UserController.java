package com.poscoict.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;

@Controller  //userController가 userservice를 사용한다. userserive는 sping-servlet.xml
@RequestMapping("/user") // from이나 이런 곳에서 절대 경로뒤에 /user 붙여 줄 수 있다.
public class UserController {  //userservice가 di 해준다.

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)  //여기는 join이 있는 곳으로 이동
	public String join() {  //여기서 join을 join form을 보여 준면 된다.
		return "user/join"; // get으로 받으면 단지 화면 이동이므로 user/join만 해주면 된다.
		// WEB-INF/views/user/join이다.
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST) //여기가 join.jsp에서 form에 의해 안의 name 값들이 보내진다.
	public String join(UserVo userVo) {  
		userService.join(userVo);  //userService에서 join을 사용한것 그래서 
		// return으로 보내 준다.
		System.out.println(userVo);  //화면에서 이쪽으로 들어오고
		return "redirect:/user/joinsuccess";  //redirect 해주면 WEB-INF/views이다
	}
	
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {  //여기서 join을 join form을 보여 준면 된다.
		return "user/joinsuccess";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {  //여기서 join을 join form을 보여 준면 된다.
		return "user/login";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(  // 여기서 이메일과 패스워드를 받아서 읽는다.
			HttpSession session,  
			@RequestParam(value="email", required=true, defaultValue="")String email,
			@RequestParam(value="password", required=true, defaultValue="")String password,
			Model model) {  //여기서 join을 join form을 보여 준면 된다.
		
		UserVo authUser = userService.getUser(email, password);  //여기서는 이메일과 패스워드를 받아와서 no name 등을 찾는다.
//			System.out.println(email + ":" + password);
		if(authUser==null) {
			model.addAttribute("result", "fail");
			model.addAttribute("email", email);
			return "user/login"; // 포워딩으로 다시 로그인 화면 생성하게끔
		}
		
		/*인증처리*/
		//로그인이 안되면 폼으로 돌리고 되면 login 성공하게끔
		session.setAttribute("authUser", authUser);
		
		
		
		return "redirect:/";
	}
	
	
	//로그아웃
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {  //여기서 join을 join form을 보여 준면 된다.
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	//인증이 필요한 경우 @Auth 사용해서 여기로 접근 할때 로그인이 되어있는지 안되어있는지 외부에서 확인한다.
	// 
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		/* access controller */
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		Long userNo = authUser.getNo();
		UserVo userVo = userService.getUser(userNo);
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session,UserVo userVo) {
		/* access controller */
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
//		System.out.println(userVo);
		
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
	
	
}