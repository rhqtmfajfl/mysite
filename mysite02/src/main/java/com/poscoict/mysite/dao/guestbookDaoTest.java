package com.poscoict.mysite.dao;

import java.util.List;

//import com.poscoict.guestbook.dao.guestbookDao;
//import com.poscoict.guestbook.vo.guestbookVo;
import com.poscoict.mysite.vo.GuestbookVo;

public class guestbookDaoTest {

	public static void main(String[] args) {
//		testInsert();
//		testFindAll();
//		testdelete(6,"1234");
//		testcount();
	}
	
//	private static void 

	private static void testcount() {
		// TODO Auto-generated method stub
		GuestbookVo vo = new GuestbookVo();
		
		
	}

	private static void testInsert() {
		GuestbookVo vo = new GuestbookVo();  //vo insert
//		vo.setFirstName("홍");
//		vo.setLastName("길동");
//		vo.setEmail("gilldong@gmail.com");
		
		vo.setName("가나다라");
		vo.setPassword("1234");
		vo.setMessage("가나다라@gmail.com");
		boolean result = new GuestbookDao().insert(vo);
		System.out.println(result ? "success" : "fail");
	}
	
	private static void testdelete(int i, String password) {
		GuestbookVo vo = new GuestbookVo();  //vo insert
//		vo.setFirstName("홍");
//		vo.setLastName("길동");
//		vo.setEmail("gilldong@gmail.com");
		
//		vo.setName("홍");
//		vo.setPassword("1234");
//		vo.setMessage("gilldong@gmail.com");
		boolean result = new GuestbookDao().delete(i, password);
		System.out.println(result ? "success" : "fail");
	}

	private static void testFindAll() {
		List<GuestbookVo> list = new GuestbookDao().findAll(); //ㄷ
		for(GuestbookVo vo : list) {
			System.out.println(vo);
		}
		
	}
}