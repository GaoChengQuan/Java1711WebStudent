package com.situ.student.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.entity.User;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		// /Java1711WebStudent/login
		System.out.println(uri);
		String servletPath = req.getServletPath();
		//对于登录这个请求就不拦截，因为本身就是要登录
		if (!"/login".equalsIgnoreCase(servletPath)) {
			//得到session对象
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {//沒有登录成功
				req.getRequestDispatcher("WEB-INF/jsp/user_login.jsp").forward(request, response);
				return;
			}
		}
		// 1、要去登录 /login 2、已经登录
		//验证成功,放行（可以访问servlet）
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
