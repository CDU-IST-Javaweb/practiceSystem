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
 * Servlet implementation class ChoiceStudentServlet
 */
@WebServlet("/PracticeManagement/ChoiceStudentServlet")
public class ChoiceStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoiceStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 对单个学生进行选择、退选
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String stu_no = request.getParameter("stu_no");
		String p_no = request.getParameter("p_no");
		/**
		 * 身份验证,方案是否属于当前登录的企业用户?
		 */
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		
		PageUtils pageUtils = null;
		if ((pageUtils = (PageUtils) request.getSession().getAttribute("choiceProjectInfoPageUtils")) == null) {
			pageUtils = new PageUtils(1, 0);
			pageUtils.setPageSize(10);
		}
		if (type.equals("1")) {
			// 选择学生
			boolean b = projectDaoImpl.chooseStudent(stu_no, p_no);
			request.getRequestDispatcher("ChoicePracticeInfoServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
		} else if (type.equals("2")) {
			// 退选学生
			boolean b=projectDaoImpl.unChooseStudent(new String[]{stu_no}, p_no);
			request.getRequestDispatcher("ChoicePracticeInfoServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
		} else {
			// 访问无效
		}
	}

	/**
	 * 批量选择学生
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
