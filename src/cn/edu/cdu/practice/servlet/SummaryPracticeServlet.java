package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.service.impl.ProjectServiceImpl;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class SummaryPracticeServlet
 */
@WebServlet("/PracticeManagement/SummaryPracticeServlet")
public class SummaryPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SummaryPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**总结实训方案
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String no = request.getParameter("no");
		String summary = request.getParameter("summary");
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if (role.equals("1") && projectServiceImpl.findProjectBelongToUserByPNo(company_username, no)) {
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			boolean b = projectDaoImpl.summaryProject(no, summary);
			PageUtils pageUtils = null;
			int pageNow = 1;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("selectProjectPageUtils")) != null) {
				pageNow = pageUtils.getPageNow();
			}
			request.getRequestDispatcher("/PracticeManagement/SelectPracticeServlet?pageNow=" + pageNow)
					.forward(request, response);

		} else {
			//跳转到404页面,并打印错误信息
			String errorMessage = "用户权限不足！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}

	}

}
