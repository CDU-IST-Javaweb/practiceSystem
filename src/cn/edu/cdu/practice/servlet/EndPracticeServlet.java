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
 * Servlet implementation class EndPracticeServlet
 */
@WebServlet("/PracticeManagement/EndPracticeServlet")
public class EndPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EndPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = (String) request.getSession().getAttribute("role");
		if (role.equals("9")) {
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			ArrayList<Project> projects = projectDaoImpl.findAllStartedProject();
			String[] p_no = new String[projects.size()];
			for (int i = 0; i < projects.size(); i++)
				p_no[i] = projects.get(i).getNo();
			projectDaoImpl.endProjects(p_no);
			request.getRequestDispatcher("/PracticeManagement/SelectPracticeServlet").forward(request, response);
		}else{
			//不是管理员，无权访问，跳至404页面
			request.getRequestDispatcher("/404.html").forward(request, response);
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
