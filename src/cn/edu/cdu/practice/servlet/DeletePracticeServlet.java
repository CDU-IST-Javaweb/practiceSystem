package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.service.impl.ProjectServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;

/**
 * Servlet implementation class DeletePracticeServlet
 */
@WebServlet("/PracticeManagement/DeletePracticeServlet")
public class DeletePracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 删除实训方案
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		ProjectServiceImpl projectServiceImpl=new ProjectServiceImpl();
		if(role.equals("1")&&projectServiceImpl.findProjectBelongToUserByPNo(company_username, no)){
			//角色为企业并对该方案拥有权限
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			boolean b = projectDaoImpl.deleteProject(no);
			request.getRequestDispatcher("/PracticeManagement/SelectPracticeServlet").forward(request, response);
		}else{
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
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
