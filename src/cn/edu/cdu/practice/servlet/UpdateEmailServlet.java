package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.dao.impl.StudentDaoImpl;
import cn.edu.cdu.practice.service.impl.UserServiceImpl;
import cn.edu.cdu.practice.utils.DbUtils;
import cn.edu.cdu.practice.utils.EmailUtils;
import cn.edu.cdu.practice.utils.IdentifyCodeUtils;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.MdPwdUtil;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName IdentifyCodeByEmailServlet.java
 * @version 1.0
 * @Description: 在学生修改个人信息时，更新其密保邮箱
 * @Author 于曦
 * @Date： 2017-5-1:上午10:04:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
@WebServlet("/Login/UpdateEmailServlet")
public class UpdateEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 先检查该学生是否有密保邮箱，如果没有，则根据输入的密保邮箱发送验证码，验证码发送成功就更新该学生的邮箱，发送失败，则提示用户，该密保邮箱有问题，请重新输入。
	 * 如果学生有密保邮箱，但与本次输入的不同，则根据输入的密保邮箱发送验证码，验证码发送成功就更新该学生的邮箱，发送失败，则提示用户，该密保邮箱有问题，请重新输入。
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得保密邮箱
		String mbemail = request.getParameter("mbemail");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		String account = (String) session.getAttribute("account");
		StudentDaoImpl sdi = new StudentDaoImpl();
		out.write(sdi.updateEmail(role, account, mbemail));
	}

}
