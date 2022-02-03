package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.poscoict.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(UserVo userVo) {
		return 1 == sqlSession.update("user.update", userVo);
	}
	
	public boolean insert(UserVo vo) {
	return 1== sqlSession.insert("user.insert", vo);
	}
	

	public UserVo findByNo(Long userNo) {
		return sqlSession.selectOne("user.findByNo", userNo);
	}

	public UserVo findByEmailAndPassword(String email, String password) {
	
		
		
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		
		
		//속도를 제려면 코드 변경 로그인할때 속도
		return  sqlSession.selectOne("user.findByEmailAndPassword", map);
		
	
		
	}	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.0.58:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}


}