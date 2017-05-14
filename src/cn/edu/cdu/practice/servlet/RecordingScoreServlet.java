package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.model.ProProSelStuView;
import cn.edu.cdu.practice.service.impl.ProjectServiceImpl;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class RecordingScoreServlet
 */
@WebServlet("/PracticeManagement/RecordingScoreServlet")
public class RecordingScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordingScoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p_no = request.getParameter("no");
		String c_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		/**
		 * 测试用数据
		 */
		c_username = "sayHello";
		role = 1 + "";
		p_no = 2017000009 + "";

		if (role != null) {
			if (p_no == null) {
				p_no = (String) request.getSession().getAttribute("recordingScoreByPNo");
				if (p_no == null) {
					// 如果没有no，则获取查询学生选择方案情况时保存在session的selectChoiceByPNo
					p_no = (String) request.getSession().getAttribute("selectChoiceByPNo");
				}
			}
			request.getSession().setAttribute("recordingScoreByPNo", p_no);
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			// 依然没有得到，那么不做处理，要求用户提交数据 或者用户角色不满足
			if (p_no == null || !role.equals("1")) {
				request.getRequestDispatcher("/404.html").forward(request, response);
			} else if (c_username != null && projectServiceImpl.findProjectBelongToUserByPNo(c_username, p_no)) {
				// 用户身份正确，对方案拥有权限
				String nowPage = request.getParameter("nowPage");
				if (nowPage == null)
					// 未得到请求的页数，默认为1
					nowPage = 1 + "";
				PageUtils pageUtils = null;
				if ((pageUtils = (PageUtils) request.getSession().getAttribute("recordingScorePageUtils")) == null) {
					pageUtils = new PageUtils(1, 0);
					pageUtils.setPageSize(10);
				} else {
					pageUtils.setPageNow(Integer.parseInt(nowPage));
				}
				ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
				ArrayList<ProProSelStuView> recordingScoreView = projectDaoImpl.findStuScoreByPNo(p_no, pageUtils);
				request.setAttribute("recordingScoreView", recordingScoreView);

				request.getSession().setAttribute("recordingScorePageUtils", pageUtils);
				request.getRequestDispatcher("recordingScore.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/404.html").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p_no = (String) request.getSession().getAttribute("recordingScoreByPNo");
		String[] stu_no = request.getParameterValues("stu_no");
		String[] score = request.getParameterValues("score");
		
		System.out.println(stu_no[0]+"  "+score[0]);
		String c_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		/**
		 * 测试用数据
		 */
		c_username = "sayHello";
		role = 1 + "";
		p_no = 2017000009 + "";

		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if (role == null){
			request.getRequestDispatcher("/404.html").forward(request, response);
			return;
		}
		if (role.equals("1") && c_username != null
				&& projectServiceImpl.findProjectBelongToUserByPNo(c_username, p_no)) {
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("recordingScorePageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
				pageUtils.setPageNow(1);
			}
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			projectDaoImpl.inputScore(stu_no, score, p_no);
			
			ArrayList<ProProSelStuView> recordingScoreView = projectDaoImpl.findStuScoreByPNo(p_no, pageUtils);
			request.setAttribute("recordingScoreView", recordingScoreView);

			request.getSession().setAttribute("recordingScorePageUtils", pageUtils);
			request.getRequestDispatcher("recordingScore.jsp").forward(request, response);
		}
	}
}
