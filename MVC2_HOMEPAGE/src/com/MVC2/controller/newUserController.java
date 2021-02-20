package com.MVC2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.MVC2.DAO.memberDAO;
import com.MVC2.DTO.memberDTO;

public class newUserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/newUser.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("ID");
		String pw = req.getParameter("PW");
		String name = req.getParameter("NAME");
		String email = req.getParameter("EMAIL");
		
		memberDTO mDTO = new memberDTO();
		memberDAO mDAO = memberDAO.getInstance();
		
		mDTO.setId(id);
		mDTO.setPw(pw);
		mDTO.setName(name);
		mDTO.setEmail(email);
		
		int newResult = mDAO.Insert(mDTO);
		
		if (newResult == 1) {
			req.setAttribute("newResult", 1);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("newResult", 0);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/newUser.jsp");
			rd.forward(req, resp);
		}

	}
}
