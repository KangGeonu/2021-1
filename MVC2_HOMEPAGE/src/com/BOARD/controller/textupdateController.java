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

public class textupdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("TITLE");
		String text = req.getParameter("TEXT");
		String o_title = req.getParameter("O_TITLE");
		text = text.replace("\n", "<br>");
		boardDAO bDAO = boardDAO.getInstance();
		boardDTO bDTO = bDAO.Search(title);
		
		bDAO.TextUpdate(title,text,o_title);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/freeboard.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		String title = req.getParameter("TITLE");
		
		boardDAO bDAO = boardDAO.getInstance();
		boardDTO bDTO = bDAO.Search(title);
		
		req.setAttribute("name",bDTO.getName());
		req.setAttribute("title", bDTO.getTitle());
		req.setAttribute("text", bDTO.getText());
		if(session.getAttribute("loginNAME").equals(bDTO.getName())) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/update.jsp");
			rd.forward(req, resp);
		}else {
			req.setAttribute("approach", true);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/read.jsp");
			rd.forward(req, resp);
		}
	}

}
