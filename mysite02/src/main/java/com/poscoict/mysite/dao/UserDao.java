package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poscoict.mysite.vo.UserVo;

public class UserDao {
	public Long find_no(String name, String password) {

//		Long no = null;
		Long no = (long)0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select no from user where name = ? and password = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, name);
			pstmt.setString(2, password);
		
			
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
		
		return no;
	}
	
	public boolean update(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		if(vo.getPassword().isEmpty()) {
		try {
			conn = getConnection();
			
			String sql =
					" update user set name = ? , gender = ? where no = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setLong(3, vo.getNo());
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
		}else {
			try {
				conn = getConnection();
				
				String sql =
						" update user set name = ? ,gender = ?, password = ? where no = ?";
				//? 스트링 값으 바인드 하는 것이ㅏㄷ.
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setString(3, vo.getPassword());
				pstmt.setLong(4, vo.getNo());
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
	
	
	
	public boolean insert(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" insert" +
					" into user" +
					" values(null, ?, ?, ?, ?, now())";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
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

	public UserVo findByEmailAndPassword(String email, String password) {

		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select no, name from user where email = ? and password = ?";
			//? 스트링 값으 바인드 하는 것이ㅏㄷ.
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, email);
			pstmt.setString(2, password);
		
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Long no = rs.getLong(1); //첫번째
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
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
		
		return result;
	}	
}