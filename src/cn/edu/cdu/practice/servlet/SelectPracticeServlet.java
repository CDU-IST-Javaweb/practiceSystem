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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int role=Integer.parseInt(request.getParameter("no"));
		String company_username=request.getParameter("company_username");
		company_username="sayHello";
		ProjectDaoImpl projectDaoImpl=new ProjectDaoImpl();
		PageUtils pageUtils=null;
		if((pageUtils=(PageUtils) request.getSession().getAttribute("selectProjectPageUtils"))==null){
			pageUtils=new PageUtils(1, 0);
			pageUtils.setPageSize(10);
		}else{
			pageUtils.setPageNow(pageUtils.getPageNow()+1);
		}
		ArrayList<Project> projects=projectDaoImpl.findAllProject(role, company_username, pageUtils);
		// 对查到的数据进行遍历
		for(int i=0;i<projects.size();i++){
			Log4jUtils.info(projects.get(i).getName()+" "+projects.get(i).getNo()+" ");
		}
		request.setAttribute("selectProjects", projects);
		request.setAttribute("selectProjectsRole", role);
		request.getRequestDispatcher("/PracticeManagement/programManagement.jsp").forward(request, response);
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
