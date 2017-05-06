package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistCompanyServlet
 */
@WebServlet("/EnterpriseManagement/RegistCompanyServlet")
public class RegistCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST解码
		request.setCharacterEncoding("UTF-8");
		String rscode = request.getParameter("rscode");
		String qyname = request.getParameter("qyname");
		String qyusername = request.getParameter("qyusername");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String yzm = request.getParameter("yzm");
	}

}
