package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.Student;
import cn.edu.cdu.practice.service.StudentService;
import cn.edu.cdu.practice.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/StudentManagement/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8"); //设置POST请求编码
		response.setContentType("text/html;charset=UTF-8"); //设置响应内容类型
		
		HttpSession session = request.getSession();
		String account=(String)session.getAttribute("account");
		//测试代码begin
		if(account==null){
			account="201401";
		}
		//测试代码end
		//String no = request.getParameter("no"); 
		String background = request.getParameter("background");
		String experience = request.getParameter("experience");
		String direction = request.getParameter("direction");
		//测试代码begin
		System.out.println("background:"+background);
		System.out.println("experience:"+experience);
		System.out.println("direction:"+direction);
		//测试代码end
		Student stu=null;
		StudentService ss=new StudentServiceImpl();
		stu=ss.findById(account);
		if(stu!=null && background!=null){
			stu.setSubjectBackground(background);
			stu.setLearningExperience(experience);
			stu.setResearchDirection(direction);
			ss.update(stu);
		}
		request.setAttribute("student", stu);
		request.getRequestDispatcher("/StudentManagement/student-personal-information-maintenance.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
