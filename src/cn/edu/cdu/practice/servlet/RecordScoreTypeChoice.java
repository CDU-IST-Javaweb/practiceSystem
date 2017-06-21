package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.model.Project;

/**
 * Servlet implementation class RecordScoreTypeChoice
 */
@WebServlet("/PracticeManagement/RecordScoreTypeChoice")
public class RecordScoreTypeChoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordScoreTypeChoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		if (company_username == null || !role.equals("1")) {
			// 未通过身份验证
			// 跳转到404页面,并打印错误信息
			String errorMessage = "当前用户无权访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else {
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();

			// 查询企业所有方案，供页面通过方案号查询学生成绩信息
			ArrayList<Project> cUserAllProject = (ArrayList<Project>) request.getSession()
					.getAttribute("cUserAllProject");
			if (cUserAllProject == null) {
				cUserAllProject = projectDaoImpl.findAllProject(company_username);
				request.getSession().setAttribute("cUserAllProject", cUserAllProject);
			}
			for (int i = 0; i < cUserAllProject.size(); i++) {
				if (cUserAllProject.get(i).getAuditDate() == null || cUserAllProject.get(i).getEndDate() != null)
					cUserAllProject.remove(i);
			}
			request.getSession().setAttribute("onStartProject", cUserAllProject);
			request.getRequestDispatcher("/PracticeManagement/recordScoreTypeChoice.jsp").forward(request,
					response);
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
