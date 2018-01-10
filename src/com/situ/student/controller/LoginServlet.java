package com.situ.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.entity.User;
import com.situ.student.service.IUserService;
import com.situ.student.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet2
 */
public class LoginServlet extends BaseServlet {
private IUserService userService = new UserServiceImpl();
	
	public void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/user_login.jsp").forward(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = userService.login(name, password);
		if (user != null) {//登录成功
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/student?method=pageList");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/user?method=getLoginPage");
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.sendRedirect(request.getContextPath() + "/login?method=getLoginPage");
	}
}
