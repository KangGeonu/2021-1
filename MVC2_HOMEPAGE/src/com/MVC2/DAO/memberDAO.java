package com.MVC2.DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpSession;

import com.MVC2.DTO.*;

public class memberDAO {
	
	private static memberDAO mDAO;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result;
	
	private memberDAO() {}
	
	public static synchronized memberDAO getInstance() {
		if(mDAO == null) {
			mDAO = new memberDAO();
		}
		return mDAO;
	}
	
	public Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/mvc_practice?serverTimezone=Asia/Seoul";
		String id = "mvc";
		String pw = "mvc";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn,PreparedStatement pstmt , ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int Insert(memberDTO mDTO) {
		
		conn = this.getConnection();
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mDTO.getId());
			pstmt.setString(2,mDTO.getPw());
			pstmt.setString(3,mDTO.getName());
			pstmt.setString(4,mDTO.getEmail());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close(conn,pstmt,null);
		}
		return result;
	}
	
	public int Login(String id, String pw) {
		
		conn = this.getConnection();
		String sql = "SELECT pw FROM MEMBERS WHERE ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pw").equals(pw)) {
					return 1;
				}else {
					return 0;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,rs);
		}
		return -1;
	}
	
	public ArrayList<String> Search(String id){
		conn = this.getConnection();
		String sql = "SELECT pw,name,email FROM MEMBERS WHERE ID = ?";
		ArrayList<String> list = new ArrayList<String> ();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list.add(rs.getString("name"));
				list.add(rs.getString("email"));
			}
			
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,rs);
		}
		return null;
	}
	
	public void Update(String id,String pw,String name,String email){
		conn = this.getConnection();
		String sql = "UPDATE MEMBERS set pw = ?,name = ?,email = ? WHERE id = ?";
		ArrayList<String> list = new ArrayList<String> ();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			pstmt.setString(2,name);
			pstmt.setString(3,email);
			pstmt.setString(4,id);
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,rs);
		}
		
	}
}