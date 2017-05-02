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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String company_username = request.getParameter("company_username");
		if (company_username == null)
			company_username = "sayHello";
		
		String nowPage = request.getParameter("nowPage");
		if(nowPage==null)
			nowPage=1+"";
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		PageUtils pageUtils = null;
		if ((pageUtils = (PageUtils) request.getSession().getAttribute("choiceProjectInfoPageUtils")) == null) {
			pageUtils = new PageUtils(1, 0);
			pageUtils.setPageSize(10);
		} else {
			pageUtils.setPageNow(Integer.parseInt(nowPage));
		}
		ArrayList<ProProSelStuView> proProSelStuViews = projectDaoImpl.findAllStudentChoice(company_username, pageUtils);
		request.getSession().setAttribute("choiceProjectInfoPageUtils", pageUtils);
		// 对查到的数据进行遍历
		// for(int i=0;i<projects.size();i++){
		// Log4jUtils.info(projects.get(i).getName()+"
		// "+projects.get(i).getNo()+" ");
		// }
		request.setAttribute("proProSelStuViews", proProSelStuViews);
		request.getRequestDispatcher("/PracticeManagement/enterpriseManagementStudents.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
