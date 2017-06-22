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

		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if (role.equals("1") || role.equals("9")) {

			if (p_no == null) {
				p_no = (String) request.getSession().getAttribute("recordingScoreByPNo");
				if (p_no == null) {
					// 如果没有no，则获取查询学生选择方案情况时保存在session的selectChoiceByPNo
					p_no = (String) request.getSession().getAttribute("selectChoiceByPNo");
				}
			}
			request.getSession().setAttribute("recordingScoreByPNo", p_no);
			// 依然没有得到，那么不做处理，要求用户提交数据 或者用户角色不满足
			if (p_no == null || !role.equals("1")) {
				// 跳转到404页面,并打印错误信息
				String errorMessage = "要求提交数据 或者用户角色不满足！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
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
			// 如果登录不成功，跳转到404页面,并打印错误信息
			String errorMessage = "当前用户无权访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
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

		System.out.println(stu_no[0] + "  " + score[0]);
		String c_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");

		ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
		if (role == null) {
			// 跳转到404页面,并打印错误信息
			String errorMessage = "用户权限不足！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
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
		} else {
			// 跳转到404页面,并打印错误信息
			String errorMessage = "用户权限不足！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}
}
