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
		
		int lastIndex = servletPath.lastIndexOf(".");
		String extension = "";
		if (lastIndex != -1) {
			extension = servletPath.substring(lastIndex);
		}
		
		//对于登录这个请求就不拦截，因为本身就是要登录
		if ("/login".equals(servletPath) 
				|| "/checkImg".equals(servletPath) 
				|| ".js".equalsIgnoreCase(extension)
				|| ".css".equalsIgnoreCase(extension)) {//都是放行
			chain.doFilter(request, response);
		} else {//需要验证
			//得到session对象
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {//沒有登录成功
				// req.getRequestDispatcher("WEB-INF/jsp/user_login.jsp").forward(request, response);
				resp.sendRedirect(req.getContextPath() + "/login?method=getLoginPage");
				return;
			}
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
	
	public static void main(String[] args) {
		String path = "/Java1711WebStudent/lib/jquery/jquery-1.11.1.js";
		String extension = path.substring(path.lastIndexOf(".") + 1);
		System.out.println(extension);
	}
}
