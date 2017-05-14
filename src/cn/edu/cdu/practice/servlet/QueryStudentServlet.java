package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class QueryStudentServlet
 */
@WebServlet("/StudentManagement/QueryStudentServlet")
public class QueryStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStudentServlet() {
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
		
		//测试代码begin
		HttpSession session = request.getSession();
		session.setAttribute("role",2);
		//测试代码end
		String option = request.getParameter("op"); //获取条件选项
		String conValue = request.getParameter("conValue"); //获取条件值
		if(conValue==null)
			conValue="";		
		int index=0;
		List<Student> list=null;
		//Student stu=null;
		StudentService ss=new StudentServiceImpl();
		if(option!=null && conValue!=null){
			switch(option){
			case "all": //查询全部
				list=ss.findAll();
				conValue="";
				break;
			case "sel": //按已选/未选查询
				index=1;
				if(conValue.matches("\\d+"))
					list=ss.findBySelected(Integer.parseInt(conValue));
				break;
			case "com": //按公司查询
				index=2;
				list=ss.findByCompany(conValue);
				break;
			case "grade": //按年级查询
				index=3;
				if(conValue.matches("\\d+"))
					list=ss.findByGrade(Integer.parseInt(conValue));
				break;
			case "major": //按专业查询
				index=4;
				list=ss.findByMajor(conValue);
				break;
			case "year": //按年度查询
				index=5;
				if(conValue.matches("\\d+"))
					list=ss.findByYear(Integer.parseInt(conValue));
			default:break;
			}
		}else{
			list=ss.findAll();
		}			
		//测试代码begin
		if(list!=null){
			for(Student st:list){
				System.out.println("No:"+st.getNo());
				System.out.println("Name:"+st.getName());				
			}
		}
		System.out.println("option="+option);
		System.out.println("index="+index);
		System.out.println("value="+conValue);
		//测试代码end
		request.setAttribute("student", list);
		request.setAttribute("index", index);
		request.setAttribute("conValue", conValue);
		System.out.println(request.getAttribute("conValue"));
		request.getRequestDispatcher("/StudentManagement/student-management.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
