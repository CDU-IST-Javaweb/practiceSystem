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
		String Session_Verification_Code = (String) session.getAttribute("code");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String Verification_Code = request.getParameter("Verification_Code");
		String role = request.getParameter("role");
		UserServiceImpl usi = new UserServiceImpl();
		if (usi.login(account, password, Verification_Code, Session_Verification_Code, role)){
			//如果登录成功，跳转到对应页面
			request.getRequestDispatcher("/Login/index.html").forward(request, response);
		}else{
			//如果登录不成功，跳转到404页面
			request.getRequestDispatcher("/404.html").forward(request, response);
		}

	}

}
