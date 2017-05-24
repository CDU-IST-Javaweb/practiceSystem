package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.CompanyDaoImpl;
import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.service.impl.ProjectServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class SelectPracticeServlet
 */
@WebServlet("/PracticeManagement/SelectPracticeServlet")
public class SelectPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过session里的登录对象的身份(企业、管理员)获取对应方案信息(分页查询)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		// role=9+"";
		if (role == null) {
			System.out.println("role 为空");
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户权限不足,role 为空！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else if (role.equals("1") || role.equals("9")) {
			// session 里保存用户查询方式
			// 键：selectProjectType 值 ： 1:无条件查 2:按年份、审核状态查
			// 通过不同方式查询的首次请求来修改该值
			String selectProjectType = request.getParameter("selectProjectType");
			if (selectProjectType != null && (selectProjectType.equals("1") || selectProjectType.equals("2"))) {
				request.getSession().setAttribute("selectProjectType", selectProjectType);
			} else
				selectProjectType = (String) request.getSession().getAttribute("selectProjectType");
			// 如果用户第一次访问该sevlet时未传入selectProjectType值，自动设为1-无条件查
			if (selectProjectType == null) {
				selectProjectType = 1 + "";
				request.getSession().setAttribute("selectProjectType", selectProjectType);
			}
			String nowPage = request.getParameter("nowPage");
			if (nowPage == null)
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("selectProjectPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			ArrayList<Project> projects = null;
			if (selectProjectType.equals("1"))
				projects = projectDaoImpl.findAllProject(Integer.parseInt(role), company_username, pageUtils);
			else {
				String year = request.getParameter("selectByYear");
				String state = request.getParameter("selectByState");
				// year不为空说明是第一次有条件访问，需保存year和state的值，以备用户点击页面下一页时使用
				if (year != null && state != null) {
					request.getSession().setAttribute("selectByYear", year);
					request.getSession().setAttribute("selectByState", state);
				} else {
					// 表示用户在查看其他页,此时页面没有传入year和state的值，从session获取
					year = (String) request.getSession().getAttribute("selectByYear");
					state = (String) request.getSession().getAttribute("selectByState");
				}
				boolean checkState = state.equals("1") ? true : false;
				projects = projectDaoImpl.findAllProject(Integer.parseInt(role), company_username, pageUtils,
						checkState, year);
			}

			// 通过方案号保存方案所属企业对象
			HashMap<String, Company> companyInfo = new HashMap<>();
			CompanyDaoImpl companyDaoImpl = new CompanyDaoImpl();
			if (projects != null) {
				for (int i = 0; i < projects.size(); i++) {
					Company company = companyDaoImpl.queryByUserName(projects.get(i).getCompanyUsername());
					companyInfo.put(projects.get(i).getNo(), company);
				}
			}
			
			//管理员是否开启企业添加方案
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			request.setAttribute("AddPracticeIsUnderWay", projectServiceImpl.findAddPracticeIsUnderWay());
			
			request.setAttribute("companyInfo", companyInfo);
			request.getSession().setAttribute("selectProjectPageUtils", pageUtils);

			request.setAttribute("selectProjects", projects);
			request.setAttribute("selectProjectsRole", role);
			request.getRequestDispatcher("programManagement.jsp").forward(request, response);
		} else {
			// 学生无法看到
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户权限不足！";
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
