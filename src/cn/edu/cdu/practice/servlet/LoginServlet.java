package cn.edu.cdu.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cn.edu.cdu.practice.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取页面传入的各种值
		String Session_Verification_Code = (String) session.getAttribute("code");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String Verification_Code = request.getParameter("Verification_Code");
		String role = request.getParameter("role");
		UserServiceImpl usi = new UserServiceImpl();
		if (usi.login(account, password, Verification_Code, Session_Verification_Code, role)){
			
		}else{
			
		}
	}
	
}
