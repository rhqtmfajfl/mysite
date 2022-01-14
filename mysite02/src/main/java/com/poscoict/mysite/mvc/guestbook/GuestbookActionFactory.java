package com.poscoict.mysite.mvc.guestbook;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("deleteform".equals(actionName)) {//deleteform이 value 값이다 
			action = new DeleteformAction();
		} else if("delete".equals(actionName)) {
//			action = new IndexAction();  //리스트 보여주기
			action = new DeleteAction(); //전달 받을 url
		} else if("add".equals(actionName)) {
//			action = new IndexAction();  //리스트 보여주기
			action = new AddAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}