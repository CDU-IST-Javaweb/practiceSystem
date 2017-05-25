package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.model.ProProSelStuView;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class ChoicePracticeInfoServlet
 */
@WebServlet("/PracticeManagement/ChoicePracticeInfoServlet")
public class ChoicePracticeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoicePracticeInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 企业查询学生选择本企业方案情况
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company_username = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");
		if (company_username == null || !role.equals("1")) {
			//未通过身份验证
			//跳转到404页面,并打印错误信息
			String errorMessage = "当前用户无权访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else {
			// session 里保存用户查询方式
			// 键：selectChoiceType 值 ： 1:无条件查 2:按方案号查
			// 通过不同方式查询的首次请求来修改该值
			String selectChoiceType = request.getParameter("selectChoiceType");
			if (selectChoiceType != null && (selectChoiceType.equals("1") || selectChoiceType.equals("2"))) {
				// 此时用户请求新的查询方式（切换查询方式）
				request.getSession().setAttribute("selectChoiceType", selectChoiceType);
			} else
				selectChoiceType = (String) request.getSession().getAttribute("selectChoiceType");
			// 如果用户第一次访问该sevlet时未传入selectChoiceType值，自动设为1-无条件查
			if (selectChoiceType == null) {
				selectChoiceType = 1 + "";
				request.getSession().setAttribute("selectChoiceType", selectChoiceType);
			}

			String nowPage = request.getParameter("nowPage");
			if (nowPage == null)
				// 未得到请求的页数，默认为1
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			PageUtils pageUtils = null;
			if ((pageUtils = (PageUtils) request.getSession().getAttribute("choiceProjectInfoPageUtils")) == null) {
				pageUtils = new PageUtils(1, 0);
				pageUtils.setPageSize(10);
			} else {
				pageUtils.setPageNow(Integer.parseInt(nowPage));
			}
			ArrayList<ProProSelStuView> proProSelStuViews = null;

			if (selectChoiceType.equals("1")) {
				proProSelStuViews = projectDaoImpl.findAllStudentChoice(company_username, pageUtils);
			} else {
				String p_no = request.getParameter("selectChoiceByPNo");
				String selectChoiceByType=request.getParameter("selectChoiceByType");
				if (p_no != null&&selectChoiceByType!=null) {
					// p_no不为空说明是第一次有条件访问，需保存p_no selectChoiceByType的值，以备用户点击页面下一页时使用
					request.getSession().setAttribute("selectChoiceByPNo", p_no);
					request.getSession().setAttribute("selectChoiceByType", selectChoiceByType);
				} else {
					// 表示用户在查看其他页,此时页面没有传入p_no selectChoiceByType的值，从session获取
					p_no = (String) request.getSession().getAttribute("selectChoiceByPNo");
					selectChoiceByType = (String) request.getSession().getAttribute("selectChoiceByType");
				}
				if(p_no == null||selectChoiceByType==null){
					//跳转到404页面,并打印错误信息
					String errorMessage = "访问参数错误！";
					request.getSession().setAttribute("ErrorMessage", errorMessage);
					response.sendRedirect(request.getContextPath() + "/404.jsp");
					return;
				}
				proProSelStuViews = projectDaoImpl.findAllStudentChoiceByPNoAndType(p_no,selectChoiceByType, pageUtils);
			}
			if(proProSelStuViews==null){
				request.getRequestDispatcher("/PracticeManagement/enterpriseManagementStudents.jsp").forward(request,
						response);
				return;
			}
			//对学生是否已有确定的实训方案进行标识
			HashMap< String, Boolean> stuHasProject=new HashMap<>();
			for(ProProSelStuView proProSelStuView:proProSelStuViews){
				if(projectDaoImpl.findStuProject(proProSelStuView.getStudent().getNo()).size()==0){
					//表示没有已被企业确定的学生选择
					stuHasProject.put(proProSelStuView.getStudent().getNo(), true);
				}else{
					stuHasProject.put(proProSelStuView.getStudent().getNo(), false);
				}
			}
			request.setAttribute("stuHasProject", stuHasProject);
			
			// 查询企业所有方案，供页面通过方案号查询学生选择信息
			ArrayList<Project> cUserAllProject = projectDaoImpl.findAllProject(company_username);
			request.getSession().setAttribute("cUserAllProject", cUserAllProject);

			request.getSession().setAttribute("choiceProjectInfoPageUtils", pageUtils);

			request.setAttribute("proProSelStuViews", proProSelStuViews);
			request.getRequestDispatcher("/PracticeManagement/enterpriseManagementStudents.jsp").forward(request,
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
