package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.Student;
import cn.edu.cdu.practice.service.StudentService;
import cn.edu.cdu.practice.service.impl.StudentServiceImpl;
import cn.edu.cdu.practice.utils.MdPwdUtil;

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
		request.setCharacterEncoding("UTF-8"); //设置POST请求编码
		response.setContentType("text/html;charset=UTF-8"); //设置响应内容类型
		
		HttpSession session = request.getSession();
		String account=(String)session.getAttribute("account");
		
		//测试代码begin
		if(account==null){
			account="201401";
		}
		session.setAttribute("role",2);
		//测试代码end
		
		String background = request.getParameter("background");
		String experience = request.getParameter("experience");
		String direction = request.getParameter("direction");
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		//测试代码begin
		System.out.println("background:"+background);
		System.out.println("experience:"+experience);
		System.out.println("direction:"+direction);
		System.out.println("oldpwd:"+oldpwd);
		System.out.println("newpwd:"+newpwd);
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
		//修改密码
		if(oldpwd!=null && newpwd!=null){
			if(oldpwd.trim()!=""){ 
				System.out.println("old:"+oldpwd);
				oldpwd=MdPwdUtil.MD5Password(oldpwd);//MD5加密
				if(oldpwd.equals(stu.getPassword())){
					System.out.println("oldMd5:"+oldpwd);
					stu.setPassword(MdPwdUtil.MD5Password(newpwd));
					ss.update(stu);
					PrintWriter out = response.getWriter();
					out.print("<script> alert(\"修改密码成功!\"); </script>");
				}else{
					response.getWriter().print("<script> alert('旧密码输入不正确！');</script>");
				}
			}
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
