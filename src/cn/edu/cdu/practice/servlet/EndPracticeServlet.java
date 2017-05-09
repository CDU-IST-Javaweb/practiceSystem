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

/**
 * Servlet implementation class EndPracticeServlet
 */
@WebServlet("/PracticeManagement/EndPracticeServlet")
public class EndPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndPracticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r = (String) request.getSession().getAttribute("role");
		int role;
		/**
		 * 用于测试，默认赋值为9
		 */
		if (r == null)
			role = 9;
		else
			role=Integer.parseInt(r);
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
		ArrayList<Project> projects=projectDaoImpl.findAllStartedProject();
		String[] p_no=new String[projects.size()];
		for(int i=0;i<projects.size();i++)
			p_no[i]=projects.get(i).getNo();
		projectDaoImpl.endProjects(p_no);
		request.getRequestDispatcher("/PracticeManagement/SelectPracticeServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
