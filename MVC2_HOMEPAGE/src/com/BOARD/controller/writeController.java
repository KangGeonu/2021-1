package com.BOARD.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BOARD.DAO.boardDAO;
import com.BOARD.DTO.boardDTO;

public class writeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/write.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String title = req.getParameter("TITLE");
		String name = (String)session.getAttribute("loginNAME");
		String text = req.getParameter("TEXT");
		text = text.replace("\n", "<br>");
		
		if(name == null) {
			name = "비회원";
		}
		boardDAO bDAO = boardDAO.getInstance();
		boardDTO bDTO = new boardDTO();
		
		bDTO.setNum(bDAO.nextval() + 1);
		bDTO.setTitle(title);
		bDTO.setName(name);
		bDTO.setText(text);
		
		bDAO.Write(bDTO);
		resp.sendRedirect("FREEBOARD");
		
	}
}