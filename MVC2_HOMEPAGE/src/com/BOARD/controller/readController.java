package com.BOARD.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BOARD.DAO.boardDAO;
import com.BOARD.DTO.boardDTO;

public class readController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("TITLE");
		
		boardDAO bDAO = boardDAO.getInstance();
		boardDTO bDTO = bDAO.Search(title);
		
		req.setAttribute("name",bDTO.getName());
		req.setAttribute("title", bDTO.getTitle());
		req.setAttribute("text", bDTO.getText());
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/read.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/자유게시판/read.jsp");
		rd.forward(req, resp);
	}

}
