package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.BoardVo;

public class BoardDao {

	
//	public List<GuestbookVo> findAll() {
//		List<GuestbookVo> list = new ArrayList<>();
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//				
//		try {
//			conn = getConnection();
//			
//			String sql =
//				"   select no, name, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, message" +
//				"     from guestbook" +
//				" order by reg_date desc";
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				Long no = rs.getLong(1);
//				String name = rs.getString(2);
//				String regDate = rs.getString(3);
//				String message = rs.getString(4);
//				
//				GuestbookVo vo = new GuestbookVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setRegDate(regDate);
//				vo.setMessage(message);
//				
//				list.add(vo);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return list;
//	}
	
	
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
	
	public Long findno() {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select user_no "
				+ " from board";
				
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				Long no1 = rs.getLong(1);
//				String title = rs.getString(2);
//				String content = rs.getString(3);
//				int hit = rs.getInt(4);
//				int g_no = rs.getInt(5);
//				int o_no = rs.getInt(6);
//				int depth = rs.getInt(7);
//
//				String reg_date = rs.getString(8);
				Long user_no = rs.getLong(1);
				
//				BoardVo vo = new BoardVo();
//				vo.setNo(no1);
//				vo.setTitle(title);
//				vo.setContents(content);
//				vo.setHit(hit);
//				vo.setGroupNo(g_no);
//				vo.setOrderNo(o_no);
//				vo.setDepth(depth);
//
//				vo.setRegDate(reg_date);
//				vo.setUserNo(user_no);
				
				
				
				
//				list.add(vo);
				return user_no;
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
	
	public BoardVo findall(String no) {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select no, title, contents, hit, g_no, o_no, depth, reg_date "
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
				
				BoardVo vo = new BoardVo();
				vo.setNo(no1);
				vo.setTitle(title);
				vo.setContents(content);
				vo.setHit(hit);
				vo.setGroupNo(g_no);
				vo.setOrderNo(o_no);
				vo.setDepth(depth);

				vo.setRegDate(reg_date);
				
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
//LIST FIND 목록 보여주는것
	public List<BoardVo> find() {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select b.no, b.title, a.name, b.hit, b.reg_date, b.user_no "
				+ " from user a, board b  "
				+ " where a.no = b.user_no "
				+ " order by b.no desc, b.reg_date asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String reg_date = rs.getString(5);
				Long userno = rs.getLong(6);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setRegDate(reg_date);
				vo.setUserNo(userno);

				list.add(vo);
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
		
		return list;
	}
	
	
	
	public String findtitle(String no) {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select title  "
				+ " from board"
				+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString(1);
//				String contents = rs.getString(2);
				
				
				BoardVo vo = new BoardVo();
//				vo.setTitle(title);
//				vo.setContents(contents);

				return title;
//				list.add(vo);
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
	
	
	public String findcontent(String no) {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
				"select contents  "
				+ " from board"
				+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, no);

			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String contents = rs.getString(1);
//				String contents = rs.getString(2);
				
				
				BoardVo vo = new BoardVo();
//				vo.setTitle(title);
//				vo.setContents(contents);

				return contents;
//				list.add(vo);
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
	

//	public String findno(String no) {
//		List<BoardVo> list = new ArrayList<>();
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//				
//		try {
//			conn = getConnection();
//			
//			String sql =
//				"select contents  "
//				+ " from board"
//				+ " where no = ?";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, no);
//
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				String contents = rs.getString(1);
////				String contents = rs.getString(2);
//				
//				
//				BoardVo vo = new BoardVo();
////				vo.setTitle(title);
////				vo.setContents(contents);
//
//				return contents;
////				list.add(vo);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return null;
//	}

//	public Object findcontent() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
