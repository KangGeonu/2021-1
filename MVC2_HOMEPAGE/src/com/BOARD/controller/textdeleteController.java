package com.BOARD.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BOARD.DAO.boardDAO;

public class textdeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/freeboard.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int num = Integer.parseInt((String)req.getParameter("NUM"));
		boardDAO bDAO = boardDAO.getInstance();
		bDAO.TextDelete(num);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/freeboard.jsp");
		rd.forward(req, resp);
	}

}
