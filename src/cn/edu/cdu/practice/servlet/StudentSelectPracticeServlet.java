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
import cn.edu.cdu.practice.dao.impl.StudentDaoImpl;
import cn.edu.cdu.practice.model.Project;
import cn.edu.cdu.practice.model.ProjectSelect;
import cn.edu.cdu.practice.model.Student;

/**
 * Servlet implementation class StudentSelectPracticeServlet
 */
@WebServlet("/PracticeManagement/StudentSelectPracticeServlet")
public class StudentSelectPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentSelectPracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 学生查询可选方案
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stu_no = (String) request.getSession().getAttribute("account");
		String role = (String) request.getSession().getAttribute("role");

		if (role.equals("2")) {
			StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
			Student student = studentDaoImpl.findById(stu_no);

			String nowPage = request.getParameter("nowPage");
			if (nowPage == null)
				nowPage = 1 + "";
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();

			ArrayList<Project> projects = projectDaoImpl.findAllProject(student.getGrade());

			// 查询学生已选方案
			ArrayList<Project> chosenProject = projectDaoImpl.findAllChosenProject(student.getNo());
			if (chosenProject == null) {
				// 查询学生已选方案失败，无法继续

			} else {
				HashMap<String, Integer> choiceState = new HashMap<>();
				for (int i = 0; i < projects.size(); i++) {
					for (int j = 0; j < chosenProject.size(); j++) {
						if (projects.get(i).getNo().equals(chosenProject.get(j).getNo())) {
							choiceState.put(projects.get(i).getNo(), 1);
							break;
						} else {
							choiceState.put(projects.get(i).getNo(), 0);
						}
					}
				}
				ArrayList<ProjectSelect> projectSelects=projectDaoImpl.findStuProject(stu_no);
				if(projectSelects.size()>0){
					request.setAttribute("stuProjectNo", projectSelects.get(0).getId().getProjectNo().toString());
				}else{
					request.setAttribute("stuProjectNo", "0");
				}
				
				request.setAttribute("selectProjects", projects);
				request.setAttribute("choiceState", choiceState);
				request.getRequestDispatcher("/PracticeManagement/studentSelectPractice.jsp").forward(request,
						response);
			}
		} else {
			// 角色不匹配
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
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
