package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import cn.edu.cdu.practice.service.impl.UserServiceImpl;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName LoginServlet.java
 * @version 1.0
 * @Description: 登录功能
 * @Author 于曦 @Date： 2017-4-17:下午21:04:04 Modification User： 程序修改时由修改人员编写
 *         Modification Date： 程序修改时间
 */
@WebServlet("/Login/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取页面传入的各种值
		HttpSession session = request.getSession();
		// 页面获得的用户输入的账号信息
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String Verification_Code = request.getParameter("verificationCode");
		String role = request.getParameter("role");
		System.out.println(account);
		// 页面获得的由后台产生的验证码
		String vchidden = request.getParameter("vchidden");
		UserServiceImpl usi = new UserServiceImpl();
		if (usi.login(account, password, Verification_Code, role, vchidden)) {
			// 将role放入到session中
			session.setAttribute("role", role);
			// 将用户名放入到session中
			session.setAttribute("account", account);
			// 如果登录成功，跳转到对应页面
			request.getRequestDispatcher("/Login/index.jsp").forward(request, response);
		} else {
			// 如果登录不成功，跳转到404页面,并打印错误信息
			String errorMessage = "登录失败，请检查输入是否正确！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}

	}

}
