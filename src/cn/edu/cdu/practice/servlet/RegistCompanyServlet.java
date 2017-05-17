package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;

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
		String email = request.getParameter("email");
		String yzm = request.getParameter("yzm");
		System.out.println("进入Servlet");
		try {
			CompanyService companyService = new CompanyServiceImpl();
			if (companyService.registerCompanyInfo(qyusername, qyname, email, password, rscode, yzm)) {
				request.getRequestDispatcher("/Login/login.jsp").forward(request, response);
				return;
			}else {
				response.sendRedirect("http://202.115.82.8:8080/404.jsp");
				//request.getRequestDispatcher("/404.html").forward(request, response);
				return ;
			}
		} catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
	}

}
