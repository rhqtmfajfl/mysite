package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.UserVo;

//import com.poscoict.guestbook.vo.guestbookVo;

public class find_emailDao {
	public String find_email() {  //EmaillistDaoTest에서 사용하기 위한 findAll() 함수 생성
		// 반환 타입은 List의 EmaillistVo이다.
		List<UserVo> result = new ArrayList<>(); //ArrayList 생성
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();		
		
			//3. SQL 준비
			String sql = "select email from user order by no desc";
			pstmt = conn.prepareStatement(sql); // 
			
			//4. 바인딩(binding)
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
			
				String email = rs.getString(1);
				
				return email;
//				UserVo vo = new UserVo();
//				
//				vo.setEmail(email);
//				
//				
////				vo.setEmail(email);
//				
//				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.print("error : " + e);
		} finally {
			// 자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
}
