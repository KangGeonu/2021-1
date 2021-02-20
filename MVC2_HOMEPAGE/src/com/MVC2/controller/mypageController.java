package com.MVC2.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MVC2.DAO.*;

public class mypageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("loginID") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/login.jsp");
			req.setAttribute("mypage", false);
			rd.forward(req, resp);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/mypage.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("loginID");

		if(id == null) {
			req.setAttribute("mypage",false);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/login.jsp");
			rd.forward(req, resp);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/회원관리/mypage.jsp");
			rd.forward(req, resp);
		}	
	}
}
