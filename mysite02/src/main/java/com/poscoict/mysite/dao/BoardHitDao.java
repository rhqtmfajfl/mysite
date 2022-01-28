package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

public class BoardHitDao extends BoardVo {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.0.58:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
	
	public String find_hit_no(String no) {

//		Long no = null;
//		Long no = (long)0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select no from board "
					+ "where no = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, no);
		
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String no1 = rs.getString(1); //첫번째
				
				return no1;
//				result = new UserVo();
//				result.setNo(no);
//				result.setName(name);
			}
			//
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs !=null) {
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
		
		return no;
	}

	public Long find_hit_no_long(String no) {

//		Long no = null;
		Long no2 = (long)0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select no from board "
					+ "where no = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, no);
		
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Long no1 = rs.getLong(1); //첫번째
				
				return no1;
//				result = new UserVo();
//				result.setNo(no);
//				result.setName(name);
			}
			//
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs !=null) {
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
		
		return no2;
	}
	
	
	public boolean update_hit(BoardVo bv) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" update board set hit = ? where no = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bv.getHit());
			
			pstmt.setLong(2, bv.getNo());
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		
		return result;

		
	}
}