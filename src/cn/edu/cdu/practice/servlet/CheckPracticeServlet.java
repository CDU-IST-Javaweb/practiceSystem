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
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class CheckPracticeServlet
 */
@WebServlet("/PracticeManagement/CheckPracticeServlet")
public class CheckPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 审核退审方案
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		// 1或2，分别表示审核、退审
		String type = request.getParameter("type");
		boolean type_boolean = false;
		String role = (String) request.getSession().getAttribute("role");
		if ((type.equals("1") || type.equals("2")) && role.equals("9")) {
			if (Integer.parseInt(type) == 1)
				type_boolean = true;
			else if (Integer.parseInt(type) == 2)
				type_boolean = false;
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			// 审核退审实训方案，返回操作结果，成功与否都跳至同一界面
			boolean b = projectDaoImpl.checkProject(no, type_boolean);
			Log4jUtils.info("CheckPracticeServlet: no= " + no + " type= " + type + "  ");
			PageUtils pageUtils=(PageUtils) request.getSession().getAttribute("selectProjectPageUtils");
			
			request.getRequestDispatcher("/PracticeManagement/SelectPracticeServlet?nowPage="+pageUtils.getPageNow()).forward(request, response);
		} else {
			// 用户访问无效
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户无权访问，或访问方式非法！";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
