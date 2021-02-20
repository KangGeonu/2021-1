package com.MVC2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MVC2.DAO.memberDAO;

public class updateController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String id = (String)req.getParameter("ID"); 
		String pw = (String)req.getParameter("PW"); 
		String name = (String)req.getParameter("NAME"); 
		String email = (String)req.getParameter("EMAIL"); 
		
		memberDAO mDAO = memberDAO.getInstance();
		mDAO.Update(id, pw, name, email);
		
		HttpSession session = req.getSession();
		session.setAttribute("loginID",id);
		session.setAttribute("loginPW",pw);
		session.setAttribute("loginNAME",name);
		session.setAttribute("loginEMAIL",email);
		req.setAttribute("updateResult", 1);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(req, resp);	
	}
}
