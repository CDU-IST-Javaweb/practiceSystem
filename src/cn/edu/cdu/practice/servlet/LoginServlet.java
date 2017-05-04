package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面传入的各种值
		HttpSession session = request.getSession();
		//页面获得的用户输入的账号信息
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String Verification_Code = request.getParameter("verificationCode");
		String role = request.getParameter("role");
		//页面获得的由后台产生的验证码
		String vchidden = request.getParameter("vchidden");
		UserServiceImpl usi = new UserServiceImpl();
		if (usi.login(account, password, Verification_Code, role,vchidden)){
			//将role放入到session中
			session.setAttribute("role", role);
			//如果登录成功，跳转到对应页面
			request.getRequestDispatcher("/Login/index.jsp").forward(request, response);
		}else{
			//如果登录不成功，跳转到404页面
			request.getRequestDispatcher("/404.html").forward(request, response);
		}

	}

}
