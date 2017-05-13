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

import cn.edu.cdu.practice.service.impl.UserServiceImpl;
import cn.edu.cdu.practice.utils.DbUtils;
import cn.edu.cdu.practice.utils.EmailUtils;
import cn.edu.cdu.practice.utils.IdentifyCodeUtils;
import cn.edu.cdu.practice.utils.Log4jUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName IdentifyCodeByEmailServlet.java
 * @version 1.0
 * @Description: 在页面控件上设置通过邮箱发送的验证码，并将验证码相关信息保存到mailbox_ Verification表中
 * @Author 于曦
 * @Date： 2017-5-1:上午10:04:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
@WebServlet("/Login/IdentifyCodeByEmailServlet")
public class IdentifyCodeByEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentifyCodeByEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 先检查保密邮箱是否在学生和企业表里存在，从而得到用户的角色，并放入到session中。如果有，则向保密邮箱发送验证码，同时把保密邮箱、验证码、用户类型保存到mailbox_verification表中
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得保密邮箱
		String mbemail = request.getParameter("mbemail");
		UserServiceImpl usi = new UserServiceImpl();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		List<String> userinfo = usi.searchbyEmail(mbemail);
		//如果字符串为空，是否转换为0？
		int role = Integer.parseInt(userinfo.get(1));
		System.out.println("role ==="+role);
		session.setAttribute("role", userinfo.get(1));
		//如果没有找到该保密邮箱，则提示用户输入错误；如果找到，则发送验证码，同时把信息存入mailbox_verification表
		if(userinfo.get(1).equals("")) {
			out.println("用户表中没有该邮箱，请重新输入！");
		}else{
			//这里的type好像没有意义，如果一定要，那在页面也需要修改
			//发送验证码到保密邮箱
			String identifyCode = IdentifyCodeUtils.getCode();
			EmailUtils.sendMail(mbemail, role,identifyCode);
			System.out.println("验证码？"+identifyCode);
			out.write(identifyCode);
			//将验证码保存到数据表中
			usi.getPassBack(mbemail,role,identifyCode);
		}
	}

}
