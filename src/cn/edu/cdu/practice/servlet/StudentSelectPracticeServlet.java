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
import cn.edu.cdu.practice.model.Project;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r = request.getParameter("role");
		int role;
		if (r == null)
			role = 2;
		else
			role=Integer.parseInt(r);
		Student student=(Student) request.getSession().getAttribute("student");
		/**
		 * 为方便测试，自造一个student对象，该对象存在于数据库
		 */
		if (student == null){
			student=new Student();
			student.setGrade(2014);
			student.setNo("201410414001");
		}
		String nowPage = request.getParameter("nowPage");
		if(nowPage==null)
			nowPage=1+"";
		ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
//		PageUtils pageUtils = null;
//		if ((pageUtils = (PageUtils) request.getSession().getAttribute("stuSelectProjectPageUtils")) == null) {
//			pageUtils = new PageUtils(1, 0);
//			pageUtils.setPageSize(10);
//		} else {
//			pageUtils.setPageNow(Integer.parseInt(nowPage));
//		}
		ArrayList<Project> projects = projectDaoImpl.findAllProject(student.getGrade());
		
		//查询学生已选方案
		ArrayList<Project> chosenProject=projectDaoImpl.findAllChosenProject(student.getNo());
		if(chosenProject==null){
			//查询学生已选方案失败，无法继续
			
		}else{
			HashMap<String, Integer> choiceState=new HashMap<>();
			for(int i=0;i<projects.size();i++){
				for(int j=0;j<chosenProject.size();j++){
					if(projects.get(i).getNo().equals(chosenProject.get(j).getNo())){
						choiceState.put(projects.get(i).getNo(), 1);
						break;
					}else{
						choiceState.put(projects.get(i).getNo(), 0);
					}
				}
			}
			request.setAttribute("choiceState", choiceState);
			//request.getSession().setAttribute("stuSelectProjectPageUtils", pageUtils);
			request.setAttribute("selectProjects", projects);
			request.setAttribute("selectProjectsRole", role);
			request.getRequestDispatcher("/PracticeManagement/studentSelectPractice.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
