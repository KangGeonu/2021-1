package com.MVC2.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MVC2.DAO.memberDAO;

public class loginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("ID");
		String pw = req.getParameter("PW");
		
		ArrayList<String> list = new ArrayList<String>();
		memberDAO mDAO = memberDAO.getInstance();
		int loginResult = mDAO.Login(id, pw);
		HttpSession session = req.getSession();

		list = mDAO.Search(id);
		
		if (loginResult == 1) {
			req.setAttribute("loginResult", loginResult);
			session = req.getSession();
			session.setAttribute("loginID", id);
			session.setAttribute("loginPW", pw);
			session.setAttribute("loginNAME", list.get(0));
			session.setAttribute("loginEMAIL", list.get(1));
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("loginResult", loginResult);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/login.jsp");
			rd.forward(req, resp);
		}
	}

}
