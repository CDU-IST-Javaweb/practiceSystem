package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.utils.Log4jUtils;

/**
 * Servlet implementation class InfoPracticeServlet
 */
@WebServlet("/PracticeManagement/InfoPracticeServlet")
public class InfoPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");

		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		Project project = projectDaoImpl.findProjectByNo(no);
		if (project != null) {
			if (role.equals("1") && project.getCompanyUsername().equals(company_username))
				// 设置当前用户对info的所有权
				request.setAttribute("InfoRole", 1);
			else
				request.setAttribute("InfoRole", 0);
			request.setAttribute("infoProject", project);
			request.getRequestDispatcher("/PracticeManagement/infoPractice.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
