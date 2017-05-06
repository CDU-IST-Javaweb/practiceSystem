package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.service.SystemParameterService;
import cn.edu.cdu.practice.service.impl.SystemParameterServiceImpl;

/**
 * Servlet implementation class SystemConfig
 */
@WebServlet("/SystemsManagement/SystemConfigServlet")
public class SystemConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SystemConfigServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SystemParameterService  systemParameterService = new SystemParameterServiceImpl();
		String userName = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		String releaseProjectStartDate = request.getParameter("releaseProjectStartDate");
		String releaseProjectEndDate = request.getParameter("releaseProjectEndDate");
		String studentSelStartDate = request.getParameter("studentSelStartDate");
		String studentSelEndDate = request.getParameter("studentSelEndDate");
		String studentSelMaxnum = request.getParameter("studentSelMaxnum");
	}

}
