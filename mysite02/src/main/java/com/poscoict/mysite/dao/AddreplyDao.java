package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.BoardVo;

public class AddreplyDao {

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
	
	
	public BoardVo find_board_all(String no) {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select no, title, contents, hit, g_no, o_no, depth, reg_date, user_no "
				+ " from board "
				+ " where no = '" + no + "'";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no1 = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int hit = rs.getInt(4);
				int g_no = rs.getInt(5);
				int o_no = rs.getInt(6);
				int depth = rs.getInt(7);

				String reg_date = rs.getString(8);
				long user_no = rs.getLong(9);
				
				
				BoardVo vo = new BoardVo();
				vo.setNo(no1);
				vo.setTitle(title);
				vo.setContents(content);
				vo.setHit(hit);
				vo.setGroupNo(g_no);
				vo.setOrderNo(o_no);
				vo.setDepth(depth);

				vo.setRegDate(reg_date);
				vo.setUserNo(user_no);
				
//				list.add(vo);
				return vo;
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
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
	
	
	
	public boolean insert_write(BoardVo bvo) {
		boolean result = false;
		int hit = 0;
//		int group_no = 1;
		int order_no = 1;
		int depth = 1;
		
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" insert" +
					" into board(no, title, contents, hit, g_no, o_no, depth,  reg_date, user_no)" +
					"  values(null, ?, ?, ?, ?, ?, ?, now(), ?)";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());
			pstmt.setInt(3, hit);
			pstmt.setInt(4, bvo.getGroupNo());

			pstmt.setInt(5, bvo.getOrderNo()+1);
			pstmt.setInt(6,	bvo.getDepth()+1);

			pstmt.setLong(7, bvo.getUserNo());
			
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


	public boolean update_o_no(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" update board set o_no =(o_no + 1) where o_no > ? and g_no = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getOrderNo());
			
			pstmt.setLong(2, vo.getGroupNo());
			
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