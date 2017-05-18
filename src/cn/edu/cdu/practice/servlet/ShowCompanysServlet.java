package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;

/**
 * Servlet implementation class ShowCompanysServlet
 */
@WebServlet("/EnterpriseManagement/ShowCompanyssServlet")
public class ShowCompanysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowCompanysServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String condition = request.getParameter("condition");
		if (condition == null || "".equals(condition)) {
			condition = "all";
		}
		CompanyService companyService = new CompanyServiceImpl();
		List<Company> list = null ;
		HttpSession session = request.getSession();
		try {
			list = companyService.queryCompanys(condition);
			session.setAttribute("companys", list);
			request.getRequestDispatcher("/EnterpriseManagement/enterprise-information-management.jsp").forward(request, response);
		} catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
	}

}
