package com.BOARD.DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.BOARD.DTO.boardDTO;
import com.MVC2.DTO.memberDTO;

public class boardDAO {
	
	private static boardDAO bDAO;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result;
	
	private boardDAO() {}
	
	public static synchronized boardDAO getInstance() {
		if(bDAO == null) {
			bDAO = new boardDAO();
		}
		return bDAO;
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
	
	public int nextval() {
		conn = getConnection();
		String sql = "SELECT MAX(num) FROM BOARD";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("MAX(num)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}
	
	public int Write(boardDTO bDTO) {
		
		conn = getConnection();
		String sql = "INSERT INTO BOARD VALUES (?,?,?,sysdate(),?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bDTO.getNum());
			pstmt.setString(2, bDTO.getTitle());
			pstmt.setString(3, bDTO.getName());
			pstmt.setString(4, bDTO.getText());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		
		return result;	
	}
	
	public ArrayList<boardDTO> selectList(){
		ArrayList<boardDTO> list = new ArrayList<boardDTO>();
		
		try {
			conn = getConnection();
			String sql ="SELECT * FROM BOARD ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardDTO bDTO = new boardDTO();
				bDTO.setNum(rs.getInt("num"));
				bDTO.setName(rs.getString("name"));
				bDTO.setTitle(rs.getString("title"));
				bDTO.setTime(rs.getTimestamp("time"));
				bDTO.setText(rs.getString("text"));
				list.add(bDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	public boardDTO Search(String title) {
		
		boardDTO bDTO = new boardDTO();
		try {
			conn = getConnection();
			String sql ="SELECT * FROM BOARD WHERE title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bDTO.setNum(rs.getInt("num"));
				bDTO.setName(rs.getString("name"));
				bDTO.setTitle(rs.getString("title"));
				bDTO.setTime(rs.getTimestamp("time"));
				bDTO.setText(rs.getString("text"));
				return bDTO;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}	
		return bDTO;
	}
	
	public void TextUpdate(String title , String text,String o_title) {
		try {
			conn = getConnection();
			String sql ="UPDATE BOARD SET title = ?, text =? WHERE title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, text);
			pstmt.setString(3, o_title);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}	
	}
	
	public void TextDelete(int num) {
		
		try {
			conn = getConnection();
			String sql ="DELETE FROM BOARD WHERE num = ?";
			String sql1 = "UPDATE BOARD SET num = num - 1 WHERE num > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}	
	}
}