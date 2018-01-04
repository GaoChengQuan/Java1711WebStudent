package com.situ.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet2
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取参数
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		//2.业务处理
		if ("zhangsan".equals(name) && "123".equals(password)) {
			//登录成功
			//2.1创建Session对象
			HttpSession session = req.getSession();
			//2.2把数据保存到域对象中
			session.setAttribute("userName", name);
			//2.3跳转到列表页面
			resp.sendRedirect(req.getContextPath() + "/findAll.do");
		} else {
			//登录失败
			resp.sendRedirect(req.getContextPath() + "/jsp/fail.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
