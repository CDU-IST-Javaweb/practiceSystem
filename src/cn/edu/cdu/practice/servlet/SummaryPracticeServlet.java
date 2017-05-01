package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 未设置身份判断
		 */
		request.setCharacterEncoding("utf-8");
		String no = request.getParameter("no");
		String summary = request.getParameter("summary");
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		boolean b = projectDaoImpl.summaryProject(no, summary);
		PageUtils pageUtils = null;
		int pageNow = 1;
		if ((pageUtils = (PageUtils) request.getSession().getAttribute("selectProjectPageUtils")) != null) {
			pageNow=pageUtils.getPageNow();
		}
		request.getRequestDispatcher("/PracticeManagement/SelectPracticeServlet?pageNow="+pageNow).forward(request, response);

	}

}
