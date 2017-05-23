package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.service.impl.UserServiceImpl;
import cn.edu.cdu.practice.utils.EmailUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName LoginServlet.java
 * @version 1.0
 * @Description: 重置密码
 * @Author 于曦
 * @Date： 2017-5-1:上午10:04:04
 * Modification User： 于曦：所有输入的验证码都改成大写再进行比较
 * Modification Date： 2017-5-16:下午23:02
 */
@WebServlet("/Login/ResetPassServlet")
public class ResetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 重置密码：
	 * 1.从mialbox_verfication中找到保密邮箱所对应的验证码；
	 * 2.获得用户输入的保密邮箱中的验证码；
	 * 3.根据用户类型找到对应的表，修改里面的密码；
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得保密邮箱
		String mbemail = request.getParameter("mbemail");
		//获得用户输入的验证码
		String rvcinAction = request.getParameter("rvcinAction").toUpperCase();
		//获得隐藏控件中得到的验证码，该验证码是发到保密邮箱的。
		String rvchidden = request.getParameter("rvchidden").toUpperCase();
		//获得用户的新密码
		String newpassword = request.getParameter("newpassword");
		//获得用户输入的确认密码
		String newconfirmPassword = request.getParameter("newconfirmPassword");
		System.out.println("mbemail+rvchidden"+mbemail + "   " + "rvchidden");
		if(rvcinAction != null && rvchidden != null && newpassword != null && newconfirmPassword != null) {
			if(!rvchidden.equals(rvcinAction)){
				//如果输入的验证码和发送到邮箱的不一致，跳转到404页面
				System.out.println("hehe");
				//跳转到404页面,并打印错误信息
				String errorMessage = "验证码输入错误！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
			if(newpassword.equals(newconfirmPassword)){
				System.out.println("hahaha");
				UserServiceImpl usi = new UserServiceImpl();
				List<String> userinfo = usi.searchbyEmail(mbemail);
				String account = userinfo.get(0);
				String role = userinfo.get(1);
				usi.resetPass(newpassword,mbemail,role,account);
				//如果修改密码成功，跳转到登录页面
				request.getRequestDispatcher("/Login/index.jsp").forward(request, response);
			}
		}
//		request.getRequestDispatcher("/Login/login.jsp").forward(request, response);
//		response.sendRedirect("/404.html");
	}
}
